package com.njuse.seecjvm.execution;

import com.njuse.seecjvm.instructions.base.Instruction;
import com.njuse.seecjvm.memory.jclass.Method;
import com.njuse.seecjvm.runtime.JThread;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.vo.StateVO;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class Interpreter {
    private static ByteBuffer codeReader;
    public static int i = 0;
    public static ArrayList<StateVO> interpret(JThread thread) {
        initCodeReader(thread);
        return loop(thread);
    }

    /**
     * This method set the code reader according to topFrame
     * When topFrame changes, this method should be called
     */
    private static void initCodeReader(JThread thread) {
        byte[] code = thread.getTopFrame().getMethod().getCode();
        codeReader = ByteBuffer.wrap(code);
        int nextPC = thread.getTopFrame().getNextPC();
 //       System.out.println("第 " + (i++) + "次 " + nextPC);
        codeReader.position(nextPC);
    }

    private static ArrayList<StateVO> loop(JThread thread) {
        while (true) {
            StackFrame oriTop = thread.getTopFrame();
            //parse code attribute for VO
            Method method = oriTop.getMethod();
            if (!method.isParsed()) {
                method.parseCode();
            }
            //set the reader's position to nextPC
            codeReader.position(oriTop.getNextPC());
            System.out.println("up     "  + oriTop.getNextPC());
            //fetch and decode
            int opcode = codeReader.get() & 0xff;
            Instruction instruction = Decoder.decode(opcode);
            instruction.fetchOperands(codeReader);
            //set nextPC to reader's position
            int nextPC = codeReader.position();
            System.out.println("down   "+ nextPC);
            oriTop.setNextPC(nextPC);
            instruction.execute(oriTop);
            //check whether there's a new frame
            //and whether there's more frame to exec
            StackFrame newTop = thread.getTopFrame();
            //add
//            PrintInfo(oriTop, newTop, thread, instruction);

            if (newTop == null) {
                return null;
            }

            if (oriTop != newTop) {
                initCodeReader(thread);
            }
        }

    }


}
