package edu.nju;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * format : dir/subdir/.../
 */
public class DirEntry extends Entry{
    public DirEntry(String classpath) {
        super(classpath);
    }

    @Override
    public byte[] readClassFile(String className) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream;
        try{
          fileInputStream = new FileInputStream(classpath + File.separator + className);
        }catch (FileNotFoundException e){
            throw new ClassNotFoundException();
        }
        return IOUtil.readFileByBytes(fileInputStream);
    }

}
