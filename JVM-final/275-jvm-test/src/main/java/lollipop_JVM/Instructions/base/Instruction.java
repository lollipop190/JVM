package lollipop_JVM.Instructions.base;

import lollipop_JVM.runtimeDataArea.StackFrame;

import java.nio.ByteBuffer;

public abstract class Instruction {
    public abstract void execute(StackFrame frame);
    public abstract void fetchOperands(ByteBuffer byteBuffer);
}
