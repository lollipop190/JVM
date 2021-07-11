package lollipop_JVM.Instructions.invoke;

import com.njuse.jvmfinal.memory.jclass.JClass;
import com.njuse.jvmfinal.memory.jclass.Method;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.Constant;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.ref.MethodRef;
import lollipop_JVM.Instructions.base.InstructionWithSixteenIndex;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.JObject;
import lollipop_JVM.runtimeDataArea.struct.Slot;
import lollipop_JVM.runtimeDataArea.struct.Vars;


public class INVOKE_SPECIAL extends InstructionWithSixteenIndex {
    @Override
    public void execute(StackFrame frame) {
        //noinspection Duplicates
        JClass currentClz = frame.getMethod().getClazz();
        Constant methodRef = currentClz.getRuntimeConstantPool().getConstant(super.index);
        assert methodRef instanceof MethodRef;
        Method method = ((MethodRef) methodRef).resolveMethodRef();

        JClass c;
        if (frame.getMethod().getClazz().isAccSuper()
                && !method.getName().equals("<init>")) {
            c = frame.getMethod().getClazz().getSuperClass();
        } else {
            c = method.getClazz();
        }
        Method toInvoke = ((MethodRef) methodRef).resolveMethodRef(c);

        Slot[] args = copyArguments(frame, method);


        StackFrame newFrame = new StackFrame(frame.getThreadStack(), toInvoke,
                toInvoke.getMaxStack(), toInvoke.getMaxLocal() + 1);
        Vars localVars = newFrame.getLocalVars();
        JObject thisRef = frame.getOperandStack().popObjectRef();
        Slot slot = new Slot();
        slot.setObject(thisRef);
        localVars.setSlot(0, slot);
        int argc = method.getArgc();
        for (int i = 1; i < args.length + 1; i++) {
            localVars.setSlot(i, args[argc - i]);
        }

        frame.getThreadStack().pushFrame(newFrame);
    }

    private Slot[] copyArguments(StackFrame frame, Method method) {
        int argc = method.getArgc();
        Slot[] argv = new Slot[argc];
        for (int i = 0; i < argc; i++) {
            argv[i] = frame.getOperandStack().popSlot();
        }
        return argv;
    }
}
