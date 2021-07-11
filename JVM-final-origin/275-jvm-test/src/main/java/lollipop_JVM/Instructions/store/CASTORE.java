package lollipop_JVM.Instructions.store;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.array.CharArrayObject;

public class CASTORE extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        char value = (char)stack.popInt();
        int index = stack.popInt();
        CharArrayObject charArrayObject = (CharArrayObject)stack.popObjectRef();
        charArrayObject.getArray()[index] = value;
    }
}
