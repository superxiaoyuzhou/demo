package com.example.lambda.test;

import java.util.function.Function;

/**
 *  柯里化:把多个参数的函数转换为一个参数的函数
 *  柯里化的目的: 函数标准化
 *
 * @ClassName级联表达式和柯里化
 * @Description
 * @Author Piter
 * @Date2021/4/22 22:01
 * @Version V1.0
 **/
public class 级联表达式和柯里化 {

    public static void main(String[] args) {
        //实现 x + y 的级联表达式
        Function<Integer,Function<Integer,Integer>> fun = x -> y -> x + y;
        System.out.println(fun.apply(1).apply(2));
        // x + y + z 的级联表达式
        Function<Integer,Function<Integer,Function<Integer,Integer>>> fun2 = x -> y -> z -> x+y+z;
        System.out.println(fun2.apply(1).apply(2).apply(3));

        int[] nums = {1,2,3};
        Function f = fun2;
        for (int i=0;i<nums.length;i++){
            if (f instanceof Function) {
                Object obj = f.apply(nums[i]);
                if (obj instanceof Function) {
                    f = (Function) obj;
                } else {
                    System.out.println("调用结束,结果为:" + obj);
                }
            }
        }

    }
}
