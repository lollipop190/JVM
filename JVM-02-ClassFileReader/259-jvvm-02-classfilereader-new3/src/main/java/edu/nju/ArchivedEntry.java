package edu.nju;

import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * format : dir/subdir/target.jar
 */
public class ArchivedEntry extends Entry{
    public ArchivedEntry(String classpath) {
        super(classpath);
    }

    @Override
    public byte[] readClassFile(String className) throws IOException,ClassNotFoundException{
        JarFile jarFile = new JarFile(classpath);
        JarEntry jarEntry = jarFile.getJarEntry("java/lang/Object.class");
        if (jarEntry == null)
            throw new ClassNotFoundException();
        return IOUtil.readFileByBytes(jarFile.getInputStream(jarEntry));
    }
}

