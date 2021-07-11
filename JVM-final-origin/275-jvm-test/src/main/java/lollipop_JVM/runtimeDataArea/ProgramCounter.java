package lollipop_JVM.runtimeDataArea;

import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Setter
@Getter
public class ProgramCounter {
    private static ProgramCounter programCounter = new ProgramCounter();
 //   private boolean isInClInit = false;
    private int nextPC = 0;
    private Stack<Integer> savedPC = new Stack<>();

    private ProgramCounter(){}
    public static ProgramCounter getInstance() {
        return programCounter;
    }

    public void resetPC(){
        setNextPC(0);
    }

    public void savePC(){
        this.savedPC.push(this.nextPC);
    }

    public void resetAll(){
        programCounter.setNextPC(0);
        programCounter.setSavedPC(new Stack<>());
    }
    public void backToLastFrame(){
        int formerNextPC = getSavedPC().pop();
        setNextPC(formerNextPC);
    }

}
