package com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.ref;

import com.njuse.jvmfinal.classloader.classfileparser.constantpool.info.InterfaceMethodrefInfo;
import com.njuse.jvmfinal.memory.jclass.JClass;
import com.njuse.jvmfinal.memory.jclass.Method;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterfaceMethodRef extends MemberRef {
//    private static int a = 0;
    // for test
    private Method method;

    public InterfaceMethodRef(RuntimeConstantPool runtimeConstantPool, InterfaceMethodrefInfo interfaceMethodrefInfo) {
        super(runtimeConstantPool, interfaceMethodrefInfo);
        //method
    }
    public Method resolveInterfaceMethodRef(JClass clazz) {
        try {
            return lookUpMethod(name, descriptor,clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     *
     * 这个方法用来解析methodRef对应的方法
     * 与上面的动态查找相比，这里的查找始终是从这个Ref对应的class开始查找的
     */
    public Method resolveInterfaceMethodRef() {
        try {
            if (this.clazz == null){
                getResolvedClass();
                assert this.clazz != null;
//                a++;
//                System.err.println(this.clazz.getName());
//                System.err.println(Arrays.toString(this.clazz.getMethods()));
//                System.err.println(this.name);
//                System.err.println(a);
            }
            this.method = lookUpMethod(name, descriptor, this.clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return this.method;
    }

    private Method lookUpMethod(String name, String descriptor, JClass clazz) throws ClassNotFoundException {
        Method method ;
        method = clazz.getMethodInClass(name,descriptor,true);
        if (method == null){
            method = clazz.getMethodInClass(name, descriptor,false);
        }

        if (method != null)
            return method;
        if (clazz.getInterfaces() != null) {
            for (JClass interFace : clazz.getInterfaces()) {
                method = lookUpMethod(name, descriptor, interFace);
                if (method != null)
                    return method;
            }
        }
        if (clazz.getSuperClass() != null) {
            return lookUpMethod(name, descriptor, clazz.getSuperClass());
        }
        return null;
    }

}
