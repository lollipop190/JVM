package com.njuse.jvmfinal.memory.jclass;

import com.njuse.jvmfinal.classloader.classfileparser.ClassFile;
import com.njuse.jvmfinal.classloader.classfileparser.FieldInfo;
import com.njuse.jvmfinal.classloader.classfileparser.MethodInfo;
import com.njuse.jvmfinal.classloader.classfileparser.constantpool.ConstantPool;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import lollipop_JVM.classLoader.ClassLoader;
import lollipop_JVM.classLoader.InterClassLoader;
import lollipop_JVM.memory.jclass.InitState;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.ThreadStack;
import lollipop_JVM.runtimeDataArea.struct.ArrayObject;
import lollipop_JVM.runtimeDataArea.struct.Vars;
import lollipop_JVM.runtimeDataArea.struct.array.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JClass {
    private short accessFlags;
    private String name;
    private String superClassName;
    private String[] interfaceNames;
    private RuntimeConstantPool runtimeConstantPool;
    private Field[] fields;
    private Method[] methods;

    private InterClassLoader classLoader;
    //add the corresponding classLoader.


    //    private EntryType loadEntryType; //请自行设计是否记录、如何记录加载器
    private JClass superClass;
    private JClass[] interfaces;
    private int instanceSlotCount;
    private int staticSlotCount;
    private Vars staticVars; // 请自行设计数据结构
    private InitState initState; // 请自行设计初始化状态

    public JClass(){
    }
    public JClass(ClassFile classFile) {
        this.accessFlags = classFile.getAccessFlags();
        this.name = classFile.getClassName();
        if (!this.name.equals("java/lang/Object")) {
            // index of super class of java/lang/Object is 0
            this.superClassName = classFile.getSuperClassName();
        } else {
            this.superClassName = "";
        }
        this.interfaceNames = classFile.getInterfaceNames();
        this.fields = parseFields(classFile.getFields());
        this.methods = parseMethods(classFile.getMethods());
        this.runtimeConstantPool = parseRuntimeConstantPool(classFile.getConstantPool());
    }

    private Field[] parseFields(FieldInfo[] info) {
        int len = info.length;
        fields = new Field[len];
        for (int i = 0; i < len; i++) {
            fields[i] = new Field(info[i], this);
        }
        return fields;
    }

    private Method[] parseMethods(MethodInfo[] info) {
        int len = info.length;
        methods = new Method[len];
        for (int i = 0; i < len; i++) {
            methods[i] = new Method(info[i], this);
        }
        return methods;
    }

    public Method getMethodInClass(String name, String descriptor, boolean isStatic) {
        for (Method m : this.methods) {
            assert this.methods != null;
            if (m.getDescriptor().equals(descriptor)
                    && m.getName().equals(name)
                    && m.isStatic() == isStatic) {
                return m;
            }
        }
        return null;
    }

    public void initClass(ThreadStack threadStack, JClass clazz) {
        clazz.setInitState(InitState.BUSY);
        Method clinit = clazz.getMethodInClass("<clinit>", "()V", true);
        if (clinit != null) {

            StackFrame stackFrame = new StackFrame(threadStack, clinit, clinit.getMaxStack(), clinit.getMaxLocal());
            threadStack.pushFrame(stackFrame);
        }

        //superClass
        if (!clazz.isInterface()){
            JClass superClass = clazz.getSuperClass();
            if (superClass != null && superClass.getInitState() != InitState.SUCCESS){
                initClass(threadStack,superClass);
            }
        }
        clazz.setInitState(InitState.SUCCESS);
    }
    public Method getMainMethod() {
        return getMethodInClass("main", "([Ljava/lang/String;)V", true);
    }

    public boolean isInterface() {
        return 0 != (this.accessFlags & AccessFlags.ACC_INTERFACE);
    }

    public boolean isPublic() {
        return 0 != (this.accessFlags & AccessFlags.ACC_PUBLIC);
    }
    public boolean isAccSuper() {
        return 0 != (this.accessFlags & AccessFlags.ACC_SUPER);
    }

    public String getPackageName() {
        int index = name.lastIndexOf('/');
        if (index >= 0) return name.substring(0, index);
        else return "";
    }


    public boolean isAccessibleTo(JClass caller) {
        if (this.isPublic())
            return true;
        else return caller.getPackageName().equals(this.getPackageName());
    }

    private RuntimeConstantPool parseRuntimeConstantPool(ConstantPool cp) {
        return new RuntimeConstantPool(cp, this);
    }

    public ArrayObject getArrayObject(int length){
        String type;
        try {
           type = ArrayType.getType(Integer.parseInt(this.name));
        }catch (NumberFormatException e){
            return new MultiArrayObject(length,"multiArray");
        }
        switch (type){
            case "boolean":
                return new BooleanArrayObject(length, type);
            case "byte":
                return new ByteArrayObject(length, type);
            case "short":
                return new ShortArrayObject(length, type);
            case "char":
                return new CharArrayObject(length, type);
            case "int":
                return new IntArrayObject(length, type);
            case "long":
                return new LongArrayObject(length, type);
            case "float":
                return new FloatArrayObject(length ,type);
            case "double":
                return new DoubleArrayObject(length, type);
            default:
                return null;

        }
    }

    private boolean isSubClassOf(JClass otherClass) {
        for(JClass superClass = this.getSuperClass(); superClass != null; superClass = superClass.getSuperClass()) {
            if (superClass == otherClass) {
                return true;
            }
        }
        return false;
    }
    private boolean isArray(){
        try{
            Integer.parseInt(this.name);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    private boolean isImplenments(JClass target){
        for(JClass superClass = this; superClass != null; superClass  = superClass.getSuperClass()){
            for(JClass interfaceClass : superClass.getInterfaces()){
                if( interfaceClass == target || interfaceClass.isSubInterfaceOf(target)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSubInterfaceOf(JClass target){
        for(JClass interfaceClass : this.getInterfaces()){
            if( interfaceClass == target || interfaceClass.isSubInterfaceOf(target)){
                return true;
            }
        }
        return false;
    }
    public JClass getComponentClass() {

        ClassLoader classLoader = ClassLoader.getInstance();
        if (this.name.charAt(0) == '['){
            //this is multiArray
            String componentTypeName = this.name.substring(1);
            //if there is no '['
            if (componentTypeName.charAt(1) == 'L'){
                componentTypeName = componentTypeName.substring(2,componentTypeName.length()-1);
            }
            else{
                componentTypeName = "" + ArrayType.getIntType(componentTypeName.substring(1));

            }
            return classLoader.loadClass(componentTypeName,this.getClassLoader());
//            else//if there in '[' , which means it's still an array
//                {
//
//            }

        }




        try{
            Integer.parseInt(this.name);
        }catch (NumberFormatException e ){
            throw new RuntimeException("Invalid Array:" + this.name);
        }

        return classLoader.loadClass(this.getName(), this.getClassLoader());

    }
    public boolean isAssignableFrom(JClass target) {
        //target is "T"
        //"this" is "S"
        JClass src = this;
        if (src == target)
            return true;

        if (!src.isInterface() && !src.isArray()){
            if (!target.isArray() && !target.isInterface()){
                return src.isSubClassOf(target);
            }else if (target.isInterface()){
                return src.isImplenments(target);
            }
        }

        if (src.isInterface()){
            if (!target.isInterface() && !target.isArray()){
                return target.getName().equals("java/lang/Object");
            }else if (target.isInterface()){
                return src.isSubInterfaceOf(target);
            }
        }

        if (src.isArray()){
            if (!target.isArray() && !target.isInterface()){
                return target.getName().equals("java/lang/Object");
            }else if (target.isInterface()){
                return target.getName().equals("java/lang/Cloneable") || target.getName().equals("java/io/Serializable");
            }else if (target.isArray()){
                return src.getComponentClass() == target.getComponentClass();
            }
        }






        return false;
    }

    public static boolean isAssignableFromForArray(JClass clazz){
        return false;
    }
}

//        if (!src.isArray()) {
//            if (!target.isInterface()) {
//                if (!src.isInterface()) {
//                    return target.isSubClassOf(src);
//                } else {
//                    return target.isImplenments(src);
//                }
//            } else if (target.isInterface()) {
//                if (!src.isInterface()) {
//                    return src.getName().equals("java/lang/Object");
//                } else if (src.isInterface()) {
//                    return target.isSubInterfaceOf(src);
//                }
//            }
//        } else if (!src.isArray()) {
//            if (!src.isInterface()) {
//                return src.getName().equals("java/lang/Object");
//            }else {
//                return
//            }
                //                if (target.isInterface()) {
//                    return src.isSubInterfaceOf(target);
//                } else if (!target.isInterface()) {
//                    return target.getName().equals("java/lang/Object");
//                }

//            else if (target.isArray()){
//                JClass srcComponent = src.getComponentClass();
//                JClass targetComponent = target.getComponentClass();
//                return srcComponent == targetComponent;
//            }
