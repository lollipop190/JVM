package edu.nju;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * format : dir/.../*
 */
public class WildEntry extends Entry{
    private File[] jarFiles;
    public WildEntry(String classpath)
    {super(classpath);


        //jarFiles = Paths.get(classpath).toFile().listFiles(new jarFilter());
    }

    @Override
    public byte[] readClassFile(String className) throws IOException{
        for (File file: jarFiles) {
            try{
                System.out.println(file.toString());
                return new ArchivedEntry(file.toString()).readClassFile(className);
            }catch (Exception e){
            }
        }
        return null;
    }
}
