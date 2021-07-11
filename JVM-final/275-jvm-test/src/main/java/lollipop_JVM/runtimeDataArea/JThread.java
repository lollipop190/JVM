package lollipop_JVM.runtimeDataArea;

public class JThread {
    private ThreadStack threadStack;
    private ProgramCounter programCounter = ProgramCounter.getInstance();
    public JThread(){
        this.threadStack = new ThreadStack(this);
    }
    public ThreadStack getThreadStack() {
        return threadStack;
    }

    public ProgramCounter getProgramCounter() {
        return programCounter;
    }
}
