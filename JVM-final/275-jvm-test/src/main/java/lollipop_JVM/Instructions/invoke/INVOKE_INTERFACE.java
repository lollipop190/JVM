package lollipop_JVM.Instructions.invoke;

import com.njuse.jvmfinal.memory.jclass.JClass;
import com.njuse.jvmfinal.memory.jclass.Method;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.Constant;
import com.njuse.jvmfinal.memory.jclass.runtimeConstantPool.constant.ref.InterfaceMethodRef;
import lollipop_JVM.Instructions.base.InstructionWithSixteenIndex;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.JObject;
import lollipop_JVM.runtimeDataArea.struct.Slot;
import lollipop_JVM.runtimeDataArea.struct.Vars;

import java.nio.ByteBuffer;


public class INVOKE_INTERFACE extends InstructionWithSixteenIndex {
    @Override
    public void fetchOperands(ByteBuffer reader) {
        super.fetchOperands(reader);
        reader.get();
        reader.get();
    }

    @Override
    public void execute(StackFrame frame) {

         Constant methodCons = frame.getMethod().getClazz().getRuntimeConstantPool().getConstant(index);
         assert methodCons instanceof InterfaceMethodRef;
         InterfaceMethodRef methodRef = (InterfaceMethodRef)methodCons;
         Method method = methodRef.resolveInterfaceMethodRef();

        int argc = method.getArgc();
        Slot[] argv = new Slot[argc];
        for (int i = 0; i < argc; i++) {
            argv[i] = frame.getOperandStack().popSlot();
        }

        JObject objectRef = frame.getOperandStack().popObjectRef();
        JClass clazz = objectRef.getClazz();
        Method toInvoke = methodRef.resolveInterfaceMethodRef(clazz);
        StackFrame newFrame = prepareNewFrame(frame, argc, argv, objectRef, toInvoke);
        frame.getThreadStack().pushFrame(newFrame);
        if (method.isNative()){
            frame.getThreadStack().popFrame();
        }
    }
    private StackFrame prepareNewFrame(StackFrame frame, int argc, Slot[] argv, JObject objectRef, Method toInvoke) {
        StackFrame newFrame = new StackFrame(frame.getThreadStack(), toInvoke,
                toInvoke.getMaxStack(), toInvoke.getMaxLocal() + 1);
        Vars localVars = newFrame.getLocalVars();
        Slot thisSlot = new Slot();
        thisSlot.setObject(objectRef);
        localVars.setSlot(0, thisSlot);
        for (int i = 1; i < argc + 1; i++) {
            localVars.setSlot(i, argv[argc - i]);
        }
        return newFrame;
    }


}
