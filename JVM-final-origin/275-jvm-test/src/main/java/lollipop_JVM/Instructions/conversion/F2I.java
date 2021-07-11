package lollipop_JVM.Instructions.conversion;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class F2I extends InstructionWithNoOperands {


    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        float value = operandStack.popFloat();
        operandStack.pushInt((int) value);
    }
}
