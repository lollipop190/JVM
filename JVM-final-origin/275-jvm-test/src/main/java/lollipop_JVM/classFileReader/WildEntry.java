package lollipop_JVM.classFileReader;

import java.io.File;
import java.nio.file.Paths;

public class WildEntry extends Entry{
    private File[] jarFiles;
    public WildEntry(String classPath){
        super(classPath);
        jarFiles = Paths.get(classPath).toFile().listFiles(new jarFilter());
    }
    @Override
    public byte[] readClassFile(String className){
        className = checkClassName(className);
        if(jarFiles == null){
            return null;
        }
        for (File file: jarFiles) {
            try{
                return new ArchivedEntry(file.toString()).readClassFile(className);
            }catch (Exception e){
            }
        }
        return null;
    }
}
