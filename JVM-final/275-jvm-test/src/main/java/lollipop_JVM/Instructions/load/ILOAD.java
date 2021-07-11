package lollipop_JVM.Instructions.load;

import lollipop_JVM.Instructions.base.InstructionWithEightIndex;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.Vars;

public class ILOAD extends InstructionWithEightIndex {

    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Vars vars = frame.getLocalVars();
        operandStack.pushInt(vars.getInt(index));
    }
}
