package lollipop_JVM.Instructions.control;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.ThreadStack;
import lollipop_JVM.runtimeDataArea.struct.JObject;

public class ARETURN extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        JObject object = operandStack.popObjectRef();
        ThreadStack stack = frame.getThreadStack();
        stack.popFrame();
        stack.getTopFrame().getOperandStack().pushObjectRef(object);

        // process pc
        frame.getThreadStack().getThread().getProgramCounter().backToLastFrame();

    }
}
