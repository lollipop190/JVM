package lollipop_JVM.classFileReader;

import java.io.File;
import java.io.IOException;

public abstract class Entry {
    public String classPath;

    public Entry(String classPath){
        this.classPath = classPath;
    }

    public String getClassPath() {
        return classPath;
    }

    public abstract byte[] readClassFile(String className) throws IOException, ClassNotFoundException;
    public String checkClassName(String className){
        if (!className.endsWith(".class")){
            className = className.replace(".", File.separator);
            return className + ".class";
        }
        return className.endsWith(".class") ? className: className + ".class";
    }
}
