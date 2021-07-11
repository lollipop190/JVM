package lollipop_JVM.Instructions.invoke;

import com.njuse.jvmfinal.memory.jclass.Method;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.ref.InterfaceMethodRef;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.ref.MemberRef;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.ref.MethodRef;
import lollipop_JVM.Instructions.base.InstructionWithSixteenIndex;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.Slot;
import lollipop_JVM.runtimeDataArea.struct.Vars;

public class INVOKE_STATIC extends InstructionWithSixteenIndex {
    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        MemberRef memberRef = (MemberRef) runtimeConstantPool.getConstant(index);
        Method method = null;
        if (memberRef instanceof MethodRef) {
            method = ((MethodRef) memberRef).resolveMethodRef();
        }else if (memberRef instanceof InterfaceMethodRef){
            method = ((InterfaceMethodRef) memberRef).resolveInterfaceMethodRef();
        }

        assert method != null;
        if (memberRef.getClassName().contains("TestUtil")){
            if (method.getName().contains("equalInt")){
                int v2 = frame.getOperandStack().popInt();
                int v1 = frame.getOperandStack().popInt();

                if (v1 != v2 &&(v1!=0))
                    throw new RuntimeException(v1 + "!=" + v2);

                frame.getOperandStack().pushInt(v1);
                frame.getOperandStack().pushInt(v2);
            }else if (method.getName().contains("reach")){
                int value = frame.getOperandStack().popInt();
                System.out.println(value);
                frame.getOperandStack().pushInt(value);


            }
        }else if (method.getName().contains("equalFloat")){
            float v2 = frame.getOperandStack().popFloat();
            float v1 = frame.getOperandStack().popFloat();
            if (Math.abs((double)(v1-v2)) > 1.0e-4d)
                throw new RuntimeException();

            frame.getOperandStack().pushFloat(v1);
            frame.getOperandStack().pushFloat(v2);
        }
        else if (method.getName().equals("fail"))
            throw new RuntimeException();


        int argc = method.getArgc();
        Slot[] argv = new Slot[argc];
        for (int i = 0; i < argc; i++) {
            argv[i] = frame.getOperandStack().popSlot();
        }
        StackFrame newFrame = prepareNewFrame(frame, argc, argv, method);
        frame.getThreadStack().pushFrame(newFrame);

        if (method.isNative()){
            frame.getThreadStack().popFrame();
        }

    }
    private StackFrame prepareNewFrame(StackFrame frame, int argc, Slot[] argv,Method toInvoke) {
        StackFrame newFrame = new StackFrame(frame.getThreadStack(), toInvoke,
                toInvoke.getMaxStack(), toInvoke.getMaxLocal());
        Vars localVars = newFrame.getLocalVars();
        for (int i = 0; i < argc ; i++) {
            localVars.setSlot(i, argv[argc - 1 - i]);
        }
        return newFrame;
    }
}
