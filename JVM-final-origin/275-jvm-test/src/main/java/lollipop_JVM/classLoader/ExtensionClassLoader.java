package lollipop_JVM.classLoader;

import lollipop_JVM.classFileReader.ClassFileReader;
import lollipop_JVM.classFileReader.Entry;

public class ExtensionClassLoader implements InterClassLoader {
    public boolean isParent = true;
    private static ExtensionClassLoader extensionClassLoader = new ExtensionClassLoader();
    private String classPath = ClassFileReader.extClassPath;
    private Entry entry = ClassFileReader.chooseEntryType(classPath);



    public Entry getEntry() {
        return entry;
    }

    @Override
    public boolean isParent() {
        return this.isParent;
    }

    protected ExtensionClassLoader(){
    }
    @Override
    public String getClassPath() {
        return classPath;
    }


    public static ExtensionClassLoader getInstance(){
        return extensionClassLoader;
    }

}
