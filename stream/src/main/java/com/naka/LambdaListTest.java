package com.naka;

import com.alibaba.fastjson.JSONObject;
import com.naka.model.Apple;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * List,Array操作
 *
 * @author: susie
 * @create: 2019-07-06 16:24
 **/
public class LambdaListTest {

    private List<Apple> appleList = new ArrayList<>();//存放apple对象集合

    {
        Apple apple1 = new Apple(1, "苹果1", new BigDecimal("3.25"), 10);
        Apple apple12 = new Apple(1, "苹果2", new BigDecimal("1.35"), 20);
        Apple apple2 = new Apple(2, "香蕉", new BigDecimal("2.89"), 30);
        Apple apple3 = new Apple(3, "荔枝", new BigDecimal("9.99"), 40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);
    }

    /**
     * @author: susie
     * @create: 2019-07-06 16:32
     **/
    @Test
    public void testList1() {
        Integer sum = appleList.stream().mapToInt(Apple::getNum).sum();//和
        OptionalInt max = appleList.stream().mapToInt(Apple::getNum).max();//最大
        OptionalInt min = appleList.stream().mapToInt(Apple::getNum).min();//最小
        OptionalDouble average = appleList.stream().mapToDouble(Apple::getNum).average();//平均值

        /* Java8 新特性：Optional 类

        Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。

        Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。

        Optional 类的引入很好的解决空指针异常。*/
        System.out.println(sum);
        System.out.println(max.getAsInt());
        System.out.println(min.getAsInt());
        System.out.println(average.getAsDouble());
    }

    /**
     * lambda循环遍历list，并改变list中对象的值
     *
     * @author: susie
     * @create: 2019-07-06 17:00
     **/
    @Test
    public void testList2() {
        Apple apple = Apple.create(Apple::new).builder().id(1).build();
        List<Apple> apples = Arrays.asList(apple);
        System.out.println(apples.toString());

        Apple a = Apple.create(Apple::new);
        appleList.forEach(a::follow);
        appleList.forEach(a::changeValue);
        //这种调外部方法的不能用，要使用外部方法改变list的对象值，请看ConsumerTest类的testConsumer方法
//        appleList.forEach(a -> changeValueOutside(apple));


    }


    /**
     * List转Map  toMap
     * id为key，apple对象为value，可以这么做：
     * <p>
     * List -> Map
     * 需要注意的是：
     * toMap 如果集合对象有重复的key，会报错Duplicate key ....
     * apple1,apple12的id都为1。
     * 可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
     *
     * @author: susie
     * @create: 2019-07-06 17:19
     */
    @Test
    public void testList3() {
        Map<Integer, Apple> appleMap = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a, (k1, k2) -> k1));
        //{1:{"id":1,"money":3.25,"name":"苹果1","num":10},2:{"id":2,"money":2.89,"name":"香蕉","num":30},3:{"id":3,"money":9.99,"name":"荔枝","num":40}}
        System.out.println(JSONObject.toJSONString(appleMap));

    }

    /**
     * 分组  groupingBy
     * List里面的对象元素，以某个属性来分组，例如，以id分组，将id相同的放在一起：
     *
     * @author: susie
     * @create: 2019-07-06 17:19
     **/
    @Test
    public void testList4() {
        //List 以ID分组 Map<Integer,List<Apple>>
        Map<Integer, List<Apple>> groupBy = appleList.stream().collect(Collectors.groupingBy(Apple::getId));

        System.err.println("groupBy:" + groupBy);
        //{1=[Apple{id=1, name='苹果1', money=3.25, num=10}, Apple{id=1, name='苹果2', money=1.35, num=20}], 2=[Apple{id=2, name='香蕉', money=2.89, num=30}], 3=[Apple{id=3, name='荔枝', money=9.99, num=40}]}
        System.out.println(JSONObject.toJSONString(groupBy));

    }

    /**
     * 过滤  filter
     * 从集合中过滤出来符合条件的元素(map只是覆盖属性，filter根据判断属性来collect宿主bean)
     *
     * @author: susie
     * @create: 2019-07-06 17:21
     **/
    public void testList5() {
        //过滤出符合条件的数据
        List<Apple> filterList = appleList.stream().filter(a -> a.getName().equals("香蕉")).collect(Collectors.toList());

        System.err.println("filterList:" + filterList);
        //[Apple{id=2, name='香蕉', money=2.89, num=30}]
        System.out.println(JSONObject.toJSONString(filterList));

    }

    /**
     * 求和（BigDecimal，Integer）
     *
     * @author: susie
     * @create: 2019-07-06 17:21
     **/
    @Test
    public void testList6() {
//        BigDecimal:
        //计算 总金额
        BigDecimal totalMoney = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.err.println("totalMoney:" + totalMoney); //totalMoney:17.48

//        Integer:
        //计算 数量
        int sum = appleList.stream().mapToInt(Apple::getNum).sum();
        System.err.println("sum:" + sum); //sum:100

        List<Integer> cc = new ArrayList<>();
        cc.add(1);
        cc.add(2);
        cc.add(3);
        int sum2 = cc.stream().mapToInt(Integer::intValue).sum();//6
        System.err.println("sum2:" + sum2); //sum:6


    }

    /**
     * 分组统计
     *
     * @author: susie
     * @create: 2019-07-06 17:38
     **/
    @Test
    public void testList7() {
        //1.分组计数
        List<Apple> list1 = Arrays.asList(new Apple(1, "苹果1", new BigDecimal("3.25"), 10), new Apple(1, "苹果2", new BigDecimal("1.35"), 20), new Apple(2, "香蕉", new BigDecimal("2.89"), 30), new Apple(3, "荔枝", new BigDecimal("9.99"), 40));
        System.out.println(list1);

        //1.1根据某个属性分组计数
        Map<Integer, Long> result1 = list1.stream().collect(Collectors.groupingBy(Apple::getId, Collectors.counting()));
        System.out.println(result1);
        //1.2根据整个实体对象分组计数,当其为String时常使用
        Map<Apple, Long> result2 = list1.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(result2);
        //1.3根据分组的key值对结果进行排序、放进另一个map中并输出
        Map<Integer, Long> xMap = new HashMap<>();
        result1.entrySet().stream().sorted(Map.Entry.<Integer, Long>comparingByKey().reversed()) //reversed不生效
                .forEachOrdered(x -> xMap.put(x.getKey(), x.getValue()));
        System.out.println(xMap);

        //2.分组，并统计其中一个属性值得sum或者avg:id总和
        Map<Integer, Integer> result3 = list1.stream().collect(
                Collectors.groupingBy(Apple::getId, Collectors.summingInt(Apple::getNum))
        );
        System.out.println(result3);


    }

    private void changeValueOutside(Apple another) {
        another.setNum(100);
        another.setName("水果");
        System.out.println("Following the " + another.toString());
    }

    /**
     * 1.supplier是个接口，有一个get()方法
     * <p>
     * 2.语法 ：
     * <p>
     * Supplier<TestSupplier> sup= TestSupplier::new;
     * 3.每次调用get()方法时都会调用构造方法创建一个新对象。
     */
//    @FunctionalInterface
//    public interface Supplier<T> {
//        T get();
//    }


}
