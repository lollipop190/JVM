package lollipop_JVM.Instructions.store;

import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.Vars;

public class DSTORE_N extends STORE_N {
    public DSTORE_N(int index) {
        checkIndex(index);
        this.index = index;
    }

    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        double ret = stack.popDouble();
        Vars vars = frame.getLocalVars();
        vars.setDouble(index,ret);

    }
}
