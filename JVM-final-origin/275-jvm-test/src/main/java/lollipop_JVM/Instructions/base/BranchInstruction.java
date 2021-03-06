package lollipop_JVM.Instructions.base;

import java.nio.ByteBuffer;

public abstract class BranchInstruction extends Instruction {
    protected int offset;

    public BranchInstruction() {
    }

    public void fetchOperands(ByteBuffer reader) {
        this.offset = reader.getShort();
    }

}
