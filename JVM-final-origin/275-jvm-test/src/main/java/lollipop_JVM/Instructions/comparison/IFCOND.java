package lollipop_JVM.Instructions.comparison;


import lollipop_JVM.Instructions.base.BranchInstruction;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.ProgramCounter;
import lollipop_JVM.runtimeDataArea.StackFrame;

public abstract class IFCOND extends BranchInstruction {

    /**
     * TODO：实现这条指令
     * 其中，condition 方法是对具体条件的计算，当条件满足时返回true，否则返回false
     */
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
