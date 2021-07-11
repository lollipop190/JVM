package lollipop_JVM.runtimeDataArea.struct.array;

import lollipop_JVM.runtimeDataArea.struct.ArrayObject;
import lollipop_JVM.runtimeDataArea.struct.JObject;

public class MultiArrayObject extends ArrayObject {
    private JObject[] array;

    public MultiArrayObject(int len,String type){
        super(len,type);
        array = new JObject[len];
    }

    public JObject[] getArray() {
        return array;
    }
}
