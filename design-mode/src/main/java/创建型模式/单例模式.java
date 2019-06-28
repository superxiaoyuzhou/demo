package 创建型模式;

/**
 * 单例对象（创建型模式.Singleton）是一种常用的设计模式。在 Java 应用中，单例对象能保证在一个 JVM 中，该对象 只有一个实例存在。
 * 这样的模式有几个好处：
 * 1、某些类创建比较频繁，对于一些大型的对象，这是一笔很大的系统开销。
 * 2、省去了 new 操作符，降低了系统内存的使用频率，减轻 GC 压力。
 * 3、有些类如交易所的核心交易引擎，控制着交易流程，如果该类可以创建多个的话，系统完全乱了。（比
 * 如一个军队出现了多个司令员同时指挥，肯定会乱成一团），所以只有使用单例模式，才能保证核心交易服
 * 务器独立控制整个流程。
 */
public class 单例模式 {

    public static void main(String[] args) {

    }
}
//懒汉式
class Singleton {
    /* 持有私有静态实例，防止被引用，此处赋值为 null，目的是实现延迟加载 */
    private static volatile Singleton instance = null;
    /* 私有构造方法，防止被实例化 */
    private Singleton() {}
    /* 静态工程方法，创建实例 */
    public static Singleton getInstance1() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    /**
     * 这个类可以满足基本要求，但是，像这样毫无线程安全保护的类，如果我们把它放入多线程的环境下，
     * 肯定就会出现问题了，如何解决？我们首先会想到对 getInstance 方法加 synchronized 关键字
     */
    public static synchronized Singleton getInstance2() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    /**
     * 但是，synchronized 关键字锁住的是这个对象，这样的用法，
     * 在性能上会有所下降，因为每次调用 getInstance()，都要对对象上锁，
     * 事实上，只有在第一次创建对象的时候需要加锁，之后就不需要了，
     * 所以，这个地方需要改进。
     * @return
     */
    public static Singleton getInstance3() {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return instance;
    }

}

//饿汉式
class HungrySingleton
{
    private static final HungrySingleton instance = new HungrySingleton();
    private HungrySingleton(){}
    public static HungrySingleton getInstance()
    {
        return instance;
    }
}

