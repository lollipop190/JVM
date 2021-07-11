package lollipop_JVM.Instructions.references;

import com.njuse.jvmfinal.memory.jclass.Field;
import com.njuse.jvmfinal.memory.jclass.JClass;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.ref.FieldRef;
import lollipop_JVM.Instructions.base.InstructionWithSixteenIndex;
import lollipop_JVM.memory.jclass.InitState;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.ProgramCounter;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.Vars;

public class PUTSTATIC extends InstructionWithSixteenIndex {

    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef)runtimeConstantPool.getConstant(index);
        try{
            Field field = fieldRef.getResolvedFieldRef();
            JClass targetClazz = field.getClazz();
            if (targetClazz.getInitState() == InitState.PREPARED) {
                ProgramCounter programCounter = ProgramCounter.getInstance();
                programCounter.setNextPC(programCounter.getNextPC() - 3);
                targetClazz.initClass(frame.getThreadStack(), targetClazz);
                return;
            }

            Vars staticVars = targetClazz.getStaticVars();
            OperandStack stack = frame.getOperandStack();
            int slotID = field.getSlotID();
            String descriptor = field.getDescriptor();
            switch (descriptor.charAt(0)) {
                case 'Z':
                case 'B':
                case 'C':
                case 'S':
                case 'I':
                    staticVars.setInt(slotID, stack.popInt());
                    break;
                case 'F':
                    staticVars.setFloat(slotID, stack.popFloat());
                    break;
                case 'J':
                    staticVars.setLong(slotID, stack.popLong());
                    break;
                case 'D':
                    staticVars.setDouble(slotID, stack.popDouble());
                    break;
                case 'L':
                case '[':
                    staticVars.setObjectRef(slotID, stack.popObjectRef());
                    break;
                default:
                    break;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
