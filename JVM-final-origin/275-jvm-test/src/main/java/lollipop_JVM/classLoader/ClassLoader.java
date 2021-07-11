package lollipop_JVM.classLoader;

import com.njuse.jvmfinal.classloader.classfileparser.ClassFile;
import com.njuse.jvmfinal.memory.jclass.Field;
import com.njuse.jvmfinal.memory.jclass.JClass;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.wrapper.IntWrapper;
import lollipop_JVM.classFileReader.ClassFileReader;
import lollipop_JVM.memory.jclass.InitState;
import lollipop_JVM.runtimeDataArea.MethodArea;
import lollipop_JVM.runtimeDataArea.struct.NullObject;
import lollipop_JVM.runtimeDataArea.struct.Slot;
import lollipop_JVM.runtimeDataArea.struct.Vars;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;

public class ClassLoader {
    private static ClassLoader classLoader = new ClassLoader();
    private ClassLoader(){
    }
    public static ClassLoader getInstance(){
        return classLoader;
    }
/**todo
 * leave loading array class undone.
 * 2020.07.04
 * */



/**todo
* do not forget to add the class into the classMap.
 * 2020.07.05
* */
    public JClass loadClass(String className, InterClassLoader classLoader) {
        if (MethodArea.getClass(className) != null)
            return MethodArea.getClass(className);

        //for multiArray
        if (className.charAt(0) == '['){
            return loadArrayClass(className, classLoader);
        }
        try{
            Integer.parseInt(className);
            return loadArrayClass(className, classLoader);
        }catch (NumberFormatException e){
            return loadNonArrayClass(className, classLoader);
        }
    }

    private JClass loadArrayClass(String className, InterClassLoader classLoader){
        JClass arrayClass = new JClass();
        arrayClass.setAccessFlags((short)1);
        arrayClass.setName(className);
        arrayClass.setInitState(InitState.SUCCESS);
        arrayClass.setSuperClass(loadClass("java/lang/Object", classLoader));
        arrayClass.setInterfaces(new JClass[]{
                loadClass("java/lang/Cloneable", classLoader),
                loadClass("java/io/Serializable", classLoader)
        });

        MethodArea.addClass(className, arrayClass);
        return arrayClass;
    }


    private JClass loadNonArrayClass(String className, InterClassLoader classLoader){
        try {
            Pair<byte[], InterClassLoader> pair = ClassFileReader.readClassFile(className, classLoader);

            //for test

//            System.err.println(className);
//            System.err.println(Arrays.toString(pair.getKey()));
            JClass clazz = defineClass(pair.getKey(), pair.getValue());
            // ignore the process of verify
            prepare(clazz);
            clazz.setInitState(InitState.PREPARED);

            return clazz;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    private JClass defineClass(byte[] data, InterClassLoader classLoader){
        ClassFile classFile = new ClassFile(data);
        JClass clazz = new JClass(classFile);
        clazz.setClassLoader(classLoader);
        resolveSuperClass(clazz);
        resolveInterfaces(clazz);
        MethodArea.addClass(clazz.getName(),clazz);
        return clazz;
    }
    private void resolveSuperClass(JClass clazz) {
        String superClassName = clazz.getSuperClassName();
        if (!superClassName.equals("")){
            clazz.setSuperClass(loadClass(superClassName,clazz.getClassLoader()));
        }

    }

    private void resolveInterfaces(JClass clazz) {
        String[] interfacesNames = clazz.getInterfaceNames();
        JClass[] interfaces = new JClass[interfacesNames.length];
        for (int i = 0; i <interfacesNames.length;i++) {
            interfaces[i] = loadClass(interfacesNames[i],clazz.getClassLoader());
        }

        clazz.setInterfaces(interfaces);
    }

    private void prepare(JClass clazz) {
        calInstanceFieldSlotIDs(clazz);
        calStaticFieldSlotIDs(clazz);
        allocAndInitStaticVars(clazz);



        /**
         * step1 (We do it for you here)
         *      count the fields slot id in instance
         *      count the fields slot id in class
         * step2
         *      alloc memory for fields(We do it for you here) and init static vars
         * step3
         *      set the init state
         */
    }
    private void calInstanceFieldSlotIDs(JClass clazz) {
        int slotID = 0;
        if (clazz.getSuperClass() != null) {
            slotID = clazz.getSuperClass().getInstanceSlotCount();
        }
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            if (!f.isStatic()) {
                f.setSlotID(slotID);
                slotID++;
                if (f.isLongOrDouble()) slotID++;
            }
        }
        clazz.setInstanceSlotCount(slotID);
    }

    private void calStaticFieldSlotIDs(JClass clazz) {
        int slotID = 0;
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            if (f.isStatic()) {
                f.setSlotID(slotID);
                slotID++;
                if (f.isLongOrDouble()) slotID++;
            }
        }
        clazz.setStaticSlotCount(slotID);

    }

    private void allocAndInitStaticVars(JClass clazz) {
        clazz.setStaticVars(new Vars(clazz.getStaticSlotCount()));
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            if(f.isStatic()){
                if (f.getConstValueIndex()!=0)
                    loadValueFromRTCP(clazz,f);
                else
                    initDefaultValue(clazz,f);
            }
            //todo
            /**
             * Add some codes here.
             *
             * Refer to manual for details.
             */
        }
    }
    /**
     * primitive type is set to 0
     * ref type is set to null
     */
    private void initDefaultValue(JClass clazz, Field field) {
        Vars vars = clazz.getStaticVars();
        Slot[] slots =  vars.getVarSlots();
        int slotID = field.getSlotID();
        slots[slotID].setValue(0);
        slots[slotID].setObject(new NullObject(null));
        if (field.getDescriptor().charAt(0) == 'J' || field.getDescriptor().charAt(0) == 'D'){
            slots[slotID+1].setValue(0);
            slots[slotID+1].setObject(new NullObject(null));
        }
        //todo
        /**
         * Add some codes here.
         * step 1
         *      get static vars of class
         * step 2
         *      get the slotID index of field
         * step 3
         *      switch by descriptor or some part of descriptor
         *      Handle basic type ZBCSIJDF and references (with new NullObject())
         */
    }

    /**
     * load const value from runtimeConstantPool for primitive type
     * String is not support now
     */
    private void loadValueFromRTCP(JClass clazz, Field field) {
        Vars vars = clazz.getStaticVars();
        RuntimeConstantPool runtimeConstantPool = clazz.getRuntimeConstantPool();
        int slotID = field.getSlotID();
        switch (field.getDescriptor().charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                vars.setInt(slotID, ((IntWrapper) runtimeConstantPool.getConstant(field.getConstValueIndex())).getValue());
                break;
            case 'J':
                vars.setLong(slotID, ((IntWrapper) runtimeConstantPool.getConstant(field.getConstValueIndex())).getValue());
                break;
            case 'D':
                vars.setDouble(slotID, ((IntWrapper) runtimeConstantPool.getConstant(field.getConstValueIndex())).getValue());
                break;
            case 'F':
                vars.setFloat(slotID, ((IntWrapper) runtimeConstantPool.getConstant(field.getConstValueIndex())).getValue());
                break;
            case 'L':
                vars.setObjectRef(slotID, new NullObject(null));
                break;
        }
    }
}
