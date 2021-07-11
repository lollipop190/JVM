package lollipop_JVM.Instructions.conversion;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class I2F extends InstructionWithNoOperands {

    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int popInt = operandStack.popInt();
        float pushFloat = (float)popInt;
        operandStack.pushFloat(pushFloat);
    }
}
