package 结构型模式;

/**
 * 下三种适配器模式的应用场景：
 * 类的适配器模式：当希望将一个类转换成满足另一个新接口的类时，可以使用类的适配器模式，创建一
 * 个新类，继承原有的类，实现新的接口即可。
 * 对象的适配器模式：当希望将一个对象转换成满足另一个新接口的对象时，可以创建一个 Wrapper 类，
 * 持有原类的一个实例，在 Wrapper 类的方法中，调用实例的方法就行。
 * 接口的适配器模式：当不希望实现一个接口中所有的方法时，可以创建一个抽象类 Wrapper，实现所
 * 有方法，我们写别的类的时候，继承抽象类即可。
 */
public class 适配器模式 {
}

//目标接口
interface Target {
    void request();
}
//适配者类
class Adaptee {
    public void specificRequest()
    {
        System.out.println("适配者中的业务代码被调用！");
    }
}
//类适配器
class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request()
    {
        specificRequest();
    }
}

/**
 * 基本思路和类的适配器模式相同，只是将 Adapter 类作修改，这次不继承 Adaptee 类，而是持有 Adaptee
 * 类
 */
//对象适配器类
class ObjectAdapter implements Target
{
    private Adaptee adaptee;
    public ObjectAdapter(Adaptee adaptee)
    {
        this.adaptee=adaptee;
    }
    @Override
    public void request()
    {
        adaptee.specificRequest();
    }
}
/**
 * 有时我们写的一个接口中有多个抽象
 * 方法，当我们写该接口的实现类时，必须实现该接口的所有方法，这明显有时比较浪费，因为并不是所
 * 有的方法都是我们需要的，有时只需要某一些，此处为了解决这个问题，我们引入了接口的适配器模式，
 * 借助于一个抽象类，该抽象类实现了该接口，实现了所有的方法，而我们不和原始的接口打交道，只和
 * 该抽象类取得联系，所以我们写一个类，继承该抽象类，重写我们需要的方法就行。
 */
//目标接口
interface Targetable {
    //目标方法
    void request1();
    void request2();
}

abstract class Wrapper implements Targetable {
    @Override
    public void request1(){}
    @Override
    public void request2(){}
}

class Adapter1 extends Wrapper {
    @Override
    public void request1() {
        System.out.println("只要请求1");
    }

}

class Adapter2 extends Wrapper {
    @Override
    public void request2() {
        System.out.println("只要请求2");
    }

}

