package lollipop_JVM.runtimeDataArea.struct.array;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ArrayType {
    public static final int AT_BOOLEAN = 4;
    public static final int AT_CHAR = 5;
    public static final int AT_FLOAT = 6;
    public static final int AT_DOUBLE = 7;
    public static final int AT_BYTE = 8;
    public static final int AT_SHORT = 9;
    public static final int AT_INT = 10;
    public static final int AT_LONG = 11;

    private static HashMap<Integer, String> arrayType = new LinkedHashMap<>();
    private static HashMap<String,Integer> IntArrayType = new LinkedHashMap<>();
    static {
        arrayType.put(AT_BOOLEAN,"boolean");
        arrayType.put(AT_CHAR,"char");
        arrayType.put(AT_FLOAT,"float");
        arrayType.put(AT_DOUBLE,"double");
        arrayType.put(AT_BYTE,"byte");
        arrayType.put(AT_SHORT,"short");
        arrayType.put(AT_INT,"int");
        arrayType.put(AT_LONG,"long");

        IntArrayType.put("Z", AT_BOOLEAN);
        IntArrayType.put("B", AT_BYTE);
        IntArrayType.put("S", AT_SHORT);
        IntArrayType.put("C", AT_CHAR);
        IntArrayType.put("I", AT_INT);
        IntArrayType.put("J", AT_LONG);
        IntArrayType.put("F", AT_FLOAT);
        IntArrayType.put("D",AT_DOUBLE);
    }

    public static Integer getIntType(String type){
        return IntArrayType.get(type);
    }
    public static String getType(Integer type){
        return arrayType.get(type);
    }
}
