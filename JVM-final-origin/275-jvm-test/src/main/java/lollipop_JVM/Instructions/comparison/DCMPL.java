package lollipop_JVM.Instructions.comparison;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class DCMPL extends InstructionWithNoOperands  {


    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double second = operandStack.popDouble();
        double first = operandStack.popDouble();
        if (first > second)
            operandStack.pushInt(1);
        else if (first == second)
            operandStack.pushInt(0);
        else if (first < second)
            operandStack.pushInt(-1);
        else if (Double.isNaN(first) || Double.isNaN(second))
            operandStack.pushInt(-1);
    }
}
