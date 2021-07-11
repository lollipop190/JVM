package lollipop_JVM.Instructions.load;

import lollipop_JVM.Instructions.base.InstructionWithEightIndex;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.Vars;

public class DLOAD extends InstructionWithEightIndex {

    /**
     * TODO：实现这条指令
     * 其中成员index是这条指令的参数，已经读取好了
     */
    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Vars vars = frame.getLocalVars();
        operandStack.pushDouble(vars.getDouble(index));
    }
}
