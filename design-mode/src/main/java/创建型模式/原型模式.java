package 创建型模式;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 原型模式虽然是创建型的模式，但是与工程模式没有关系，从名字即可看出，该模式的思想就是将一个
 * 对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象。本小结会通过对象的复制，进
 * 行讲解。在 Java 中，复制对象是通过 clone()实现的
 * 一个原型类，只需要实现 Cloneable 接口，覆写 clone 方法，此处 clone 方法可以改成任意的 名称，
 * 因为 Cloneable 接口是个空接口，你可以任意定义实现类的方法名，如 cloneA 或者 cloneB，
 * 因为此处的重点是super.clone()这句话，super.clone()调用的是Object的clone()方法，
 * 而在Object类中， clone()是 native 的，具体怎么实现，另一篇文章中，
 * 关于解读 Java 中本地方法的调用，此处不 再深究。
 * 首先需要了解对象深、浅复制的概念：
 * 浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。
 * 深复制：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深
 * 复制进行了完全彻底的复制，而浅复制不彻底。
 * 要实现深复制，需要采用流的形式读入当前对象的二进制输入，再写出二进制数据对应的对象。
 */
public class 原型模式 { }
class Prototype implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    private String string;
    private SerializableObject obj;

    /* 浅复制 */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Prototype proto = (Prototype) super.clone();
        return proto;
    }
    /* 深复制 */
    public Object deepClone() throws IOException, ClassNotFoundException {
        /* 写入当前对象的二进制流 */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        /* 读出二进制流产生的新对象 */
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

}
class SerializableObject implements Serializable {
    private static final long serialVersionUID = 1L;
}