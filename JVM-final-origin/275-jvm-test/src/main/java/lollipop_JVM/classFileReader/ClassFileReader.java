package lollipop_JVM.classFileReader;

import lollipop_JVM.classLoader.ApplicationClassLoader;
import lollipop_JVM.classLoader.BootstrapClassLoader;
import lollipop_JVM.classLoader.ExtensionClassLoader;
import lollipop_JVM.classLoader.InterClassLoader;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.IOException;

@Getter
@Setter
public class ClassFileReader {

    //todo
    //for test
    private static int a = 0;

   // private static Entry entry;
    private static String JAVA_HOME = System.getenv("JAVA_HOME");
    public static String bootClassPath = String.join(File.separator, JAVA_HOME, "jre","lib", "*");
    public static String extClassPath = String.join(File.separator, JAVA_HOME, "jre","lib", "ext", "*");
    public static String userClassPath;

    private static BootstrapClassLoader bootstrapClassLoader = BootstrapClassLoader.getInstance();
    private static ExtensionClassLoader extensionClassLoader = ExtensionClassLoader.getInstance();
    private static ApplicationClassLoader applicationClassLoader = ApplicationClassLoader.getInstance();

//    private static Entry Bootstrap = chooseEntryType(bootClassPath);
//    private static Entry Extension = chooseEntryType(extClassPath);
//    private static Entry Application;


    public static void setUserClassPath(String userClassPath) {
        ClassFileReader.userClassPath = userClassPath;
        applicationClassLoader.setClassPath(userClassPath);
    }

    public static Entry chooseEntryType(String classpath) {
        String PATH_SEPARATOR = File.pathSeparator;
        if(classpath == null)
            new ClassNotFoundException().printStackTrace();
        if(classpath.contains(PATH_SEPARATOR)){
            return new CompositeEntry(classpath);
        } else if (classpath.contains(".jar") || classpath.contains(".JAR") || classpath.contains(".zip") || classpath.contains(".ZIP")) {
            return new ArchivedEntry(classpath);
        }else if (classpath.contains("*")){
            return new WildEntry(classpath.substring(0,classpath.length()-2));
        }else {
            return new DirEntry(classpath);
        }
    }

/**
 * todo
 * is it a must to deliver a classloader as a permeter?
 * */
    public static Pair<byte[], InterClassLoader> readClassFile(String className,InterClassLoader classLoader) throws ClassNotFoundException, IOException {
        /**todo
         * remember to fix this before pushing
         */
        //className = transform(className);
        //className = transform(classPath);
    a++;

        byte[] content = null;
        Pair<byte[], InterClassLoader> pair = null;
        //双亲委派实现
//        if ( (content =  bootstrapClassLoader.getEntry().readClassFile(className))!= null){
//            return Pair.of(content, bootstrapClassLoader);
//        }else if (classLoader.isParent() && (content = extensionClassLoader.getEntry().readClassFile(className)) != null ){
//            return Pair.of(content, extensionClassLoader);
//        }else if (classLoader. && (content = applicationClassLoader.getEntry().readClassFile(className)) != null){
//            return Pair.of(content, applicationClassLoader);
//        }else {
//            throw new ClassNotFoundException();
//        }



        if ( (content =  bootstrapClassLoader.getEntry().readClassFile(className))!= null){
            return Pair.of(content, bootstrapClassLoader);
        }else if (classLoader instanceof ExtensionClassLoader){
            if ((content = extensionClassLoader.getEntry().readClassFile(className)) != null ) {
                return Pair.of(content, extensionClassLoader);
            }else if (!classLoader.isParent() && (content = applicationClassLoader.getEntry().readClassFile(className)) != null) {
                return Pair.of(content, applicationClassLoader);
            }
        }
            throw new ClassNotFoundException();
        /**
 * todo
 * resolve byte[] to JClass
 * */

    }

    private static String transform(String pathName) {
        if(pathName == null){
            return null;
        }
        if (pathName.contains("/")) {
            return pathName.replace("/", File.separator);
        }
        return pathName;
    }


}
