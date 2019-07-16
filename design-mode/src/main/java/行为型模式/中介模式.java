package 行为型模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介者（Mediator）模式的定义：定义一个中介对象来封装一系列对象之间的交互，使原有对象之间的耦合松散，且可以独立地改变它们之间的交互。中介者模式又叫调停模式，它是迪米特法则的典型应用。
 *
 * 中介者模式是一种对象行为型模式，其主要优点如下。
 * 降低了对象之间的耦合性，使得对象易于独立地被复用。
 * 将对象间的一对多关联转变为一对一的关联，提高系统的灵活性，使得系统易于维护和扩展。
 *
 * 其主要缺点是：当同事类太多时，中介者的职责将很大，它会变得复杂而庞大，以至于系统难以维护。
 *
 * 中介者模式包含以下主要角色。
 * 抽象中介者（Mediator）角色：它是中介者的接口，提供了同事对象注册与转发同事对象信息的抽象方法。
 * 具体中介者（ConcreteMediator）角色：实现中介者接口，定义一个 List 来管理同事对象，协调各个同事角色之间的交互关系，因此它依赖于同事角色。
 * 抽象同事类（Colleague）角色：定义同事类的接口，保存中介者对象，提供同事对象交互的抽象方法，实现所有相互影响的同事类的公共功能。
 * 具体同事类（Concrete Colleague）角色：是抽象同事类的实现者，当需要与其他同事对象交互时，由中介者对象负责后续的交互。
 */
public class 中介模式 {

    public static void main(String[] args) {
        ConcreteMeiator meiator = new ConcreteMeiator();
        ConcreteColleague1 colleague1 = new ConcreteColleague1();
        meiator.register(colleague1);
        ConcreteColleague2 colleague2 = new ConcreteColleague2();
        meiator.register(colleague2);
        colleague1.send();
    }
}

interface Mediator {
    void register(Colleague colleague);
    void relay(Colleague colleague);
}
class ConcreteMeiator implements Mediator{

    private List<Colleague> list = new ArrayList<>();

    @Override
    public void register(Colleague colleague) {
        list.add(colleague);
        colleague.setMedium(this);
    }

    @Override
    public void relay(Colleague colleague) {
        for(Colleague col : list){
            if(!col.equals(colleague)){
                col.receive();
            }

        }
    }
}

abstract class Colleague {
    protected Mediator mediator;

    public void setMedium(Mediator medium) {
        this.mediator = medium;
    }

    public abstract void receive();
    public abstract void send();
}

class ConcreteColleague1 extends Colleague{
    @Override
    public void receive() {
        System.out.println("1收到请求");
    }

    @Override
    public void send() {
        System.out.println("1发出请求");
        mediator.relay(this);
    }
}
class ConcreteColleague2 extends Colleague{
    @Override
    public void receive() {
        System.out.println("2收到请求");
    }

    @Override
    public void send() {
        System.out.println("2发出请求");
        mediator.relay(this);
    }
}