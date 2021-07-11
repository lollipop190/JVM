package lollipop_JVM.Instructions.references;


import com.njuse.jvmfinal.memory.jclass.JClass;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.ref.ClassRef;
import lollipop_JVM.Instructions.base.InstructionWithSixteenIndex;
import lollipop_JVM.memory.jclass.InitState;
import lollipop_JVM.runtimeDataArea.JHeap;
import lollipop_JVM.runtimeDataArea.OperandStack;
import lollipop_JVM.runtimeDataArea.ProgramCounter;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.NonArrayObject;

public class NEW extends InstructionWithSixteenIndex {
    /**
     * TODO 实现这条指令
     * 其中 对应的index已经读取好了
     */
    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        ClassRef classRef = (ClassRef)runtimeConstantPool.getConstant(index);
        OperandStack operandStack = frame.getOperandStack();
        try{
            JClass jClass = classRef.getResolvedClass();
            if (jClass.getInitState() == InitState.PREPARED) {
                ProgramCounter programCounter = ProgramCounter.getInstance();
                programCounter.setNextPC(programCounter.getNextPC() - 3);
                jClass.initClass(frame.getThreadStack(), jClass);
                return;
            }
            NonArrayObject newObj = new NonArrayObject(jClass);
            JHeap.getInstance().addObj(newObj);
            operandStack.pushObjectRef(newObj);
        }catch (ClassNotFoundException e ){
            e.printStackTrace();
        }
    }

}
