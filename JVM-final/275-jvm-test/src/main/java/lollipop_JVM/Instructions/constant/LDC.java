package lollipop_JVM.Instructions.constant;

import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.Constant;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.wrapper.FloatWrapper;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.wrapper.IntWrapper;
import lollipop_JVM.Instructions.base.InstructionWithEightIndex;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;

public class LDC extends InstructionWithEightIndex {
    @Override
    public void execute(StackFrame frame) {
        loadConstant(frame, index);
    }
    public static void loadConstant(StackFrame frame, int index) {
        OperandStack stack = frame.getOperandStack();
        Constant constant = frame.getMethod().getClazz().getRuntimeConstantPool().getConstant(index);
        if (constant instanceof IntWrapper) {
            stack.pushInt(((IntWrapper)constant).getValue());
        }
        else if (constant instanceof FloatWrapper) {
            stack.pushFloat(((FloatWrapper) constant).getValue());
        }

        else throw new ClassFormatError();

    }

}
