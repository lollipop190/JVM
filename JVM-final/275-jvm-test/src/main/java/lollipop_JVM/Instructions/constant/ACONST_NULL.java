package lollipop_JVM.Instructions.constant;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.NullObject;

public class ACONST_NULL extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushObjectRef(new NullObject(null));
    }
}
