package lollipop_JVM.Instructions.store;

import lollipop_JVM.Instructions.base.InstructionWithEightIndex;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.Vars;

public class FSTORE extends InstructionWithEightIndex {


    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        float ret = stack.popFloat();
        Vars vars = frame.getLocalVars();
        vars.setFloat(index,ret);
    }
}
