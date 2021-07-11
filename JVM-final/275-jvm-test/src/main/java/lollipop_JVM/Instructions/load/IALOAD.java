package lollipop_JVM.Instructions.load;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.array.IntArrayObject;

public class IALOAD extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        IntArrayObject object = (IntArrayObject)stack.popObjectRef();
        stack.pushInt(object.getArray()[index]);
    }
}
