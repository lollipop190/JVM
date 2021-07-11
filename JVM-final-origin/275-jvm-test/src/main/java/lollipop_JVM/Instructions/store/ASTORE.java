package lollipop_JVM.Instructions.store;

import lollipop_JVM.Instructions.base.InstructionWithEightIndex;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.JObject;

public class ASTORE extends InstructionWithEightIndex {
    @Override
    public void execute(StackFrame frame) {
        JObject object = frame.getOperandStack().popObjectRef();
        frame.getLocalVars().setObjectRef(this.index , object);
    }
}
