package lollipop_JVM.classLoader;

import lollipop_JVM.classFileReader.ClassFileReader;
import lollipop_JVM.classFileReader.Entry;

public class BootstrapClassLoader implements InterClassLoader{
    private static BootstrapClassLoader bootstrapClassLoader = new BootstrapClassLoader();
    protected String classPath = ClassFileReader.bootClassPath;
    private Entry entry = ClassFileReader.chooseEntryType(classPath);

    public Entry getEntry() {
        return entry;
    }

    @Override
    public boolean isParent() {
        return false;
    }

    private BootstrapClassLoader(){
    }


    public static BootstrapClassLoader getInstance(){
        return bootstrapClassLoader;
    }

}
