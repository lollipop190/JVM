package lollipop_JVM.Instructions.comparison;


import lollipop_JVM.Instructions.base.BranchInstruction;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.ProgramCounter;
import lollipop_JVM.runtimeDataArea.StackFrame;

public abstract class IFCOND extends BranchInstruction {


    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int operation = operandStack.popInt();
        if (condition(operation)){
            ProgramCounter programCounter = ProgramCounter.getInstance();
            programCounter.setNextPC(programCounter.getNextPC() - 3 + super.offset);
        }

    }

    protected abstract boolean condition(int value);

}
