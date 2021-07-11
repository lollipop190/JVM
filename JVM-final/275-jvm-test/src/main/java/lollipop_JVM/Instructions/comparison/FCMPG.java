package lollipop_JVM.Instructions.comparison;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class FCMPG extends InstructionWithNoOperands {
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        float v2 = stack.popFloat();
        float v1 = stack.popFloat();
        if (v1 > v2)
            stack.pushInt(1);
        else if (v1 ==v2)
            stack.pushInt(0);
        else if (v1 < v2)
            stack.pushInt(-1);
        else if (Float.isNaN(v1) || Float.isNaN(v2))
            stack.pushInt(1);

    }
}
