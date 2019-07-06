package com.naka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.function.Supplier;

/**
 * @description:
 * @author: susie
 * @create: 2019-07-06 18:28
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Apple {


    private Integer id;
    private String name;
    private BigDecimal money;
    private Integer num;

    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Apple create(Supplier<Apple> supplier) {
        return supplier.get();
    }

    public void follow(Apple another) {
        System.out.println("Following the " + another.toString());
    }

    public void changeValue(Apple another) {
        another.setNum(100);
        another.setName("水果");
        System.out.println("Following the " + another.toString());
    }


}
