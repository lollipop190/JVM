package lollipop_JVM.Instructions.references;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.ArrayObject;
import lollipop_JVM.runtimeDataArea.struct.JObject;

public class ARRAYLENGTH extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        JObject object = stack.popObjectRef();
        stack.pushInt(((ArrayObject)object).getLen());
    }
}
