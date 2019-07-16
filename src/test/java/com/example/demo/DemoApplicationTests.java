package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void test1() {
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        list.parallelStream().forEach(
                str -> {
                    if ("bc".equals(str)) {
                        return;
                    }
                    System.out.println(str);
                }
        );
        System.out.println("xxxxxxxxxxxxxxxxx");
    }

    @Test
    public void test2() {
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        list = new ArrayList(list);
        for (String str : list) {
            if ("abc".equals(str))
                list.remove("abc");
        }

    }

    @Test
    public void test3() {

        List<String> list1 = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> list = new ArrayList();
        list.addAll(list1);
        list.stream().forEach(str -> {
            if ("abc".equals(str))
                list.add("aaaaa");
        });

    }

    @Test
    public void test4() {
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        list = new ArrayList(list);
        Iterator<String> iterator = list.iterator();
        System.out.println(iterator);
        while (iterator.hasNext()){
            if ("abc".equals(iterator.next())){
                iterator.remove();
            }
        }

    }

    @Test
    public void test5(){
        MyGenericMethod myGenericMethod = new MyGenericMethod();
        myGenericMethod.show("aaa");
        String s = myGenericMethod.show2("aaa");
        myGenericMethod.show(123);
        myGenericMethod.show(12.34);
    }
}
class MyGenericMethod {
    public <MVP> void show(MVP mvp) {
        System.out.println(mvp.getClass());
    }

    public <MVP> MVP show2(MVP mvp) {
        return mvp;
    }
}
