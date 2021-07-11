package lollipop_JVM.execution;


import lollipop_JVM.Instructions.base.Instruction;
import lollipop_JVM.Instructions.base.OpCode;
import lollipop_JVM.Instructions.comparison.*;
import lollipop_JVM.Instructions.constant.*;
import lollipop_JVM.Instructions.control.*;
import lollipop_JVM.Instructions.conversion.*;
import lollipop_JVM.Instructions.invoke.INVOKE_INTERFACE;
import lollipop_JVM.Instructions.invoke.INVOKE_SPECIAL;
import lollipop_JVM.Instructions.invoke.INVOKE_STATIC;
import lollipop_JVM.Instructions.invoke.INVOKE_VIRTUAL;
import lollipop_JVM.Instructions.load.*;
import lollipop_JVM.Instructions.math.algorithm.*;
import lollipop_JVM.Instructions.references.*;
import lollipop_JVM.Instructions.stack.DUP;
import lollipop_JVM.Instructions.stack.POP;
import lollipop_JVM.Instructions.store.*;

import java.util.HashMap;

public class Decoder {
    private static HashMap<Integer, Instruction> opMap;

    static {
        opMap = new HashMap<>();
        opMap.put(OpCode.NOP, new NOP());
        opMap.put(OpCode.ACONST_NULL, new ACONST_NULL());
        opMap.put(OpCode.ICONST_M1, new ICONST_N(-1));
        opMap.put(OpCode.ICONST_0, new ICONST_N(0));
        opMap.put(OpCode.ICONST_1, new ICONST_N(1));
        opMap.put(OpCode.ICONST_2, new ICONST_N(2));
        opMap.put(OpCode.ICONST_3, new ICONST_N(3));
        opMap.put(OpCode.ICONST_4, new ICONST_N(4));
        opMap.put(OpCode.ICONST_5, new ICONST_N(5));
        opMap.put(OpCode.DCONST_0, new DCONST_0());
        opMap.put(OpCode.DCONST_1, new DCONST_1());
        opMap.put(OpCode.FCONST_0, new FCONST_0());
        opMap.put(OpCode.FCONST_1, new FCONST_1());
        opMap.put(OpCode.FCONST_2, new FCONST_2());
        opMap.put(OpCode.LCONST_0, new LCONST_0());
        opMap.put(OpCode.LCONST_1, new LCONST_1());
        opMap.put(OpCode.BIPUSH, new BIPUSH());
        opMap.put(OpCode.SIPUSH, new SIPUSH());
        opMap.put(OpCode.LDC, new LDC());
        opMap.put(OpCode.LDC_W, new LDC_W());
        opMap.put(OpCode.LDC2_W, new LDC2_W());
        opMap.put(OpCode.ILOAD, new ILOAD());
        opMap.put(OpCode.POP, new POP());
        opMap.put(OpCode.DUP, new DUP());
        opMap.put(OpCode.INSTANCEOF, new INSTANCEOF());
        opMap.put(OpCode.DLOAD, new DLOAD());
        opMap.put(OpCode.DLOAD_0, new DLOAD_N(0));
        opMap.put(OpCode.DLOAD_1, new DLOAD_N(1));
        opMap.put(OpCode.DLOAD_2, new DLOAD_N(2));
        opMap.put(OpCode.DLOAD_3, new DLOAD_N(3));
        opMap.put(OpCode.FLOAD, new FLOAD());
        opMap.put(OpCode.FLOAD_0 , new FLOAD_N(0));
        opMap.put(OpCode.FLOAD_1 , new FLOAD_N(1));
        opMap.put(OpCode.FLOAD_2 , new FLOAD_N(2));
        opMap.put(OpCode.FLOAD_3 , new FLOAD_N(3));
        opMap.put(OpCode.ILOAD_0, new ILOAD_N(0));
        opMap.put(OpCode.ILOAD_1, new ILOAD_N(1));
        opMap.put(OpCode.ILOAD_2, new ILOAD_N(2));
        opMap.put(OpCode.ILOAD_3, new ILOAD_N(3));
        opMap.put(OpCode.LLOAD, new LLOAD());
        opMap.put(OpCode.LLOAD_0, new LLOAD_N(0));
        opMap.put(OpCode.LLOAD_1, new LLOAD_N(1));
        opMap.put(OpCode.LLOAD_2, new LLOAD_N(2));
        opMap.put(OpCode.LLOAD_3, new LLOAD_N(3));
        opMap.put(OpCode.ALOAD, new ALOAD());
        opMap.put(OpCode.ALOAD_0, new ALOAD_N(0));
        opMap.put(OpCode.ALOAD_1, new ALOAD_N(1));
        opMap.put(OpCode.ALOAD_2, new ALOAD_N(2));
        opMap.put(OpCode.ALOAD_3, new ALOAD_N(3));
        opMap.put(OpCode.IALOAD, new IALOAD());
        opMap.put(OpCode.ASTORE, new ASTORE());
        opMap.put(OpCode.ISTORE, new ISTORE());
        opMap.put(OpCode.ISTORE_0, new ISTORE_N(0));
        opMap.put(OpCode.ISTORE_1, new ISTORE_N(1));
        opMap.put(OpCode.ISTORE_2, new ISTORE_N(2));
        opMap.put(OpCode.ISTORE_3, new ISTORE_N(3));
        opMap.put(OpCode.LSTORE, new LSTORE());
        opMap.put(OpCode.DSTORE, new DSTORE());
        opMap.put(OpCode.DSTORE_0, new DSTORE_N(0));
        opMap.put(OpCode.DSTORE_1, new DSTORE_N(1));
        opMap.put(OpCode.DSTORE_2, new DSTORE_N(2));
        opMap.put(OpCode.DSTORE_3, new DSTORE_N(3));
        opMap.put(OpCode.ASTORE_0, new ASTORE_N(0));
        opMap.put(OpCode.ASTORE_1, new ASTORE_N(1));
        opMap.put(OpCode.ASTORE_2, new ASTORE_N(2));
        opMap.put(OpCode.ASTORE_3, new ASTORE_N(3));
        opMap.put(OpCode.IASTORE, new IASTORE());
        opMap.put(OpCode.FSTORE, new FSTORE());
        opMap.put(OpCode.GOTO_, new GOTO());
        opMap.put(OpCode.IRETURN, new IRETURN());
        opMap.put(OpCode.LRETURN, new LRETURN());
        opMap.put(OpCode.FRETURN, new FRETURN());
        opMap.put(OpCode.DRETURN, new DRETURN());
        opMap.put(OpCode.ARETURN, new ARETURN());
        opMap.put(OpCode.RETURN, new RETURN());
        opMap.put(OpCode.GETSTATIC, new GETSTATIC());
        opMap.put(OpCode.PUTSTATIC, new PUTSTATIC());
        opMap.put(OpCode.GETFIELD, new GETFIELD());
        opMap.put(OpCode.PUTFIELD, new PUTFIELD());
        opMap.put(OpCode.INVOKEVIRTUAL, new INVOKE_VIRTUAL());
        opMap.put(OpCode.INVOKESPECIAL, new INVOKE_SPECIAL());
        opMap.put(OpCode.INVOKESTATIC, new INVOKE_STATIC());
        opMap.put(OpCode.INVOKEINTERFACE, new INVOKE_INTERFACE());
        opMap.put(OpCode.NEW_, new NEW());
        opMap.put(OpCode.IFNE, new IFNE());
        opMap.put(OpCode.IFLT, new IFLT());
        opMap.put(OpCode.IFGE, new IFGE());
        opMap.put(OpCode.IFGT, new IFGT());
        opMap.put(OpCode.IFLE, new IFLE());
        opMap.put(OpCode.IFEQ, new IFEQ());
        opMap.put(OpCode.LCMP, new LCMP());
        opMap.put(OpCode.IFNULL, new IFNULL());
        opMap.put(OpCode.IFNONNULL, new IFNONNULL());
        opMap.put(OpCode.DCMPG, new DCMPG());
        opMap.put(OpCode.DCMPL, new DCMPL());
        opMap.put(OpCode.FCMPG, new FCMPG());
        opMap.put(OpCode.FCMPL, new FCMPL());
        opMap.put(OpCode.IF_ICMPEQ, new IF_ICMPEQ());
        opMap.put(OpCode.IF_ICMPNE, new IF_ICMPNE());
        opMap.put(OpCode.IF_ICMPLT, new IF_ICMPLT());
        opMap.put(OpCode.IF_ICMPGE, new IF_ICMPGE());
        opMap.put(OpCode.IF_ICMPGT, new IF_ICMPGT());
        opMap.put(OpCode.IF_ICMPLE, new IF_ICMPLE());
        opMap.put(133, new I2L());
        opMap.put(134, new I2F());
        opMap.put(135, new I2D());
        opMap.put(OpCode.D2I, new D2I());
        opMap.put(OpCode.D2L, new D2L());
        opMap.put(OpCode.F2D, new F2D());
        opMap.put(OpCode.F2I, new F2I());
        opMap.put(OpCode.F2L, new F2L());
        opMap.put(136, new L2I());
        opMap.put(137, new L2F());
        opMap.put(OpCode.L2D, new L2D());
        opMap.put(144, new D2F());
        opMap.put(145, new I2B());
        opMap.put(146, new I2C());
        opMap.put(147, new I2S());
        opMap.put(OpCode.IINC, new IINC());
        opMap.put(OpCode.IXOR, new IXOR());
        opMap.put(OpCode.LXOR, new LXOR());
        opMap.put(OpCode.IADD,new IADD());
        opMap.put(OpCode.LADD, new LADD());
        opMap.put(OpCode.IDIV, new IDIV());
        opMap.put(OpCode.IMUL, new IMUL());
        opMap.put(OpCode.ISUB, new ISUB());
        opMap.put(OpCode.DADD, new DADD());
        opMap.put(OpCode.FADD, new FADD());
        opMap.put(OpCode.INEG, new INEG());
        opMap.put(OpCode.ISHL, new ISHL());
        opMap.put(OpCode.NEWARRAY, new NEWARRAY());
        opMap.put(OpCode.MULTIANEWARRAY, new MULTIANEWARRAY());
        opMap.put(OpCode.CASTORE, new CASTORE());
        opMap.put(OpCode.AASTORE, new AASTORE());
        opMap.put(OpCode.ARRAYLENGTH, new ARRAYLENGTH());
        opMap.put(OpCode.CALOAD, new CALOAD());
        opMap.put(OpCode.AALOAD, new AALOAD());
    }

    public static Instruction decode(int opcode) {
        Instruction instruction = opMap.get(opcode);
        if (instruction == null) {
            throw new UnsupportedOperationException("Unsupported instruction " + String.format("%d", opcode));
        }
        return instruction;
    }
}