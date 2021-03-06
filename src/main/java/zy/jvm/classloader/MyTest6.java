package zy.jvm.classloader;

/**
 *   public static int counter2 = 0; 的位置对结果的影响：
 *   当位于 private Singleton() 之前，则 结果为 counter1: 1  counter2: 1
 *   当位于 private Singleton() 之后，则 结果为 counter1: 1  counter2: 0
 *
 * 因调用了类的静态变量或静态方法，属于主动使用类，该类会被初始化。
 * 类在 连接过程中，要先经历 准备阶段，这时类的静态变量会被初始化为默认值，
 * 本例中Singleton的counter1和counter2都被初始化为0。
 * 而在类的 初始化阶段，类的静态变量赋值语句和构造方法 按代码编写顺序进行执行，
 * 本例中 Singleton的counter1和counter2在 准备阶段 被赋0，初始化阶段时，没有对counter1 赋值，
 * 而构造方法中counter1和counter2都被加 1，都变成 1，而之后再执行把counter2设置为0的赋值语句。
 * 这就造成了 counter1: 1  counter2: 0 的结果。
 *
 * @author zhangyan_g
 */
public class MyTest6 {
    public static void main(String[] args) {
//        Singleton singleton = Singleton.getInstance();

        System.out.println("counter1: " + Singleton.counter1);
        System.out.println("counter2: " + Singleton.counter2);
    }
}

class Singleton {
    public static int counter1;

    private static Singleton singleton = new Singleton();

    private Singleton() {
        counter1++;
        counter2++;
        System.out.println("构造方法内：counter1=" + counter1 + ", counter2=" + counter2);
    }

    public static int counter2 = 0;

    public static Singleton getInstance(){
        return singleton;
    }
}

