package 创建型模式;

/**
 *
 * 简单工厂模式有一个问题就是，类的创建依赖工厂类，也就是说，如果想要拓展程序，必须对工厂类进
 * 行修改，这违背了闭包原则，所以，从设计角度考虑，有一定的问题，如何解决？就用到工厂方法模式，
 * 创建一个工厂接口和创建多个工厂实现类，这样一旦需要增加新的功能，直接增加新的工厂类就可以了，
 * 不需要修改之前的代码
 *
 * 其实这个模式的好处就是，如果你现在想增加一个功能：发及时信息，则只需做一个实现类，实现 Sender 接口
 * ，同时做一个工厂类，实现 创建型模式.Provider 接口，就 OK 了，无需去改动现成的代码。这样做，拓展性 较好
 */
public class 工厂方法模式 {

    public static void main(String[] args) {
        DogFactory dogFactory = new DogFactory();
        Animal animal = dogFactory.getAnimal();
        animal.eat();
    }
}

//抽象工厂类
interface Provider {
    Animal getAnimal();
}
//工厂A
class DogFactory implements Provider {

    @Override
    public Animal getAnimal() {
        return new Dog();
    }
}
//工厂B
class CatFactory implements Provider {

    @Override
    public Animal getAnimal() {
        return new Cat();
    }
}



