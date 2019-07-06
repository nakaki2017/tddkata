package com.naka;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * 参考 https://www.cnblogs.com/CarpenterLee/p/5936664.html
 * @author: susie
 * @create: 2019-05-02 17:43
 **/
public class LambdaTest {


    /**
     * 从一个字符串列表中选出以数字开头的字符串并输出
     */
    @Test
    public void lambda1() {

        //origin
        List<String> list = Arrays.asList("1one", "two", "three", "4four");
        for (String str : list) {
            //Character.isDigit 判断是否为数字
            //Character.isLetter 判断是否为字母
            if (Character.isDigit(str.charAt(0))) {
                System.out.println(str);
            }
        }


        // lambda
        List<String> listLambda = Arrays.asList("1one", "two", "three", "4four");
        listLambda.stream()// 1.得到容器的Steam
                .filter(str -> Character.isDigit(str.charAt(0)))// 2.选出以数字开头的字符串
                .forEach(str -> System.out.println(str));// 3.输出字符串
    }


    /**
     * 从一个字符串列表中，选出所有不以数字开头的字符串，将其转换成大写形式，并把结果放到新的集合当中。
     */
    @Test
    public void lambda2() {
        List<String> list = Arrays.asList("1one", "two", "three", "4four");
        Set<String> newList =
                list.stream()// 1.得到容器的Stream
                        .filter(str -> !Character.isDigit(str.charAt(0)))// 2.选出不以数字开头的字符串
                        .map(String::toUpperCase)// 3.转换成大写形式
                        .collect(Collectors.toSet());// 4.生成结果集

        //通过这个例子我们看到了Stream链式操作，即多个操作可以连成一串。
        // 不用担心这会导致对容器的多次迭代，因为不是每个Stream的操作都会立即执行。
        // Stream的操作分成两类，一类是中间操作(intermediate operations)，另一类是结束操作(terminal operation)，只有结束操作才会导致真正的代码执行，中间操作只会做一些标记，表示需要对Stream进行某种操作。
        // 这意味着可以在Stream上通过关联多种操作，但最终只需要一次迭代。如果你熟悉Spark RDD
    }



}
