package com.njuse.seecjvm.instructions.invoke;

import com.njuse.seecjvm.instructions.base.Index16Instruction;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.memory.jclass.Method;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.Constant;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.InterfaceMethodRef;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.MethodRef;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.Vars;
import com.njuse.seecjvm.runtime.struct.JObject;
import com.njuse.seecjvm.runtime.struct.Slot;

import java.nio.ByteBuffer;


public class INVOKE_INTERFACE extends Index16Instruction {

    /**
     * TODO：实现这个方法
     * 这个方法用于读取这条指令操作码以外的部分
     */
    @Override
    public void fetchOperands(ByteBuffer reader) {
        super.fetchOperands(reader);
        reader.get();
        reader.get();
    }

    /**
     * TODO：实现这条指令
     */
    @Override
    public void execute(StackFrame frame) {
         Constant methodCons = frame.getMethod().getClazz().getRuntimeConstantPool().getConstant(index);
         InterfaceMethodRef methodRef = (InterfaceMethodRef)methodCons;
         Method method = methodRef.resolveInterfaceMethodRef();

        int argc = method.getArgc();
        Slot[] argv = new Slot[argc];
        for (int i = 0; i < argc; i++) {
            argv[i] = frame.getOperandStack().popSlot();
        }

        JObject objectRef = frame.getOperandStack().popObjectRef();
        JClass clazz = objectRef.getClazz();
        Method toInvoke = methodRef.resolveInterfaceMethodRef(clazz);
        StackFrame newFrame = prepareNewFrame(frame, argc, argv, objectRef, toInvoke);
        frame.getThread().pushFrame(newFrame);
        if (method.isNative()){
            frame.getThread().popFrame();
        }
    }
    private StackFrame prepareNewFrame(StackFrame frame, int argc, Slot[] argv, JObject objectRef, Method toInvoke) {
        StackFrame newFrame = new StackFrame(frame.getThread(), toInvoke,
                toInvoke.getMaxStack(), toInvoke.getMaxLocal() + 1);
        Vars localVars = newFrame.getLocalVars();
        Slot thisSlot = new Slot();
        thisSlot.setObject(objectRef);
        localVars.setSlot(0, thisSlot);
        for (int i = 1; i < argc + 1; i++) {
            localVars.setSlot(i, argv[argc - i]);
        }
        return newFrame;
    }


}
