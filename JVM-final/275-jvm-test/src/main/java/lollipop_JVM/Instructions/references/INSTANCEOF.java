package lollipop_JVM.Instructions.references;

import com.njuse.jvmfinal.memory.jclass.JClass;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.ref.ClassRef;
import lollipop_JVM.Instructions.base.InstructionWithSixteenIndex;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.JObject;

public class INSTANCEOF extends InstructionWithSixteenIndex {

    @Override
    public void execute(StackFrame frame) {

        OperandStack stack = frame.getOperandStack();
        JObject object = stack.popObjectRef();
        if (object.isNull()){
            stack.pushInt(0);
        }else{
            ClassRef classRef = (ClassRef) frame.getMethod().getClazz().getRuntimeConstantPool().getConstant(this.index);
            try {
                JClass clazz = classRef.getResolvedClass();
                if (object.isInstanceOf(clazz)){
                    stack.pushInt(1);
                }else
                    stack.pushInt(0);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
