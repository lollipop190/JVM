package lollipop_JVM.Instructions.store;

import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.JObject;

public class ASTORE_N extends STORE_N {
    public ASTORE_N(int index){
        checkIndex(index);
        this.index = index;
    }

    @Override
    public void execute(StackFrame frame) {
        JObject object = frame.getOperandStack().popObjectRef();
        frame.getLocalVars().setObjectRef(this.index, object);
    }
}
