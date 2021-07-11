package lollipop_JVM.Instructions.control;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.ThreadStack;

public class IRETURN extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int value = operandStack.popInt();
        ThreadStack stack = frame.getThreadStack();
        stack.popFrame();
        stack.getTopFrame().getOperandStack().pushInt(value);

        // process pc
        frame.getThreadStack().getThread().getProgramCounter().backToLastFrame();
    }
}
