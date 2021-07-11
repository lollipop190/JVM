package lollipop_JVM.Instructions.constant;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class LCONST_0 extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushLong((long)0);
    }
}

