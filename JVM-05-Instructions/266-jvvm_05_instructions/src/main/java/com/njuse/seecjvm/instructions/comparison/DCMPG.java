package com.njuse.seecjvm.instructions.comparison;

import com.njuse.seecjvm.instructions.base.NoOperandsInstruction;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;
import org.jcp.xml.dsig.internal.dom.DOMUtils;

public class DCMPG extends NoOperandsInstruction {

    /**
     * TODO：实现这条指令
     */
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
            operandStack.pushInt(1);
    }
}
