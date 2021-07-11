package lollipop_JVM.Instructions.load;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.array.MultiArrayObject;

public class AALOAD extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int index = stack.popInt();
        MultiArrayObject multiArrayObject = (MultiArrayObject)stack.popObjectRef();
        stack.pushObjectRef(multiArrayObject.getArray()[index]);

    }
}
