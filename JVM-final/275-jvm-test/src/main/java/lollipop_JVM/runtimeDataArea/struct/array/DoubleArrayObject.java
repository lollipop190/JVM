package lollipop_JVM.runtimeDataArea.struct.array;

import lollipop_JVM.runtimeDataArea.struct.ArrayObject;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DoubleArrayObject extends ArrayObject {
    private double[] array;

    public DoubleArrayObject(int len, String type) {
        super(len, type);
        array = new double[len];
    }
}