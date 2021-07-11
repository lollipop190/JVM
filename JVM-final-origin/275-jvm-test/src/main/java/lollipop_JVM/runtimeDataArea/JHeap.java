package lollipop_JVM.runtimeDataArea;

import lollipop_JVM.runtimeDataArea.struct.JObject;

import java.util.LinkedHashSet;
import java.util.Set;
public class JHeap {
    private static JHeap heap = new JHeap();
    private static final int maxSize = 50;
    private static int currentSize = 0;
    private Set<JObject> objects = new LinkedHashSet<>();

    private JHeap(){
    }

    public static JHeap getInstance(){
        return heap;
    }

    public void addObj(JObject obj) {
        if (currentSize >= maxSize) throw new OutOfMemoryError();
        objects.add(obj);
        currentSize++;
    }
}
