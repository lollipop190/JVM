package cases.hard;


interface InterfaceInst {
}

class ChildHardObjectInst extends DarkHardObjectInst implements InterfaceInst {
    ChildHardObjectInst() {
    }
}

public class DarkHardObjectInst {
    private static final int FINAL_A = 2;
    private static final int FINAL_B = 4;
    public static int staticC = 8;
    public int D;

    public DarkHardObjectInst() {
    }

    public static void main(String[] args) {
        TestUtil.reach(2);
        TestUtil.reach(4);
        new DarkHardObjectInst();
        TestUtil.reach(staticC);
        staticC = 16;
        TestUtil.reach(staticC);
        DarkHardObjectInst tmp = new DarkHardObjectInst();
        TestUtil.reach(tmp.D);
        tmp.D = 32;
        TestUtil.reach(tmp.D);
        ChildHardObjectInst child = new ChildHardObjectInst();
        int num = 0;
        if (child instanceof DarkHardObjectInst) {
            num = 1;
        }

        TestUtil.reach(num);
        if (child instanceof InterfaceInst) {
            num = 2;
        }

        TestUtil.reach(num);
        int[] array = new int[10];
        if (array instanceof Object) {
            num = 3;
        }

        TestUtil.reach(num);
        if (array instanceof Cloneable) {
            num = 4;
        }

        TestUtil.reach(num);
    }
}

