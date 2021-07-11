package edu.nju;

import java.io.File;
import java.io.IOException;

/**
 * format : dir/subdir;dir/subdir/*;dir/target.jar*
 */
public class CompositeEntry extends Entry{
    public CompositeEntry(String classpath) {
        super(classpath);
    }

    @Override
    public byte[] readClassFile(String className) throws IOException {
        String[] paths = classpath.split(File.pathSeparator);
        for (String path:paths) {
            try{
            return ClassFileReader.readClassFile(path,className.substring(0,className.length()-6));
        }catch (ClassNotFoundException e){
                continue;
            }
        }
        return null;
    }
}
