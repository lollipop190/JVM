package lollipop_JVM.classFileReader;

import com.njuse.jvmfinal.util.IOUtil;

import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ArchivedEntry extends Entry{
    public ArchivedEntry(String classpath) {
        super(classpath);
    }

    @Override
    public byte[] readClassFile(String className) throws IOException,ClassNotFoundException{
        className = checkClassName(className);
        JarFile jarFile = new JarFile(classPath);
        JarEntry jarEntry = jarFile.getJarEntry(className);
        if (jarEntry == null)
            throw new ClassNotFoundException();
        return IOUtil.readFileByBytes(jarFile.getInputStream(jarEntry));
    }
}
