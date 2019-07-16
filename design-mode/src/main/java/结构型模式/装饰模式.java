package 结构型模式;

/**
 * 顾名思义，装饰模式就是给一个对象增加一些新的功能，而且是动态的，要求装饰对象和被装饰对象实
 * 现同一个接口，装饰对象持有被装饰对象的实例
 * 通常情况下，扩展一个类的功能会使用继承方式来实现。但继承具有静态特征，耦合度高，
 * 并且随着扩展功能的增多，子类会很膨胀。如果使用组合关系来创建一个包装对象（即装饰对象）来包裹真实对象，
 * 并在保持真实对象的类结构不变的前提下，为其提供额外的功能，这就是装饰模式的目标。
 *
 * 装饰者模式的关注点在于添加功能
 *
 * 代理模式的关注点在于控制对象的访问，其原型对象对于用户无法得知
 *
 * 适配器模式关注点在于适配
 */
public class 装饰模式 {
}

//抽象构件角色
interface Component {
    void operation();
}

//具体构件角色
class ConcreteComponent implements Component {
    public ConcreteComponent() {
        System.out.println("创建具体构件角色");
    }

    @Override
    public void operation() {
        System.out.println("调用具体构件角色的方法operation()");
    }
}

//抽象装饰角色
abstract class Decorator implements Component {
    private Component component;

    Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}

//具体装饰角色
class ConcreteDecorator extends Decorator {

    ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        addedFunction();
    }


    public void addedFunction() {
        System.out.println("为具体构件角色增加额外的功能addedFunction()");
    }

}