package lollipop_JVM.Instructions.base;

import java.nio.ByteBuffer;

public abstract class InstructionWithSixteenIndex extends Instruction {
    public int index;

    public void fetchOperands(ByteBuffer reader) {
        this.index = reader.getShort() & '\uffff';
    }

}
