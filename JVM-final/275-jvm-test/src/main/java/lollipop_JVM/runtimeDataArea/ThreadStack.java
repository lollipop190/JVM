package lollipop_JVM.runtimeDataArea;

import java.util.EmptyStackException;
import java.util.Stack;

public class ThreadStack {
    private Stack<StackFrame> stack = new Stack<>();
    private int currentSize;
    private JThread thread;

    public JThread getThread() {
        return thread;
    }

    public ThreadStack(JThread thread){
        this.thread = thread;
    }
    public void pushFrame(StackFrame frame){
        int maxSize = 65536;
        if (this.currentSize >= maxSize) {
            throw new StackOverflowError();
        } else {
            this.stack.push(frame);
            this.currentSize ++;
        }
    }

    public void popFrame(){
        if (this.currentSize == 0) {
            throw new EmptyStackException();
        } else {
            this.stack.pop();
            this.currentSize --;
        }
    }

    public Stack<StackFrame> getStack() {
        return stack;
    }

    public StackFrame getTopFrame() {
        return this.currentSize == 0 ? null : this.stack.lastElement();
    }

}
