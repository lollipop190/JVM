package lollipop_JVM.Instructions.constant;

import lollipop_JVM.Instructions.base.Instruction;
import lollipop_JVM.runtimeDataArea.StackFrame;

import java.nio.ByteBuffer;

public class BIPUSH extends Instruction {
    private byte value;


    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushInt(this.value);
    }

    @Override
    public void fetchOperands(ByteBuffer byteBuffer) {
        this.value = byteBuffer.get();
    }
}
