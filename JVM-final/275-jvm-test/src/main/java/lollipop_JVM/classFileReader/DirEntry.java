package lollipop_JVM.classFileReader;

import com.njuse.jvmfinal.util.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DirEntry extends Entry {
    public DirEntry(String classPath){
        super(classPath);
    }
    @Override
    public byte[] readClassFile(String className) throws IOException, ClassNotFoundException {
        className = checkClassName(className);
        FileInputStream fileInputStream;
        try{
            fileInputStream = new FileInputStream(classPath + File.separator + className);
        }catch (FileNotFoundException e){
            return null;
        }
        return IOUtil.readFileByBytes(fileInputStream);
    }
}
