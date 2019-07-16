package 行为型模式;

import java.util.HashSet;
import java.util.Set;

/**
 * 解释器（Interpreter）模式的定义：给分析对象定义一个语言，并定义该语言的文法表示，
 * 再设计一个解析器来解释语言中的句子。也就是说，用编译语言的方式来分析应用中的实例。
 * 这种模式实现了文法表达式处理的接口，该接口解释一个特定的上下文。
 *
 * 这里提到的文法和句子的概念同编译原理中的描述相同，“文法”指语言的语法规则，
 * 而“句子”是语言集中的元素。例如，汉语中的句子有很多，“我是中国人”是其中的一个句子，
 * 可以用一棵语法树来直观地描述语言中的句子。
 *
 * 解释器模式是一种类行为型模式，其主要优点如下。
 * 扩展性好。由于在解释器模式中使用类来表示语言的文法规则，因此可以通过继承等机制来改变或扩展文法。
 * 容易实现。在语法树中的每个表达式节点类都是相似的，所以实现其文法较为容易。
 *
 * 解释器模式的主要缺点如下。
 * 执行效率较低。解释器模式中通常使用大量的循环和递归调用，当要解释的句子较复杂时，其运行速度很慢，
 * 且代码的调试过程也比较麻烦。
 * 会引起类膨胀。解释器模式中的每条规则至少需要定义一个类，当包含的文法规则很多时，类的个数将急剧增加，
 * 导致系统难以管理与维护。
 * 可应用的场景比较少。在软件开发中，需要定义语言文法的应用实例非常少，所以这种模式很少被使用到。
 */
public class 解释器模式 {
    /**
     * 文法规则
     *     <expression> ::= <city>的<person>
     *     <city> ::= 韶关|广州
     *     <person> ::= 老人|妇女|儿童
     * @param args
     */
    public static void main(String[] args) {
        Context2 context2 = new Context2();
        context2.freeRide("重庆的小姐");
        context2.freeRide("重庆的老人");
        context2.freeRide("重庆的孕妇");
    }
}

//抽象表达式类
interface Expression
{
    public boolean interpret(String info);
}
//终结符表达式类
class TerminalExpression implements Expression
{
    private Set<String> set= new HashSet<String>();
    public TerminalExpression(String[] data)
    {
        for(int i=0;i<data.length;i++)set.add(data[i]);
    }
    @Override
    public boolean interpret(String info)
    {
        if(set.contains(info))
        {
            return true;
        }
        return false;
    }
}
//非终结符表达式类
class AndExpression implements Expression
{
    private Expression city=null;
    private Expression person=null;
    public AndExpression(Expression city,Expression person)
    {
        this.city=city;
        this.person=person;
    }
    @Override
    public boolean interpret(String info)
    {
        String s[]=info.split("的");
        return city.interpret(s[0])&&person.interpret(s[1]);
    }
}
//环境类
class Context2
{
    private String[] citys={"韶关","广州"};
    private String[] persons={"老人","妇女","儿童"};
    private Expression cityPerson;
    public Context2()
    {
        Expression city=new TerminalExpression(citys);
        Expression person=new TerminalExpression(persons);
        cityPerson=new AndExpression(city,person);
    }
    public void freeRide(String info)
    {
        boolean ok=cityPerson.interpret(info);
        if(ok) {
            System.out.println("您是"+info+"，您本次乘车免费！");
        } else {
            System.out.println(info+"，您不是免费人员，本次乘车扣费2元！");
        }
    }
}