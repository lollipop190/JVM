package lollipop_JVM.Instructions.base;

import java.nio.ByteBuffer;

public abstract class InstructionWithEightIndex extends Instruction {
    public int index;

    public void fetchOperands(ByteBuffer reader) {
        this.index = reader.get() & 255;
    }

}
