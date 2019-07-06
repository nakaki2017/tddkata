package com.naka;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @description:
 * @author: susie
 * @create: 2019-05-02 17:00
 **/
public class SteamTest {

    @Test
    public void forEach(){
        // 使用Stream.forEach()迭代
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.forEach(str -> System.out.println(str));
    }
    @Test
    public void filter(){
        // 保留长度等于3的字符串
        Stream<String> stream= Stream.of("I", "love", "you", "too");
        stream.filter(str -> str.length()==3)
                .forEach(str -> System.out.println(str));
    }
    @Test
    public void distinct(){
        // 函数原型为Stream<T> distinct()，作用是返回一个去除重复元素之后的Stream。
        Stream<String> stream= Stream.of("I", "love", "you", "too", "too");
        stream.distinct()
                .forEach(str -> System.out.println(str));
    }
    @Test
    public void sorted(){
        // 排序函数有两个，一个是用自然顺序排序，一个是使用自定义比较器排序，函数原型分别为Stream<T>　sorted()和Stream<T>　sorted(Comparator<? super T> comparator)。
        // 输出按照长度升序排序后的字符串
        Stream<String> stream= Stream.of("I", "love", "you", "too");
        stream.sorted((str1, str2) -> str1.length()-str2.length())
                .forEach(str -> System.out.println(str));
    }
    @Test
    public void map(){
        // 函数原型为<R> Stream<R> map(Function<? super T,? extends R> mapper)，作用是返回一个对当前所有元素执行执行mapper之后的结果组成的Stream。
        // 直观的说，就是对每个元素按照某种操作进行转换，转换前后Stream中元素的个数不会改变，但元素的类型取决于转换之后的类型。
        // 输出原字符串的大写形式
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        stream.map(str -> str.toUpperCase())
                .forEach(str -> System.out.println(str));
    }
    @Test
    public void flatMap(){
        // 函数原型为<R> Stream<R> flatMap(Function<? super T,? extends Stream<? extends R>> mapper)，作用是对每个元素执行mapper指定的操作，并用所有mapper返回的Stream中的元素组成一个新的Stream作为最终返回结果。
        // 说起来太拗口，通俗的讲flatMap()的作用就相当于把原stream中的所有元素都"摊平"之后组成的Stream，转换前后元素的个数和类型都可能会改变。
        // 最终将输出1~5这5个数字
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1,2), Arrays.asList(3, 4, 5));
        stream.flatMap(list -> list.stream())
                .forEach(i -> System.out.println(i));
    }


}
