package lollipop_JVM.Instructions.constant;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class DCONST_1 extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushDouble((double)1.0);
    }
}
