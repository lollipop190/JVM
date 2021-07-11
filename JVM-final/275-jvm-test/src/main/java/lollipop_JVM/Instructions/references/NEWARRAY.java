package lollipop_JVM.Instructions.references;

import com.njuse.jvmfinal.memory.jclass.JClass;
import lollipop_JVM.Instructions.base.InstructionWithEightIndex;
import lollipop_JVM.classLoader.ClassLoader;
import lollipop_JVM.runtimeDataArea.JHeap;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.ArrayObject;

public class NEWARRAY extends InstructionWithEightIndex {

    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        ClassLoader classLoader = ClassLoader.getInstance();
        int count = stack.popInt();
        int atype = super.index;

        JClass arrayClass = classLoader.loadClass(""+ atype, frame.getMethod().getClazz().getClassLoader());
        ArrayObject arrayObject = arrayClass.getArrayObject(count);
        arrayObject.setClazz(arrayClass);
        JHeap.getInstance().addObj(arrayObject);
        stack.pushObjectRef(arrayObject);
    }

}
