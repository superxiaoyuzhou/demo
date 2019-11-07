package 创建型模式;

/**
 *
 * 工厂模式适合：凡是出现了大量的产品需要创建，并且具有共同的接口时，可以通过工厂方
 * 法模式进行创建。在以上的三种模式中，第一种如果传入的字符串有误，不能正确创建对象，第三种相
 * 对于第二种，不需要实例化工厂类，所以，大多数情况下，我们会选用第三种——静态工厂方法模式。
 *
 * 简单工厂模式有一个问题就是，类的创建依赖工厂类，也就是说，如果想要拓展程序，必须对工厂类进
 * 行修改，这违背了闭包原则，所以，从设计角度考虑，有一定的问题，如何解决？就用到工厂方法模式，
 * 创建一个工厂接口和创建多个工厂实现类，这样一旦需要增加新的功能，直接增加新的工厂类就可以了，
 * 不需要修改之前的代码
 */
public class 简单工厂模式 {

    public static void main(String[] args) {
        AnimalFactory animalFactory = new AnimalFactory();
        Animal dog = animalFactory.getAnimal("创建型模式.Dog");
        dog.eat();
    }

}

//普通工厂
class AnimalFactory {

    public Animal getAnimal(String type) {
        if ("创建型模式.Dog".equals(type)){
            return new Dog();
        } else if ("创建型模式.Cat".equals(type)){
            return new Cat();
        }
        return null;
    }
}

//多方法普通工厂
class AnimalFactorys {

    public Animal getDog() {
        return new Dog();
    }
    public Animal getCat() {
        return new Cat();
    }
}

//多方法静态普通工厂
class AnimalStaticFactorys {

    public static Animal getDog() {
        return new Dog();
    }
    public static Animal getCat() {
        return new Cat();
    }
}

//抽象产品类
interface Animal {
    void eat();
}
//具体产品A
class Dog implements Animal {

    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }
}
//*具体产品B
class Cat implements Animal {

    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }
}