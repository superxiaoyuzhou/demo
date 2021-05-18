package com.example.lambda.test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 明日复明日,明日何其多
 *
 * 每天都是新的一天
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        Stream<String> stream1 = list.stream();
        //filter操作用于数据过滤。
        List<String> filtered = list.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered);
        Stream<String> stream = list.stream();
        list.forEach(System.out::print);
        System.out.println();
        stream.forEach(System.out::print);

        list = Arrays.asList("1", "2", "333", "2", "5", "6");
        //map方法可以将一种类型的值转换成另外一种类型，map 操作就可以使用该函数，将一个流中的值转换成一个新的流。其实就是就是实现一个Function接口。
        List<Integer> list1 = list.stream().map(str -> Integer.parseInt(str)).collect(Collectors.toList());
        System.out.println("map:" + list1);

        //flatMap对于stream，   两者的输入都是stream的每一个元素，map的输出对应一个元素，必然是一个元素（null也是要返回），flatmap是0或者多个元素（为null的时候其实就是0个元素）。
        //flatmap的意义在于，一般的java方法都是返回一个结果，但是对于结果数量不确定的时候，用map这种java方法的方式，是不太灵活的，所以引入了flatmap。
        list = list.stream().map(word -> word.split("")).flatMap(str -> Arrays.stream(str)).collect(Collectors.toList());
        System.out.println("flatMap:" + list);

        //distinct操作用于数据去重。distinct使用hashCode和equals方法来获取不同的元素。因此，我们的类必须实现hashCode和equals方法。
        list1 = list1.stream().distinct().collect(Collectors.toList());
        System.out.println("distinct" + list1);

        //sorted操作用于以自然序排序,也可指定排序规则
        list1 = list1.stream().sorted((i,j)->-1).collect(Collectors.toList());
        //与上一句代码一样,可见Lambda表达式的简介代码的作用
        list1 = list1.stream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -1;
            }
        }).collect(Collectors.toList());
        System.out.println("sorted:" + list1);

        //peek接收一个没有返回值的λ表达式，可以做一些输出，外部处理等。
        // map接收一个有返回值的λ表达式，之后Stream的泛型类型将转换为map参数λ表达式返回的类型。和forEach是有所区别的。
        //peek是中间操作,forEach是终端操作
        Stream.of("one", "two", "three", "four").peek(e -> System.out.println(e));
        Stream.of("one", "two", "three", "four").peek(e -> System.out.println(e)).collect(Collectors.toList());

        //limit对一个Stream进行截断操作，获取其前N个元素，如果原Stream中包含的元素个数小于N，那就获取其所有的元素；
        list1 = list1.stream().limit(3).collect(Collectors.toList());
        System.out.println("limit:" + list1);

        //skip: 返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream；
        list1 = list1.stream().skip(1).collect(Collectors.toList());
        System.out.println("skip:" +  list1);

        //forEach作用是对容器中的每个元素执行action指定的动作，也就是对元素进行遍历。
        list1.stream().forEach(i->System.out.println("forEach:" + i));

        //reduce 操作可以实现从一组值中生成一个值。在上述例子中用到的 count、min 和 max 方法，因为常用而被纳入标准库中。
        Integer isum = list1.stream().reduce(0,(sum,i)-> sum + i); //求和
        System.out.println("reduce:" + isum);

        //mapToInt 转换类型
        int sum = list1.stream().mapToInt(i -> i ).sum();   //求和

        //max及min求最大值和最小值，此时max及min接受的是Comparator<? super T> comparator
        Integer max = list1.stream().max(Comparator.comparing(i->i)).get();
        System.out.println("max:" + max);

        //count
        long count = list1.stream().count();

        //collect也就是收集器，是Stream一种通用的、从流生成复杂值的结构。只要将它传给collect方法，也就是所谓的转换方法，其就会生成想要的数据结构。这里不得不提下，Collectors这个工具库，在该库中封装了相应的转换方法。当然，Collectors工具库仅仅封装了常用的一些情景，如果有特殊需求，那就要自定义了。
        //方法	用途
        //toList	把结果转换为一个List
        //toSet	把结果转换为一个Set
        //groupingBy	分类用，例如按某个属性分类转换为一个map
        //toMap
        List<User> list2 = Arrays.asList(new User("aaa",11),new User("aaa",111), new User("aaa",20), new User("bbb",20), new User("ccc",22), new User("ddd",22), new User("eee",22));
        Map<Integer, List<User>> listMap = list2.stream().collect(Collectors.groupingBy(user -> user.getAge()));//按年龄分组
        System.out.println("collect:" + listMap);

        //anyMatch：匹配上任何一个则返回 Boolean
        //allMatch：匹配所有的元素则返回 Boolean
        //noneMatch跟allMatch相反，判断条件里的元素，所有的都不是，返回true
        //Optional<T>是一个可以包含或不可以包含非空值的容器对象，
        boolean a = list2.stream().allMatch(user -> user.getAge() == 22);   //是否包含年龄为22的
        boolean b = list2.stream().allMatch(user -> user.getAge() == 22);   //是否所有年龄都为22
        System.out.println("anyMatch:" + a);
        System.out.println("allMatch:" + b);

        int[] nums = {1,2,3,4};
        int num = IntStream.of(nums).max().getAsInt();  //求最小值
        System.out.println(num);
        //map是中间操作(返回stream的操作),sum是终止操作
        int sum1 = IntStream.of(nums).map(i -> i * 2).sum();
        //惰性求值:在没有调用终止操作的情况下,中间操作不会执行,如:
        IntStream.of(nums).map(StreamDemo::doubleNum);

        //Stream流创建的方式
        //1.Collection.stream/parallelStream
        Stream<Object> stream2 = new ArrayList<>().stream();
        Stream<Object> stream3 = new ArrayList<>().parallelStream();
        //2.Arrays.stream
        Stream<Object> stream4 = Arrays.stream(new Object[]{});
        //3.数字Stream,如:IntStream.range/rangeClosed/of等
        new Random().ints().limit(10);  //创建一个无限流,限制产生10个元素
        //4.自己创建 Stream.generate/iterate
//        Stream.generate(() -> "1").forEach(System.out::println);    //无限流
        Stream.generate(() -> "1").limit(3).forEach(System.out::println);    //无限流,限制产生3个元素

        //Stream流的中间操作
        //无状态操作
        //有状态操作

    }

    public static int doubleNum(int i) {
        System.out.println("执行乘2");
        return i*2;
    }
}
