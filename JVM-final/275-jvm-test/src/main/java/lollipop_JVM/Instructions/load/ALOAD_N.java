package lollipop_JVM.Instructions.load;

import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.JObject;

public class ALOAD_N extends LOAD_N {
    public ALOAD_N(int index){
        checkIndex(index);
        this.index = index;
    }

    @Override
    public void execute(StackFrame frame) {
        JObject object = frame.getLocalVars().getObjectRef(this.index);
        frame.getOperandStack().pushObjectRef(object);
    }
}
