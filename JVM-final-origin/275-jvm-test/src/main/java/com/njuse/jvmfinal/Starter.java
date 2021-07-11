package com.njuse.jvmfinal;


import com.njuse.jvmfinal.memory.jclass.JClass;
import com.njuse.jvmfinal.memory.jclass.Method;
import lollipop_JVM.classFileReader.ClassFileReader;
import lollipop_JVM.classLoader.ApplicationClassLoader;
import lollipop_JVM.classLoader.BootstrapClassLoader;
import lollipop_JVM.classLoader.ClassLoader;
import lollipop_JVM.classLoader.ExtensionClassLoader;
import lollipop_JVM.execution.Interpreter;
import lollipop_JVM.runtimeDataArea.JThread;
import lollipop_JVM.runtimeDataArea.ProgramCounter;
import lollipop_JVM.runtimeDataArea.StackFrame;
import lollipop_JVM.runtimeDataArea.ThreadStack;

import java.util.Stack;

public class Starter {
    public static void main(String[] args) {

    }

    /**
     * ⚠️警告：不要改动这个方法签名，这是和测试用例的唯一接口
     */

    /**
     *cp看着太烦了，我要把它改成classPath
     * 2020.07.03
     */

    public static void runTest(String mainClassName, String classPath) {
        ClassLoader classLoader = ClassLoader.getInstance();
        JClass clazz = null;


        JThread thread = new JThread();
        ThreadStack stack = thread.getThreadStack();
        ProgramCounter programCounter = thread.getProgramCounter();
        // reset PC
        programCounter.resetAll();

        if (classPath.equals(ClassFileReader.bootClassPath)){
            clazz = classLoader.loadClass(mainClassName, BootstrapClassLoader.getInstance());
        }else if (classPath.equals(ClassFileReader.extClassPath)){
            clazz = classLoader.loadClass(mainClassName, ExtensionClassLoader.getInstance());
        }else {
            ClassFileReader.setUserClassPath(classPath);
            clazz = classLoader.loadClass(mainClassName, ApplicationClassLoader.getInstance());
        }


        assert clazz != null;
        // execute clinit first
        clazz.initClass(stack,clazz);

        if (!stack.getStack().empty()) {
           // programCounter.setInClInit(true);
            Interpreter.interpret(thread);

            programCounter.setNextPC(0);
            programCounter.setSavedPC(new Stack<>());
            //programCounter.setInClInit(false);
        }


        Method mainMethod = clazz.getMainMethod();
        StackFrame mainFrame = new StackFrame(stack,mainMethod, mainMethod.getMaxStack(), mainMethod.getMaxLocal());
        stack.pushFrame(mainFrame);

        Interpreter.interpret(thread);


    }

}
