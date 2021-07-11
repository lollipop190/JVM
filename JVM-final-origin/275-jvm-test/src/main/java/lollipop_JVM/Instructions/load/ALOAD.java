package lollipop_JVM.Instructions.load;

import lollipop_JVM.Instructions.base.InstructionWithEightIndex;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.JObject;

public class ALOAD extends InstructionWithEightIndex {
    @Override
    public void execute(StackFrame frame) {
        JObject object = frame.getLocalVars().getObjectRef(this.index);
        frame.getOperandStack().pushObjectRef(object);
    }
}
