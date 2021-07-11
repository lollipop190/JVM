package lollipop_JVM.Instructions.math.algorithm;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class ISHL extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        int loc = v2 & 0b011111;
        operandStack.pushInt(v1 << loc);

    }
}
