package lollipop_JVM.Instructions.math.algorithm;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class INEG extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        int value = frame.getOperandStack().popInt();
        frame.getOperandStack().pushInt(-value);
    }
}
