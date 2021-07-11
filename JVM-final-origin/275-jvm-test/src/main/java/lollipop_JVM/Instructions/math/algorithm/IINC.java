package lollipop_JVM.Instructions.math.algorithm;

import lollipop_JVM.Instructions.base.Instruction;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.Vars;

import java.nio.ByteBuffer;

public class IINC extends Instruction {
    private int index;
    private int increment;
    @Override
    public void execute(StackFrame frame) {

        Vars vars = frame.getLocalVars();
        int value = vars.getInt(index) + increment;
        vars.setInt(index,value);
    }

    @Override
    public void fetchOperands(ByteBuffer byteBuffer) {
        this.index = byteBuffer.get() & 255;
        this.increment = byteBuffer.get();
    }
}
