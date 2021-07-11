package lollipop_JVM.Instructions.references;


import com.njuse.jvmfinal.memory.jclass.Field;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.ref.FieldRef;
import lollipop_JVM.Instructions.base.InstructionWithSixteenIndex;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.JObject;
import lollipop_JVM.runtimeDataArea.struct.NonArrayObject;
import lollipop_JVM.runtimeDataArea.struct.Vars;
public class PUTFIELD extends InstructionWithSixteenIndex {
    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef)runtimeConstantPool.getConstant(index);
        OperandStack stack = frame.getOperandStack();
        try {
            Field field = fieldRef.getResolvedFieldRef();
            NonArrayObject nonArrayObject;
            int slotID = field.getSlotID();
            Vars fields;
            switch (field.getDescriptor().charAt(0)) {
                case 'Z':
                case 'B':
                case 'C':
                case 'S':
                case 'I':
                    int i = stack.popInt();
                    nonArrayObject = (NonArrayObject)stack.popObjectRef();
                    fields = nonArrayObject.getFieldsOfObj();
                    fields.setInt(slotID, i);
                    break;
                case 'F':
                    float f = stack.popFloat();
                    nonArrayObject = (NonArrayObject)stack.popObjectRef();
                    fields = nonArrayObject.getFieldsOfObj();
                    fields.setFloat(slotID, f);
                    break;
                case 'J':
                    long l = stack.popLong();
                    nonArrayObject = (NonArrayObject)stack.popObjectRef();
                    fields = nonArrayObject.getFieldsOfObj();
                    fields.setLong(slotID,l);
                    break;
                case 'D':
                    double d = stack.popDouble();
                    nonArrayObject = (NonArrayObject)stack.popObjectRef();
                    fields = nonArrayObject.getFieldsOfObj();
                    fields.setDouble(slotID, d);
                    break;
                case 'L':
                case '[':
                    JObject jObject = stack.popObjectRef();
                    nonArrayObject = (NonArrayObject)stack.popObjectRef();
                    fields = nonArrayObject.getFieldsOfObj();
                    fields.setObjectRef(slotID, jObject);
                    break;
                default:
            }

        }catch (ClassNotFoundException e ){
            e.printStackTrace();
        }

    }

}
