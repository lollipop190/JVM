package lollipop_JVM.runtimeDataArea;

import com.njuse.jvmfinal.memory.jclass.Method;
import lollipop_JVM.runtimeDataArea.struct.Vars;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StackFrame {
    private OperandStack operandStack;
    private Vars localVars;
    private Method method;
    private ThreadStack threadStack;

    public StackFrame( ThreadStack threadStack, Method method, int maxStackSize, int maxVarSize) {
        this.method = method;
        this.threadStack = threadStack;
        this.operandStack = new OperandStack(maxStackSize);
        this.localVars = new Vars(maxVarSize);
    }
}
