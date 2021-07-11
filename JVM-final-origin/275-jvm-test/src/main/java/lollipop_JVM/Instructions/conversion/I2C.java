package lollipop_JVM.Instructions.conversion;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class I2C extends InstructionWithNoOperands {

    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int popInt = operandStack.popInt();
        char pushChar = (char)popInt;
        operandStack.pushInt(pushChar);
    }
}
