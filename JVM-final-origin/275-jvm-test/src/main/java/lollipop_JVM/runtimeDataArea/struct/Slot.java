package lollipop_JVM.runtimeDataArea.struct;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Slot {
    private Integer value;
    private JObject object;

    public Slot clone(){
        Slot newOne = new Slot();
        newOne.object = this.object;
        newOne.value = this.value;
        return newOne;
    }
}
