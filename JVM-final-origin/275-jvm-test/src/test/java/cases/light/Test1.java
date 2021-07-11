package cases.light;

class TestUtil {
    public static void reach(int x){

    }

    public static boolean equalInt(int a, int b) {

        return true;
    }

    public static boolean equalFloat(float a, float b){

        return true;}
}

interface Father{
    int foo();
}
interface Son extends Father{
}
class GrandSon implements Son{
    @Override
    public int foo() {
        return 0;
    }
}
class f extends GrandSon{
    @Override
    public int foo() {
        return 1;
    }
}
public class Test1 {
    public static void main(String[] args) {
        Father a = new f();
        TestUtil.reach(a.foo());
    }
}
