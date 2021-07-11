package com.njuse.seecjvm.instructions.references;

import com.njuse.seecjvm.instructions.base.Index16Instruction;
import com.njuse.seecjvm.memory.JHeap;
import com.njuse.seecjvm.memory.jclass.InitState;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.ClassRef;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.JObject;
import com.njuse.seecjvm.runtime.struct.NonArrayObject;

public class NEW extends Index16Instruction {
    /**
     * TODO 实现这条指令
     * 其中 对应的index已经读取好了
     */
    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        ClassRef classRef = (ClassRef)runtimeConstantPool.getConstant(index);
        OperandStack operandStack = frame.getOperandStack();
        try{
            JClass jClass = classRef.getResolvedClass();
            if (jClass.getInitState() == InitState.PREPARED) {
                frame.setNextPC(frame.getNextPC() - 3);
                jClass.initClass(frame.getThread(), jClass);
                return;
            }
            NonArrayObject newObj = new NonArrayObject(jClass);
            JHeap.getInstance().addObj(newObj);
            operandStack.pushObjectRef(newObj);
        }catch (ClassNotFoundException e ){
            e.printStackTrace();
        }
    }

}
