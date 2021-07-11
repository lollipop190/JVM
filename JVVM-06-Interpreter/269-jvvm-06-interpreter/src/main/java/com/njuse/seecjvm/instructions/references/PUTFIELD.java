package com.njuse.seecjvm.instructions.references;

import com.njuse.seecjvm.instructions.base.Index16Instruction;
import com.njuse.seecjvm.memory.jclass.Field;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.FieldRef;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.Vars;
import com.njuse.seecjvm.runtime.struct.JObject;
import com.njuse.seecjvm.runtime.struct.NonArrayObject;


public class PUTFIELD extends Index16Instruction {
    /**
     * TODO 实现这条指令
     * 其中 对应的index已经读取好了
     */
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
                    fields = nonArrayObject.getFields();
                    fields.setInt(slotID, i);
                    break;
                case 'F':
                    float f = stack.popFloat();
                    nonArrayObject = (NonArrayObject)stack.popObjectRef();
                    fields = nonArrayObject.getFields();
                    fields.setFloat(slotID, f);
                    break;
                case 'J':
                    long l = stack.popLong();
                    nonArrayObject = (NonArrayObject)stack.popObjectRef();
                    fields = nonArrayObject.getFields();
                    fields.setLong(slotID,l);
                    break;
                case 'D':
                    double d = stack.popDouble();
                    nonArrayObject = (NonArrayObject)stack.popObjectRef();
                    fields = nonArrayObject.getFields();
                    fields.setDouble(slotID, d);
                    break;
                case 'L':
                    JObject jObject = stack.popObjectRef();
                    nonArrayObject = (NonArrayObject)stack.popObjectRef();
                    fields = nonArrayObject.getFields();
                    fields.setObjectRef(slotID, jObject);
                    break;
                default:
            }



        }catch (ClassNotFoundException e ){
            e.printStackTrace();
        }

    }

}
