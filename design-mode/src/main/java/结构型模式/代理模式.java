package 结构型模式;

/**
 * 代理模式的主要优点有：
 * 代理模式在客户端与目标对象之间起到一个中介作用和保护目标对象的作用；
 * 代理对象可以扩展目标对象的功能；
 * 代理模式能将客户端与目标对象分离，在一定程度上降低了系统的耦合度；
 *
 * 其主要缺点是：
 * 在客户端和目标对象之间增加一个代理对象，会造成请求处理速度变慢；
 * 增加了系统的复杂度；
 *
 * 1、和适配器模式的区别：适配器模式主要改变所考虑对象的接口，而代理模式不能改变所代理类的接口。
 * 2、和装饰器模式的区别：装饰器模式为了增强功能，而代理模式是为了加以控制。
 */
public class 代理模式 {
}
interface Subject
{
    void Request();
}
//真实主题
class RealSubject implements Subject
{
    @Override
    public void Request()
    {
        System.out.println("访问真实主题方法...");
    }
}
//代理
class Proxy implements Subject
{
    private RealSubject realSubject;
    @Override
    public void Request()
    {
        if (realSubject==null)
        {
            realSubject=new RealSubject();
        }
        beforeRequest();
        realSubject.Request();
        afterRequest();
    }
    public void beforeRequest()
    {
        System.out.println("访问真实主题之前的预处理。");
    }
    public void afterRequest()
    {
        System.out.println("访问真实主题之后的后续处理。");
    }
}
