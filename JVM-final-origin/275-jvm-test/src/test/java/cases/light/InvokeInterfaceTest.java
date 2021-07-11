package cases.light;

interface Creature{
    default int wym(){
        return 2333;
    }
}
interface Human extends Creature{
    int bar();
}

class Parent {
    Parent(){
    }
    public int foo() {
        return 0;
    }

}

class Child extends Parent implements Human {
    Child() {
    }

    public int foo() {
        return 1;
    }

    public int bar() {
        return this.foo();
    }
}


class Boy extends Child implements Human {
    Boy() {
    }

    public int wym() {
        return -23333;
    }

    public int bar() {
        return 3;
    }
}

public class InvokeInterfaceTest{
    public static void main(String[] args) {
        Child var3 = new Child();
        TestUtil.equalInt(var3.foo(), 1);
        Boy var4 = new Boy();
        TestUtil.equalInt(var4.foo(), 1);
        Boy var5 = new Boy();
        TestUtil.equalInt(var5.bar(), 3);
        TestUtil.equalInt(var5.wym(), -23333);
        Child var6 = new Child();
        TestUtil.equalInt(var6.wym(), 23333);
    }
}