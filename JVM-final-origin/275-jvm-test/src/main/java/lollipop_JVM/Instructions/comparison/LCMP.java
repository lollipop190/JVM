package lollipop_JVM.Instructions.comparison;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class LCMP extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        long v2 = stack.popLong();
        long v1 = stack.popLong();
        if (v1 > v2)
            stack.pushInt(1);
        else if (v1 ==v2)
            stack.pushInt(0);
        else
            stack.pushInt(-1);
    }
}
