package com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.ref;

import com.njuse.jvmfinal.classloader.classfileparser.constantpool.info.MethodrefInfo;
import com.njuse.jvmfinal.memory.jclass.JClass;
import com.njuse.jvmfinal.memory.jclass.Method;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MethodRef extends MemberRef {
    private Method method;

    public MethodRef(RuntimeConstantPool runtimeConstantPool, MethodrefInfo methodrefInfo) {
        super(runtimeConstantPool, methodrefInfo);
    }
    /**
     *
     * 这个方法用来实现对象方法的动态查找
     * @param clazz 对象的引用
     */
    public Method resolveMethodRef(JClass clazz) {
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
    public Method resolveMethodRef() {
        try {
            this.method = lookUpMethod(name, descriptor, clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return this.method;
    }

    private Method lookUpMethod(String name, String descriptor, JClass clazz) throws ClassNotFoundException {
        Method method ;
        method = getResolvedClass().getMethodInClass(name,descriptor,true);
        if (method == null){
            method = getResolvedClass().getMethodInClass(name, descriptor,false);
        }

        if (method != null)
            return method;
//        for (Method method : clazz.getMethods()) {
//            if (f.getDescriptor().equals(descriptor) && f.getName().equals(name)) {
//                return f;
//            }
//        }
        for (JClass interFace : clazz.getInterfaces()) {
            method = lookUpMethod(name, descriptor, interFace);
            if (method != null)
                return method;
        }

        if (clazz.getSuperClass() != null) {
            return lookUpMethod(name, descriptor, clazz.getSuperClass());
        }
        return null;
    }
}
