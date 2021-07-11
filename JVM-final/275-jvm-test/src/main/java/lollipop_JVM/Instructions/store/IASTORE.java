package lollipop_JVM.Instructions.store;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.array.IntArrayObject;

public class IASTORE extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int value = stack.popInt();
        int index = stack.popInt();
        IntArrayObject object = (IntArrayObject)stack.popObjectRef();
        object.getArray()[index] = value;
    }
}
