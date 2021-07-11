package com.njuse.seecjvm.instructions.control;
import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.JThread;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;

public class IRETURN extends NoOperandsInstruction {

    /**
     * TODO： 实现这条指令
     */
    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int value = operandStack.popInt();
        JThread jThread = frame.getThread();
        jThread.popFrame();
        jThread.getTopFrame().getOperandStack().pushInt(value);
    }
}
