package lollipop_JVM.Instructions.stack;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class POP extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().popSlot();
    }

}
