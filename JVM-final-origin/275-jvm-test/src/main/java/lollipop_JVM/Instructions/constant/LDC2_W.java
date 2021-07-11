package lollipop_JVM.Instructions.constant;

import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.Constant;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.wrapper.DoubleWrapper;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.wrapper.LongWrapper;
import lollipop_JVM.Instructions.base.InstructionWithSixteenIndex;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class LDC2_W extends InstructionWithSixteenIndex {
    @Override
    public void execute(StackFrame frame) {
        loadConstant(frame, index);
    }
    public static void loadConstant(StackFrame frame, int index) {
        Constant constant = frame.getMethod().getClazz().getRuntimeConstantPool().getConstant(index);
        OperandStack stack = frame.getOperandStack();
        if (constant instanceof LongWrapper) {
            stack.pushLong(((LongWrapper)constant).getValue());
        }
        else if (constant instanceof DoubleWrapper) {
            stack.pushDouble(((DoubleWrapper) constant).getValue());
        }

        else throw new ClassFormatError();

    }
}
