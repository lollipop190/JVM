package lollipop_JVM.runtimeDataArea.struct.array;


import lollipop_JVM.runtimeDataArea.struct.ArrayObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortArrayObject extends ArrayObject {
    private short[] array;

    public ShortArrayObject(int len, String type) {
        super(len, type);
        array = new short[len];
    }
}