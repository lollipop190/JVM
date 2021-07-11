package lollipop_JVM.classLoader;

import lollipop_JVM.classFileReader.ClassFileReader;
import lollipop_JVM.classFileReader.Entry;

public class ApplicationClassLoader extends ExtensionClassLoader implements InterClassLoader{
    public boolean isParent = false;
    private static ApplicationClassLoader applicationClassLoader = new ApplicationClassLoader();
    protected String classPath;
    private Entry entry;

    @Override
    public boolean isParent() {
        return this.isParent;
    }

    public Entry getEntry() {
        return entry;
    }
    protected ApplicationClassLoader(){
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
        this.entry= ClassFileReader.chooseEntryType(classPath);
    }

    public static ApplicationClassLoader getInstance(){
        return applicationClassLoader;
    }

}
