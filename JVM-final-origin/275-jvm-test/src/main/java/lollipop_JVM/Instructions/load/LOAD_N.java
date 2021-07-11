package lollipop_JVM.Instructions.load;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;

public abstract class LOAD_N extends InstructionWithNoOperands {
    protected int index;
    private static int[] valid = {0, 1, 2, 3};

    public static void checkIndex(int i) {
        assert (i >= valid[0] && i <= valid[valid.length - 1]);
    }
}
