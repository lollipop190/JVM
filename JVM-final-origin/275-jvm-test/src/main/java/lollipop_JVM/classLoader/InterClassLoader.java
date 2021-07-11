package lollipop_JVM.classLoader;

import lollipop_JVM.classFileReader.Entry;

public interface InterClassLoader {
    public String getClassPath();
    public Entry getEntry();
    public boolean isParent();
//    public static GetSpecificClassLoader(){
//
//    }
}
