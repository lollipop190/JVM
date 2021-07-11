package lollipop_JVM.Instructions.load;

import lollipop_JVM.Instructions.base.InstructionWithEightIndex;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class FLOAD extends InstructionWithEightIndex {
    @Override
    public void execute(StackFrame frame) {
        float v1 = frame.getLocalVars().getFloat(this.index);
        frame.getOperandStack().pushFloat(v1);
    }
}
