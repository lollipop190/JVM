package lollipop_JVM.Instructions.store;

import lollipop_JVM.Instructions.base.InstructionWithEightIndex;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.Vars;

public class DSTORE extends InstructionWithEightIndex {


    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        double ret = stack.popDouble();
        Vars vars = frame.getLocalVars();
        vars.setDouble(index,ret);
    }
}
