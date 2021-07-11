package lollipop_JVM.runtimeDataArea.struct;

import com.njuse.jvmfinal.memory.jclass.Field;
import com.njuse.jvmfinal.memory.jclass.JClass;

public class NonArrayObject extends JObject {
    private Vars fieldsOfObj;
    public NonArrayObject(JClass clazz) {
        this.clazz = clazz;
        assert clazz != null;
        fieldsOfObj = new Vars(clazz.getInstanceSlotCount());
        initDefaultValue(clazz);
    }

    private void initDefaultValue(JClass clazz){
        do {
            Field[] fields = clazz.getFields();
            for (Field field: fields) {
                if (!field.isStatic()){
                    switch (field.getDescriptor().charAt(0)){
                        case 'Z':
                        case 'B':
                        case 'C':
                        case 'S':
                        case 'I':
                            this.fieldsOfObj.setInt(field.getSlotID(), 0);
                            break;
                        case 'F':
                            this.fieldsOfObj.setFloat(field.getSlotID(), 0);
                            break;
                        case 'J':
                            this.fieldsOfObj.setLong(field.getSlotID(), 0);
                            break;
                        case 'D':
                            this.fieldsOfObj.setDouble(field.getSlotID(), 0);
                            break;
                        default:
                            this.fieldsOfObj.setObjectRef(field.getSlotID(), new NullObject(null));
                            break;
                    }
                }

            }
            clazz = clazz.getSuperClass();
        } while (clazz != null);
    }

    public Vars getFieldsOfObj() {
        return fieldsOfObj;
    }
}
