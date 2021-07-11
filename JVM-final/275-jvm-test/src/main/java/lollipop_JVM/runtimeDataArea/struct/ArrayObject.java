package lollipop_JVM.runtimeDataArea.struct;

public class ArrayObject extends JObject {
    protected int len;
    protected String type;
    public ArrayObject(int len, String type) {
        this.len = len;
        this.type = type;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    public int getLen() {
        return len;
    }
}
