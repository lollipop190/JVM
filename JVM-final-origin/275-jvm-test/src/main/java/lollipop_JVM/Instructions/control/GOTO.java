package lollipop_JVM.Instructions.control;

import lollipop_JVM.Instructions.base.BranchInstruction;
import lollipop_JVM.runtimeDataArea.ProgramCounter;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class GOTO extends BranchInstruction {

    /**
     * TODO：实现这条指令
     * 这是一个已完成的例子
     */
    @Override
    public void execute(StackFrame frame) {
        ProgramCounter programCounter = ProgramCounter.getInstance();
        int branchPC = programCounter.getNextPC() - 3 + super.offset;// 3 = opcode + signed short offset
        //设置PC为跳转后地址
        programCounter.setNextPC(branchPC);
    }
}