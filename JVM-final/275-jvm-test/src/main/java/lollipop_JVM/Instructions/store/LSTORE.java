package lollipop_JVM.Instructions.store;

import lollipop_JVM.Instructions.base.InstructionWithEightIndex;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.Vars;

public class LSTORE extends InstructionWithEightIndex {


    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        long ret = stack.popLong();
        Vars vars = frame.getLocalVars();
        vars.setLong(index,ret);
    }
}
