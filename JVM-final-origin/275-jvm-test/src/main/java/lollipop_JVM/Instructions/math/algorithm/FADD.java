package lollipop_JVM.Instructions.math.algorithm;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class FADD extends InstructionWithNoOperands {


    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        float val2 = stack.popFloat();
        float val1 = stack.popFloat();
        float res = val1 + val2;
        stack.pushFloat(res);
    }
}
