package 行为型模式;

/**
 * 面向对象程序设计过程中，程序员常常会遇到这种情况：设计一个系统时知道了算法所需的关键步骤，
 * 而且确定了这些步骤的执行顺序，但某些步骤的具体实现还未知或不同，或者说某些步骤的实现与具体的环境相关。
 *
 * 例如，去银行办理业务一般要经过以下4个流程：取号、排队、办理具体业务、对银行工作人员进行评分等，
 * 其中取号、排队和对银行工作人员进行评分的业务对每个客户是一样的，可以在父类中实现，
 * 但是办理具体业务却因人而异，它可能是存款、取款或者转账等，可以延迟到子类中实现。
 */
public class 模板模式 {

    public static void main(String[] args) {
        ConcreteClass concreteClass = new ConcreteClass();
        concreteClass.TemplateMethod();
    }
}

//抽象类
abstract class AbstractClass
{
    public void TemplateMethod() //模板方法
    {
        SpecificMethod();
        abstractMethod1();
        abstractMethod2();
    }
    public void SpecificMethod() //具体方法
    {
        System.out.println("抽象类中的具体方法被调用...");
    }
    public abstract void abstractMethod1(); //抽象方法1
    public abstract void abstractMethod2(); //抽象方法2
}
//具体子类
class ConcreteClass extends AbstractClass
{
    @Override
    public void abstractMethod1()
    {
        System.out.println("抽象方法1的实现被调用...");
    }
    @Override
    public void abstractMethod2()
    {
        System.out.println("抽象方法2的实现被调用...");
    }
}