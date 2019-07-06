package com.naka;

import com.naka.model.Apple;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * @description:
 * @author: susie
 * @create: 2019-07-06 18:30
 **/
public class ConsumerTest {
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
     * Consumer
     * 消费某个对象
     * <p>
     * lambda循环遍历list，并改变list中对象的值
     * <p>
     *
     * @author: susie
     * @create: 2019-07-06 17:00
     **/
    @Test
    public void testConsumer() {
        // 调外部方法使用lambda改list里对象的值
        appleList.forEach(new AppleConsumer());
        System.out.println(appleList);
    }

    /**
     * Predicate
     * 判断对象是否符合某个条件
     *
     * @author: susie
     * @create: 2019-07-06 18:14
     **/
    @Test
    public void testPredicate() {
        // 调外部方法使用lambda改list里对象的值
        appleList.forEach(new AppleMoneyConsumer());
        System.out.println(appleList);
    }

    /**
     * Function
     * 1 作用
     * 实现一个”一元函数“，即传入一个值经过函数的计算返回另一个值。
     * 2 使用场景
     * V HashMap.computeIfAbsent(K , Function<K, V>) // 简化代码，如果指定的键尚未与值关联或与null关联，使用函数返回值替换。
     * <R> Stream<R> map(Function<? super T, ? extends R> mapper); // 转换流
     * 3 设计思想
     * 一元函数的思想，将转换逻辑提取出来，解耦合
     *
     * @author: susie
     * @create: 2019-07-06 18:14
     **/
    @Test
    public void testFunction() {
        String result = printMoney(appleList, new MoneyFunction());
        System.out.println(result);
    }

    /**
     * UnaryOperator
     * <p>
     * 1 作用
     * UnaryOperator继承了Function，与Function作用相同
     * 不过UnaryOperator，限定了传入类型和返回类型必需相同
     * 2 使用场景
     * List.replaceAll(UnaryOperator) // 该列表的所有元素替换为运算结算元素
     * Stream.iterate(T,UnaryOperator) // 重复对seed调用UnaryOperator来生成元素
     *
     * @author: susie
     * @create: 2019-07-06 18:48
     **/
    @Test
    public void testUnaryOperator() {
        //都涨价5块钱
        addMoney(appleList, new MoneyUnaryOperator(new BigDecimal("5")));
        System.out.println(appleList);
    }

    private String printMoney(List<Apple> appleList, Function<Apple, String> function) {
        String[] ints = new String[appleList.size()];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = function.apply(appleList.get(i));
        }
        return Arrays.toString(ints);
    }

    private void addMoney(List<Apple> appleList, UnaryOperator<Apple> operator) {
        for (int i = 0; i < appleList.size(); i++) {
            appleList.set(i, operator.apply(appleList.get(i)));
        }
    }

    /**
     * Supplier
     * 1 作用
     * 创建一个对象（工厂类）
     * 2 使用场景
     * Optional.orElseGet(Supplier<? extends T>)：当this对象为null，就通过传入supplier创建一个T返回。
     * 3 设计思想
     * 封装工厂创建对象的逻辑
     *
     * @author: susie
     * @create: 2019-07-06 18:14
     **/
    @Test
    public void testSupplier() {
        Supplier<Apple> supplier = () -> new Apple();
        Apple a1 = supplier.get();
        a1.setName("test1");
        Apple a2 = supplier.get();
        a2.setName("test2");
        System.out.println("employee1:" + a1);
        System.out.println("employee2:" + a2);
    }

    private static class AppleConsumer implements Consumer<Apple> {

        /**
         * Performs this operation on the given argument.
         *
         * @param apple the input argument
         */
        @Override
        public void accept(Apple apple) {
            if (apple.getId() == 1) {
                apple.setId(99);
            }
        }
    }

    private static class AppleMoneyConsumer implements Consumer<Apple> {

        /**
         * Performs this operation on the given argument.
         *
         * @param apple the input argument
         */
        @Override
        public void accept(Apple apple) {
            // 苹果金额大于2减1块钱
            if (new AppleMoneyPredicate(new BigDecimal("3")).test(apple)) {
                apple.setMoney(apple.getMoney().subtract(new BigDecimal("1")));
                System.out.println(apple.getName() + "要减1元.");
            }
        }
    }

    static class AppleMoneyPredicate implements Predicate<Apple> {
        private BigDecimal tax;

        AppleMoneyPredicate(BigDecimal tax) {
            this.tax = tax;
        }

        @Override
        public boolean test(Apple apple) {
            return apple.getMoney().compareTo(tax) == 1;
        }
    }

    private class MoneyFunction implements Function<Apple, String> {

        /**
         * Applies this function to the given argument.
         *
         * @param apple the function argument
         * @return the function result
         */
        @Override
        public String apply(Apple apple) {
            System.out.println("Money:" + apple.getMoney().toString());
            return "Money:" + apple.getMoney().toString();
        }
    }

    private class MoneyUnaryOperator implements UnaryOperator<Apple> {
        private BigDecimal adjust;

        MoneyUnaryOperator(BigDecimal adjust) {
            this.adjust = adjust;
        }


        /**
         * Applies this function to the given argument.
         *
         * @param apple the function argument
         * @return the function result
         */
        @Override
        public Apple apply(Apple apple) {
            apple.setMoney(apple.getMoney().add(adjust));
            return apple;
        }
    }
}
