package lollipop_JVM.Instructions.stack;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.Slot;

public class DUP extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot = stack.popSlot();
        stack.pushSlot(slot.clone());
        stack.pushSlot(slot.clone());
    }
}
