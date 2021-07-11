package lollipop_JVM.Instructions.control;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.ProgramCounter;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class RETURN extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        frame.getThreadStack().popFrame();

        // process pc
        ProgramCounter programCounter =  frame.getThreadStack().getThread().getProgramCounter();
        if (!programCounter.getSavedPC().empty())
            programCounter.backToLastFrame();
    }
}
