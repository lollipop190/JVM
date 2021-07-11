package lollipop_JVM.Instructions.conversion;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class I2B extends InstructionWithNoOperands {

    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int popInt = operandStack.popInt();
        byte pushByte = (byte)popInt;
        operandStack.pushInt(pushByte);
    }
}
