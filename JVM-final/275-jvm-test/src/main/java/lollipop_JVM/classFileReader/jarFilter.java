package lollipop_JVM.classFileReader;

import java.io.File;
import java.io.FilenameFilter;

public class jarFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        if (name.endsWith(".jar") || name.endsWith(".JAR"))
            return true;
        return false;
    }
}
