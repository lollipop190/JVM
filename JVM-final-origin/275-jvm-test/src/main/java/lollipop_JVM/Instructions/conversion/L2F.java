package lollipop_JVM.Instructions.conversion;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class L2F extends InstructionWithNoOperands {


    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long value = operandStack.popLong();
        operandStack.pushFloat((float) value);
    }
}
