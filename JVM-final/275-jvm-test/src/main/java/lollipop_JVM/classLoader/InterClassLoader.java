package lollipop_JVM.classLoader;

import lollipop_JVM.classFileReader.Entry;

public interface InterClassLoader {
    Entry getEntry();
    boolean isParent();

}
