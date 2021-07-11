package lollipop_JVM.Instructions.load;

import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.Vars;

public class DLOAD_N extends LOAD_N {
    public DLOAD_N(int index) {
        checkIndex(index);
        this.index = index;
    }

    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Vars vars = frame.getLocalVars();
        double ret = vars.getDouble(index);
        operandStack.pushDouble(ret);
    }
}
