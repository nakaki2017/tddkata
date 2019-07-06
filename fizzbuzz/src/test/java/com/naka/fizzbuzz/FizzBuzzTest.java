package com.naka.fizzbuzz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @description: FizzBuzzTest
 * @author: susie
 * @create: 2019-04-23 13:08
 **/
public class FizzBuzzTest {

    /**
     * 第一阶段：
     * 编写一个程序，打印从1到100的数字。但是对于三个的倍数打印“Fizz”而不是数字和五个打印“Buzz”的倍数。对于三和五的倍数的数字打印“FizzBu​​zz”。
     * Write a program that prints the numbers from 1 to 100.
     * But for multiples of three print “Fizz” instead of the number and for the multiples of five print “Buzz”.
     * For numbers which are multiples of both three and five print “FizzBuzz “.
     */
    @Test
    public void print_100_with_fizzbuzz() throws Exception {
        for (int i = 1; i <= 100; i++) {
            System.out.println(FizzBuzz.fizzbuzz(i));
        }
    }


    @Test
    public void should_output_fizz_when_multiple_of_3() throws Exception {
        assertEquals("Fizz", FizzBuzz.fizzbuzz(3));
    }

    @Test
    public void should_output_buzz_when_multiple_of_5() throws Exception {
        assertEquals("Buzz", FizzBuzz.fizzbuzz(5));
    }

    @Test
    public void should_output_fizzbuzz_when_multiple_of_5_and_3() throws Exception {
        assertEquals("FizzBuzz", FizzBuzz.fizzbuzz(15));
    }

}