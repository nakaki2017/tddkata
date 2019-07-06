package com.naka.fizzbuzz;

/**
 * @description: ${description}
 * @author: susie
 * @create: 2019-04-23 13:07
 **/
public class FizzBuzz {
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";


    public static String fizzbuzz(int i) {
        if (i % 3 == 0 && i % 5 == 0) {
            return FIZZ+BUZZ;
        }
        if (i % 3 == 0) {
            return FIZZ;
        }
        if (i % 5 == 0) {
            return BUZZ;
        }
        return String.valueOf(i);
    }
}
