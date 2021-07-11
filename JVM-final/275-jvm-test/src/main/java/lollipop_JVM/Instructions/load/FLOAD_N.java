package lollipop_JVM.Instructions.load;

import lollipop_JVM.runtimeDataArea.StackFrame;

public class FLOAD_N extends LOAD_N{
    public FLOAD_N(int index){
        checkIndex(index);
        this.index = index;
    }

    @Override
    public void execute(StackFrame frame) {
        float val = frame.getLocalVars().getFloat(this.index);
        frame.getOperandStack().pushFloat(val);
    }
}
