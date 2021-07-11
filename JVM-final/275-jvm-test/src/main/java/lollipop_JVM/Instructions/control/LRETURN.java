package lollipop_JVM.Instructions.control;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.ThreadStack;

public class LRETURN extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long value = operandStack.popLong();
        ThreadStack stack = frame.getThreadStack();
        stack.popFrame();
        stack.getTopFrame().getOperandStack().pushLong(value);

        // process pc
        frame.getThreadStack().getThread().getProgramCounter().backToLastFrame();

    }
}