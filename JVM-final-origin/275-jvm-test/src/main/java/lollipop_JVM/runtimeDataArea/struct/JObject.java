package lollipop_JVM.runtimeDataArea.struct;

import com.njuse.jvmfinal.memory.jclass.JClass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JObject {
    protected JClass clazz;
    public JObject(){
    }

//    public boolean InstanceOf(JClass clazz){
//        return this.clazz
//    }
    public boolean isNull(){
        return clazz == null;
    }

    public boolean isInstanceOf(JClass clazz){

        return this.clazz.isAssignableFrom(clazz);
    }
}
