package lollipop_JVM.Instructions.conversion;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class D2I extends InstructionWithNoOperands {


    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double value = operandStack.popDouble();
        operandStack.pushInt((int)value);
    }
}
