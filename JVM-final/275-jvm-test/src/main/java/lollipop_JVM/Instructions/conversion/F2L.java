package lollipop_JVM.Instructions.conversion;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class F2L extends InstructionWithNoOperands {


    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        float value = operandStack.popFloat();
        operandStack.pushLong((long) value);
    }
}
