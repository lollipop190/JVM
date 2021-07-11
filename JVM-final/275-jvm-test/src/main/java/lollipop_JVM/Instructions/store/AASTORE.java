package lollipop_JVM.Instructions.store;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.JObject;
import lollipop_JVM.runtimeDataArea.struct.array.MultiArrayObject;

public class AASTORE extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        JObject object = stack.popObjectRef();
        int index = stack.popInt();
        MultiArrayObject multiArrayObject = (MultiArrayObject)stack.popObjectRef();
        multiArrayObject.getArray()[index] = object;
    }
}
