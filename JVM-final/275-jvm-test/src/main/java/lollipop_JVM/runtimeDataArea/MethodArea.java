package lollipop_JVM.runtimeDataArea;

import com.njuse.jvmfinal.memory.jclass.JClass;

import java.util.LinkedHashMap;
import java.util.Map;

public class MethodArea {
    private static Map<String, JClass> classMap = new LinkedHashMap<>();

    public static JClass getClass(String className){
        return classMap.get(className);
    }

    public static void addClass(String className, JClass jClass){
        classMap.put(className, jClass);
    }
}
