package lollipop_JVM.Instructions.store;

import lollipop_JVM.Instructions.base.InstructionWithNoOperands;

public abstract class STORE_N extends InstructionWithNoOperands {
    protected int index;
    private static int[] valid = {0, 1, 2, 3};

    public static void checkIndex(int i) {
        assert (i >= valid[0] && i <= valid[valid.length - 1]);
    }

    @Override
    public String toString() {
        String suffix = index + "";
        return this.getClass().getSimpleName().replace("N", suffix);
    }
}

