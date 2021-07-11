package lollipop_JVM.Instructions.comparison;

import lollipop_JVM.Instructions.base.BranchInstruction;
import lollipop_JVM.runtimeDataArea.ProgramCounter;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.JObject;

public class IFNONNULL extends BranchInstruction {

    @Override
    public void execute(StackFrame frame) {
        JObject object = frame.getOperandStack().popObjectRef();
        if (!object.isNull()){
            ProgramCounter programCounter = frame.getThreadStack().getThread().getProgramCounter();
            programCounter.setNextPC(programCounter.getNextPC() - 3 + super.offset);
        }
    }
}
