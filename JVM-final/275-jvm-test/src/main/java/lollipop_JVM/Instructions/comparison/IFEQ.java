package lollipop_JVM.Instructions.comparison;


public class IFEQ extends IFCOND {
    @Override
    public boolean condition(int value) {
        return value == 0;
    }
}
