package 创建型模式;

/**
 *
 * 工厂方法模式和抽象工厂模式不好分清楚，他们的区别如下：
 * 创建型模式.工厂方法模式：
 * 一个抽象产品类，可以派生出多个具体产品类
 * 一个抽象工厂类，可以派生出多个具体工厂类。
 * 每个具体工厂类只能创建一个具体产品类的实例。
 *
 * 创建型模式.抽象工厂模式：
 * 多个抽象产品类，每个抽象产品类可以派生出多个具体产品类。
 * 一个抽象工厂类，可以派生出多个具体工厂类。
 * 每个具体工厂类可以创建多个具体产品类的实例，也就是创建的是一个产品线下的多个产品。
 *
 * 区别：
 * 工厂方法模式只有一个抽象产品类，而抽象工厂模式有多个。
 * 工厂方法模式的具体工厂类只能创建一个具体产品类的实例，而抽象工厂模式可以创建多个。
 * 工厂方法创建 "一种" 产品，他的着重点在于"怎么创建"，也就是说如果你开发，你的大量代码很可能 围绕着这种产品的构造，
 * 初始化这些细节上面。也因为如此，类似的产品之间有很多可以复用的特征， 所以会和模版方法相随。
 *
 * 抽象工厂需要创建一些列产品，着重点在于"创建哪些"产品上，也就是说，如果你开发，你的主要任务是划分不同差异的产品线,
 * 并且尽量保持每条产品线接口一致，从而可以从同一个抽象工厂继承。
 * 对于 java 来说，你能见到的大部分抽象工厂模式都是这样的：
 * ---它的里面是一堆工厂方法，每个工厂方法返回某种类型的对象。
 *
 * 比如说工厂可以生产鼠标和键盘。那么抽象工厂的实现类（它的某个具体子类）的对象都可以生产鼠标 和键盘，
 * 但可能工厂 A 生产的是罗技的键盘和鼠标，工厂 B 是微软的。
 *
 * 抽象工厂模式除了具有工厂方法模式的优点外，其他主要优点如下。
 * 可以在类的内部对产品族中相关联的多等级产品共同管理，而不必专门引入多个新的类来进行管理。
 * 当增加一个新的产品族时不需要修改原代码，满足开闭原则。
 *
 * 其缺点是：当产品族中需要增加一个新的产品时，所有的工厂类都需要进行修改。
 */
public class 抽象工厂模式 {

}
//一个抽象产品类
interface Cat1 {
    void eat();
}
//一个抽象产品类
interface Dog1 {
    void eat();
}
//抽象工厂(宠物店)
interface AbstractFactory {
    Cat1 getCat();
    Dog1 getDog();
}

//具体狗类下产品A
class DogA implements Dog1 {
    @Override
    public void eat() {
        System.out.println("A狗");
    }
}
//具体狗类下产品B
class DogB implements Dog1 {
    @Override
    public void eat() {
        System.out.println("B狗");
    }
}
//具体猫类下产品A
class CatA implements Cat1 {
    @Override
    public void eat() {
        System.out.println("A猫");
    }
}
//具体猫类下产品B
class CatB implements Cat1 {
    @Override
    public void eat() {
        System.out.println("B猫");
    }
}
//具体宠物工厂A
class shopFactoryA implements AbstractFactory {

    @Override
    public Cat1 getCat() {
        return new CatA();
    }

    @Override
    public Dog1 getDog() {
        return new DogA();
    }
}
//具体宠物工厂B
class shopFactoryB implements AbstractFactory {

    @Override
    public Cat1 getCat() {
        return new CatB();
    }

    @Override
    public Dog1 getDog() {
        return new DogB();
    }
}

