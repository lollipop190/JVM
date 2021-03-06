package light;

import com.njuse.jvmfinal.Starter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Description:
 *
 * @author xxz
 * Created on 07/01/2020
 */
public class LightEasyTest {

    ByteArrayOutputStream outBytes = null;
    String lineSeparator = System.lineSeparator();
    String cp = String.join(File.separator, "src", "test", "java");

    @org.junit.Before
    public void setUp() {

        //todo
        //暂时屏蔽out bytes
//        outBytes = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outBytes));
    }

    @Test
    public void easy1() {
        try {
            Starter.runTest("cases.light.LightEasyTestUtilTest", cp);

            fail("You should throw an exception but you were not.");
        } catch (RuntimeException e) {
            String message = e.getMessage();
            assertEquals("Your exception has wrong output: ", "7!=6", message);
        }

        String out = outBytes.toString().replaceAll("\\s", "");
        if (out.equals("33")) {
            //pass
        } else {
            fail("Your answer is not correct");
        }
     //   System.err.println(out);
    }

    @Test
    public void easy2() {
        Starter.runTest("cases.light.LightEasyStaticTest", cp);

        String out = outBytes.toString().replaceAll("\\s", "");
        if (out.equals("34")) {
            //pass
        } else {
            fail("Your answer is not correct");
        }
        System.err.println(out);

    }

    @Test
    public void easy3() {
        Starter.runTest("cases.light.LightEasyBranchTest", cp);

        String out = outBytes.toString().replaceAll("\\s", "");
        if (out.equals("-3-4")) {
            //pass
        } else {
            fail("Your answer is not correct");
        }
//        System.err.println(out);
    }

    @Test
    public void medium1(){
        Starter.runTest("cases.light.InvokeInterfaceTest", cp);
    }

    @Test
    public void test1(){
        Starter.runTest("cases.myTest.DarkMediumInstruction",cp);
    }

    @Test
    public void testHard(){
        Starter.runTest("cases.hard.DarkHardObjectInst", cp);
    }

    @Test

    public void testField(){
        Starter.runTest("cases.hard.myTest",cp);
    }
    @Test

    public void testMulArray(){
        Starter.runTest("cases.hard.DarkHardMulArray", cp);
    }

}
