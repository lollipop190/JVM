package lollipop_JVM.runtimeDataArea.struct.array;

import lollipop_JVM.runtimeDataArea.struct.ArrayObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LongArrayObject extends ArrayObject {
    private long[] array;

    public LongArrayObject(int len, String type) {
        super(len, type);
        array = new long[len];
    }
}