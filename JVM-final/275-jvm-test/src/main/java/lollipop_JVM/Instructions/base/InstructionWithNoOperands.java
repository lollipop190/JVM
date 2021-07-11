package lollipop_JVM.Instructions.base;

import java.nio.ByteBuffer;

public abstract class InstructionWithNoOperands extends Instruction {
    public void fetchOperands(ByteBuffer reader) {
    }
}
