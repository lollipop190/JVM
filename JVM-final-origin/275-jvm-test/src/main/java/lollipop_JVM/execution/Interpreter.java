package lollipop_JVM.execution;

import com.njuse.jvmfinal.util.ColorUtil;
import lollipop_JVM.Instructions.base.Instruction;
import lollipop_JVM.runtimeDataArea.JThread;
import lollipop_JVM.runtimeDataArea.ProgramCounter;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.struct.NonArrayObject;
import lollipop_JVM.runtimeDataArea.struct.Slot;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Interpreter {
    private static ByteBuffer codeReader;
    public static int counter = 0;
    public static void interpret(JThread thread) {
        initCodeReader(thread);
        loop(thread);
    }

    /**
     * This method set the code reader according to topFrame
     * When topFrame changes, this method should be called
     */
    private static void initCodeReader(JThread thread){
        byte[] code = thread.getThreadStack().getTopFrame().getMethod().getCode();
        assert code != null;
        codeReader = ByteBuffer.wrap(code);
        int nextPC = thread.getProgramCounter().getNextPC();
        codeReader.position(nextPC);
    }

    private static void loop(JThread thread) {
        while (true) {
            StackFrame oriTop = thread.getThreadStack().getTopFrame();
            ProgramCounter pcRegister = thread.getProgramCounter();
            //parse code attribute for VO
//            Method method = oriTop.getMethod();
//            if (!method.isParsed()) {
//                method.parseCode();
//            }
            //set the reader's position to nextPC
            codeReader.position(pcRegister.getNextPC());
            //fetch and decode
            int opcode = codeReader.get() & 0xff;
            Instruction instruction = Decoder.decode(opcode);
            instruction.fetchOperands(codeReader);
            //set nextPC to reader's position
            int nextPC = codeReader.position();
            pcRegister.setNextPC(nextPC);
            instruction.execute(oriTop);
            //check whether there's a new frame
            //and whether there's more frame to exec
            StackFrame newTop = thread.getThreadStack().getTopFrame();
            //todo
            // add
//            try{
//            PrintInfo(oriTop, newTop, thread, instruction);}
//            catch (NullPointerException e){
//
//            }
            counter++;
            if (newTop == null) {
                return;
            }

            if (oriTop != newTop) {
//                if (pcRegister.isInClInit()){
//                    pcRegister.resetAll();
//                }
                if (thread.getThreadStack().getStack().contains(oriTop)){
                pcRegister.savePC();
                pcRegister.resetPC();
                }
                initCodeReader(thread);
            }
        }

    }

    private static void PrintInfo(StackFrame originTop, StackFrame newTop, JThread thread,Instruction instruction){
        String langSpace = "     ";
        String nameOfInstruction = instruction.getClass().getSimpleName();
        System.err.println("Instruction : " + nameOfInstruction);
        ColorUtil.printYellow("Methods in current thread: ");
        thread.getThreadStack().getStack().forEach((frame -> {
            System.err.println(langSpace + frame.getMethod().getClazz().getName() + " : " + frame.getMethod().getName());
        }));
        System.err.println();
        ColorUtil.printYellow(langSpace + "Contents in operand stack:");
        printVars(originTop.getOperandStack().getSlots());
        System.err.println();
        ColorUtil.printYellow(langSpace + "Contents in local var:");
        printVars(originTop.getLocalVars().getVarSlots());
        System.err.println();
        if (originTop == newTop) {
            ColorUtil.printCyan("Next frame doesn't change.Method is still " + originTop.getMethod().getClazz().getName() + " : " + originTop.getMethod().getName());
        } else {
            ColorUtil.printRed("Next frame changed.Method is " + newTop.getMethod().getClazz().getName() + " : " + newTop.getMethod().getName());
        }

        ColorUtil.printBlue("----------------------------------------------------------------------");
    }

    private static void printVars(Slot[] vars) {
        String langSpace = "    ";
        Arrays.stream(vars).forEach((s) -> {
            assert s != null;

            if (s.getValue() != null) {
                System.err.println(langSpace + "value = " + s.getValue());
            } else if (s.getObject() != null) {
                if (s.getObject() instanceof NonArrayObject) {
                    System.err.println(langSpace + "Object ref to -> " + s.getObject().getClazz().getName());
                }
//                else if (s.getObject() instanceof ArrayObject) {
//                    System.out.println(langSpace + "Object ref to -> " + ((ArrayObject)s.getObject()).getType());
//                }
            }

        });
    }

}