package 结构型模式;

/**
 * 将抽象与实现分离，使它们可以独立变化。
 * 它是用组合关系代替继承关系来实现，从而降低了抽象和实现这两个可变维度的耦合度。
 */
public class 桥接模式 {
    public static void main(String[] args) {
        Implementor imple = new ConcreteImplementorA();
        Abstraction abs = new RefinedAbstraction(imple);
        abs.Operation();
    }
}

//实现化角色
interface Implementor {
    void OperationImpl();
}

//具体实现化角色
class ConcreteImplementorA implements Implementor {

    @Override
    public void OperationImpl() {

    }
}

//抽象化角色
abstract class Abstraction {
    protected Implementor implementor;

    protected Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void Operation();
}

//扩展抽象化角色
class RefinedAbstraction extends Abstraction {
    protected RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void Operation() {

        System.out.println("扩展抽象化(Refined Abstraction)角色被访问");
        implementor.OperationImpl();
    }
}