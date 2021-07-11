package cases.myTest;

class TestUtil {
    public static void reach(int x){

    }

    public static boolean equalInt(int a, int b) {

        return true;
    }

    public static boolean equalFloat(float a, float b){

        return true;}
}
interface wym{
    void foo();
}
interface xxz extends wym{

}
class zcy implements xxz{
    @Override
    public void foo() {
        TestUtil.reach(3);
    }
}
public class Test {
    public static void main(String[] args) {
        wym a = new zcy();
        a.foo();
    }
}
