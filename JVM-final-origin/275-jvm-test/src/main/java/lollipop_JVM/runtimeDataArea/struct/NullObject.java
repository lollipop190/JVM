package lollipop_JVM.runtimeDataArea.struct;

import com.njuse.jvmfinal.memory.jclass.JClass;

public class NullObject extends JObject{
    public NullObject(JClass clazz) {
        this.clazz = clazz;
        assert clazz == null;
    }
}
