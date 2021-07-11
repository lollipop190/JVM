package lollipop_JVM.Instructions.comparison;


import lollipop_JVM.Instructions.base.BranchInstruction;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.ProgramCounter;
import lollipop_JVM.runtimeDataArea.StackFrame;

public abstract class IF_ICMPCOND extends BranchInstruction {


    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        if (condition(v1,v2)){
            ProgramCounter programCounter = frame.getThreadStack().getThread().getProgramCounter();
            programCounter.setNextPC(programCounter.getNextPC() - 3 + super.offset);
        }
    }

    protected abstract boolean condition(int v1, int v2);
}
