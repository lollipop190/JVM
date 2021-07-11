
public class ConditionTest {
    public static void test(int small, int big, long smallL, long bigL, float smallF, float bigF,
                            double smallD, double bigD) {
        if (small == 3) {
            if (small < big && smallL < bigL && smallF < bigF && smallD < bigD) {

            } else {
                System.out.println("1");
                TestUtil.fail();
            }
            big++;
            if (big > small && bigL > smallL && bigF > smallF && bigD > smallD) {

            } else {
                System.out.println("2");
                TestUtil.fail();
            }
        } else {
            System.out.println("3");
            TestUtil.fail();
        }


        if (small <= big) {
            if (big > small) {

            } else {
                System.out.println("4");
                TestUtil.fail();
            }
            if (big + 1 >= small) {
                if (big == small) {
                    System.out.println("5");
                    TestUtil.fail();
                }
                if (big != small) {

                } else {
                    System.out.println("6");
                    TestUtil.fail();
                }
            }
        }
    }

    public static void main(String[] args) {
        test(3, 4, 5, 6, 7f, 8f, 9, 10);
    }
}
