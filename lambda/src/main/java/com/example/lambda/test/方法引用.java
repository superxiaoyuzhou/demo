package com.example.lambda.test;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @ClassName方法引用
 * @Description
 * @Author Piter
 * @Date2021/4/21 22:17
 * @Version V1.0
 **/
public class 方法引用 {
    public static void main(String[] args) {
        //1.方法引用
        //lambda
        Consumer<String> consumer = s -> System.out.println(s);
        //lambda替换为方法引用
        Consumer<String> consumer2 = System.out::println;

        Dog dog = new Dog();
        dog.eat2(1);
        //2.静态方法引用
        Consumer<Dog> consumer3 = Dog::bark;
        consumer3.accept(dog);

        //3.非静态方法引用,使用对象实例的方法引用
        Function<Integer,Integer> function = dog::eat;
        System.out.println("还剩"+function.apply(666));

        //4.使用类名来引用方法
        BiFunction<Dog,Integer,Integer> biFunction = Dog::eat;
//        BiFunction<Dog,Integer,Integer> biFunction = Dog::eat2;
        System.out.println("还剩"+biFunction.apply(dog,88));

        //5.构造函数的方法引用
        Supplier<Dog> supplier = Dog::new;
        System.out.println("创建了新对象:" + supplier.get());

        //6.带参数的构造函数的方法引用
        Function<Integer, Dog> function1 = Dog::new;
        System.out.println("创建了新对象:" + function1.apply(99));

    }

}
class Dog {
    private int food = 1000;

    public Dog(){

    }

    public Dog(int food){
        this.food = food;
    }

    public static void bark(Dog dog) {
        System.out.println("狗叫了");
    }

    public int eat(int aaa) {
        System.out.println("吃了:" + aaa + "斤狗粮");
        this.food = this.food - aaa;
        return this.food ;
    }

    /**
     * jdk 会默认把当前实例作为第一个参数传入到非静态方法中,参数名为 this,通过字节码查看编译后代码
     * 所以在成员方法中可以通过this获取成员变量或通过this调用方法
     * @param aaa
     * @return
     */
    public int eat2(Dog this,int aaa) {
        System.out.println("吃了:" + aaa + "斤狗粮");
        this.food = this.food - aaa;
        return this.food ;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "food=" + food +
                '}';
    }
}
