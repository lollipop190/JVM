package lollipop_JVM.Instructions.load;

import lollipop_JVM.runtimeDataArea.StackFrame;

public class LLOAD_N extends LOAD_N {
    public LLOAD_N(int index){
        checkIndex(index);
        this.index = index;
    }

    @Override
    public void execute(StackFrame frame) {
        long val = frame.getLocalVars().getLong(this.index);
        frame.getOperandStack().pushLong(val);
    }
}
