package lollipop_JVM.Instructions.constant;

import lollipop_JVM.Instructions.base.Instruction;
import lollipop_JVM.runtimeDataArea.StackFrame;

import java.nio.ByteBuffer;

public class SIPUSH extends Instruction {
    private short value;

    public void fetchOperands(ByteBuffer reader) {
        this.value = reader.getShort();
    }

    public void execute(StackFrame frame){
        frame.getOperandStack().pushInt(this.value);
    }

}
