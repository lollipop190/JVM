package com.njuse.seecjvm.instructions.load;

import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.Vars;

public class DLOAD_N extends LOAD_N {
    public DLOAD_N(int index) {
        checkIndex(index);
        this.index = index;
    }
    /**
     * TODO：实现这条指令
     * 其中成员index是这条指令的参数，已经读取好了
     */
    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Vars vars = frame.getLocalVars();
        double ret = vars.getDouble(index);
        operandStack.pushDouble(ret);
    }
}