package 行为型模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 指多个对象间存在一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
 * 这种模式有时又称作发布-订阅模式、模型-视图模式，它是对象行为型模式。
 *
 */
public class 观察者模式 {

    public static void main(String[] args) {
        Observer observer1 = new ConcreteObserver1();
        Observer observer2 = new ConcreteObserver2();
        ConcreteSubject subject = new ConcreteSubject();
        subject.add(observer1);     //添加观察者1
        subject.add(observer2);     //添加观察者2

        subject.notifyObserver();    //被观察者改变 发布通知

    }
}

//抽象目标
abstract class Subject{
    protected List<Observer> observers=new ArrayList<>();
    //增加观察者方法
    public void add(Observer observer)
    {
        observers.add(observer);
    }
    //删除观察者方法
    public void remove(Observer observer)
    {
        observers.remove(observer);
    }
    public abstract void notifyObserver(); //通知观察者方法

}

//具体目标
class ConcreteSubject extends Subject {

    @Override
    public void notifyObserver() {
        System.out.println("具体目标发生改变!");

        for (Observer observer : observers) {
            observer.response();
        }
    }
}

//抽象观察者
interface Observer
{
    void response(); //反应
}

//具体观察者1
class ConcreteObserver1 implements Observer {

    @Override
    public void response() {
        System.out.println("具体观察者1做出反应！");
    }
}

//具体观察者2
class ConcreteObserver2 implements Observer {

    @Override
    public void response() {
        System.out.println("具体观察者2做出反应!");
    }
}