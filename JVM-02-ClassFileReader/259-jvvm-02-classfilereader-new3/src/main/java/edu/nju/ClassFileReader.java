package edu.nju;

import java.io.File;
import java.io.IOException;

public class ClassFileReader {
    private static final String FILE_SEPARATOR = File.separator;
    private static final String PATH_SEPARATOR = File.pathSeparator;

    private static Entry bootStrapReader;

    public static Entry chooseEntryType(String classpath) {
        /**
         * tips:
         *      Every case can correspond to a class
         *      These cases are disordered
         *      You should take care of the order of if-else
         * case 1 带通配符的路径，例如dir/.../*
         * case 2 normal dir path，相对路径，例如dir/subdir/.../
         * case 3 archived file，归档文件，例如dir/subdir/target.jar
         * case 4 classpath with path separator，复合型，例如dir/subdir;dir/subdir/*;dir/target.jar*
         */
        if (classpath.contains(PATH_SEPARATOR))
            return new CompositeEntry(classpath);
        else if (classpath.endsWith("*"))
            return new WildEntry(classpath);
        else if (classpath.endsWith("zip") ||
                classpath.endsWith("ZIP") ||
                classpath.endsWith("jar") ||
                classpath.endsWith("JAR"))
            return new ArchivedEntry(classpath);
            // 复合型
        else
            return new DirEntry(classpath);
    }

    /**
     * @param classpath where to find target class
     * @param className format: /package/.../class
     * @return content of classfile
     */
    public static byte[] readClassFile(String classpath, String className) throws ClassNotFoundException {
        if (classpath == null || className == null)
            throw new ClassNotFoundException();
        className = IOUtil.transform(className);
        className += ".class";

        bootStrapReader = chooseEntryType(classpath);
        byte[] ret = new byte[0];
        try {
            ret = bootStrapReader.readClassFile(className);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (ret == null)
            throw new ClassNotFoundException();
        return ret;
    }
}
