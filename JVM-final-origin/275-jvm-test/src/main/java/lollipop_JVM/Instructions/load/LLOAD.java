package lollipop_JVM.Instructions.load;

import lollipop_JVM.Instructions.base.InstructionWithEightIndex;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class LLOAD extends InstructionWithEightIndex {
    @Override
    public void execute(StackFrame frame) {
        long value = frame.getLocalVars().getLong(this.index);
        frame.getOperandStack().pushLong(value);
    }
}
