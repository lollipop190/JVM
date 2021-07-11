package lollipop_JVM.Instructions.references;


import com.njuse.jvmfinal.memory.jclass.Field;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.ref.FieldRef;
import lollipop_JVM.Instructions.base.InstructionWithSixteenIndex;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.NonArrayObject;
import lollipop_JVM.runtimeDataArea.struct.Vars;

public class GETFIELD extends InstructionWithSixteenIndex {

    /**
     * TODO 实现这条指令
     * 其中 对应的index已经读取好了
     */
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        NonArrayObject nonArrayObject =(NonArrayObject) stack.popObjectRef();
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef) runtimeConstantPool.getConstant(index);
        try{
        Field field = fieldRef.getResolvedFieldRef();
        int slotID = field.getSlotID();
        Vars fields = nonArrayObject.getFieldsOfObj();
            switch (field.getDescriptor().charAt(0)) {
                case 'Z':
                case 'B':
                case 'C':
                case 'S':
                case 'I':
                    stack.pushInt(fields.getInt(slotID));
                    break;
                case 'F':
                    stack.pushFloat(fields.getFloat(slotID));
                    break;
                case 'J':
                    stack.pushLong(fields.getLong(slotID));
                    break;
                case 'D':
                    stack.pushDouble(fields.getDouble(slotID));
                    break;
                case 'L':
                case '[':
                    stack.pushObjectRef(fields.getObjectRef(slotID));
                    break;
                default:
            }

    }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
