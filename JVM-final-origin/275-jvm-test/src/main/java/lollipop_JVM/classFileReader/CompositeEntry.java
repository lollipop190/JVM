package lollipop_JVM.classFileReader;

import java.io.File;
import java.io.IOException;

public class CompositeEntry extends Entry{
    public CompositeEntry(String classpath) {
        super(classpath);

    }
    @Override
    public byte[] readClassFile(String className) throws IOException {
        className = checkClassName(className);
        String[] paths = classPath.split(File.pathSeparator);
        for (String path:paths) {
            Entry entry = ClassFileReader.chooseEntryType(path);
            try {
                byte[] content = entry.readClassFile(className);
                if ( content != null){
                    return content;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
