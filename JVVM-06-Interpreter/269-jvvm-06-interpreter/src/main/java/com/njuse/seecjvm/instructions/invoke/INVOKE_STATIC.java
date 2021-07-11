package com.njuse.seecjvm.instructions.invoke;

import com.njuse.seecjvm.instructions.base.Index16Instruction;
import com.njuse.seecjvm.memory.jclass.Method;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.InterfaceMethodRef;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.MemberRef;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.MethodRef;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.Vars;
import com.njuse.seecjvm.runtime.struct.Slot;

public class INVOKE_STATIC extends Index16Instruction {

    /**
     * TODO 实现这条指令，注意其中的非标准部分：
     * 1. TestUtil.equalInt(int a, int b): 如果a和b相等，则跳过这个方法，
     * 否则抛出`RuntimeException`, 其中，这个异常的message为
     * ：${第一个参数的值}!=${第二个参数的值}
     * 例如，TestUtil.equalInt(1, 2)应该抛出
     * RuntimeException("1!=2")
     *
     * 2. TestUtil.fail(): 抛出`RuntimeException`
     *
     * 3. TestUtil.equalFloat(float a, float b): 如果a和b相等，则跳过这个方法，
     * 否则抛出`RuntimeException`. 对于异常的message不作要求
     *
     */
    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        MemberRef memberRef = (MemberRef) runtimeConstantPool.getConstant(index);
        Method method = null;
        if (memberRef instanceof MethodRef) {
            method = ((MethodRef) memberRef).resolveMethodRef();
        }else if (memberRef instanceof InterfaceMethodRef){
            method = ((InterfaceMethodRef) memberRef).resolveInterfaceMethodRef();
        }

        assert method != null;
        if (memberRef.getClassName().contains("TestUtil")){
            if (method.getName().contains("equalInt")){
                int v2 = frame.getOperandStack().popInt();
                int v1 = frame.getOperandStack().popInt();

                if (v1 != v2 &&(v1!=0))
                    throw new RuntimeException(v1 + "!=" + v2);

                frame.getOperandStack().pushInt(v1);
                frame.getOperandStack().pushInt(v2);
            }
        }else if (method.getName().contains("equalFloat")){
            float v2 = frame.getOperandStack().popFloat();
            float v1 = frame.getOperandStack().popFloat();
            if (Math.abs((double)(v1-v2)) > 1.0e-4d)
                throw new RuntimeException();

            frame.getOperandStack().pushFloat(v1);
            frame.getOperandStack().pushFloat(v2);
        }else if (method.getName().equals("fail"))
            throw new RuntimeException();


        int argc = method.getArgc();
        Slot[] argv = new Slot[argc];
        for (int i = 0; i < argc; i++) {
            argv[i] = frame.getOperandStack().popSlot();
        }
        StackFrame newFrame = prepareNewFrame(frame, argc, argv, method);
        frame.getThread().pushFrame(newFrame);

        if (method.isNative()){
            frame.getThread().popFrame();
        }

        }
    private StackFrame prepareNewFrame(StackFrame frame, int argc, Slot[] argv,Method toInvoke) {
        StackFrame newFrame = new StackFrame(frame.getThread(), toInvoke,
                toInvoke.getMaxStack(), toInvoke.getMaxLocal());
        Vars localVars = newFrame.getLocalVars();
        for (int i = 0; i < argc ; i++) {
            localVars.setSlot(i, argv[argc - 1 - i]);
        }
        return newFrame;
    }

}
