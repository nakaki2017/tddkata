package com.naka.fizzbuzz;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @description: ${description}
 * @author: susie
 * @create: 2019-04-23 13:07
 **/
public class FizzBuzzWhizz {
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";
    private static final String WHIZZ = "Whizz";

    private static int FIZZNUM;
    private static int BUZZNUM;
    private static int WHIZZNUM;

    public FizzBuzzWhizz(int num1, int num2, int num3) {
        FIZZNUM = num1;
        BUZZNUM = num2;
        WHIZZNUM = num3;
    }


    public String sayNum(int i) {
        String result = "";

        if (String.valueOf(i).indexOf(String.valueOf(FIZZNUM))!=-1) {
            result += FIZZ;
        }else{
            if (isMultipleWith(i, FIZZNUM)) {
                result += FIZZ;
            }
            if (isMultipleWith(i, BUZZNUM)) {
                result += BUZZ;
            }
            if (isMultipleWith(i, WHIZZNUM)) {
                result += WHIZZ;
            }
            if (result.length()==0) {

                result += i;
            }
        }
        return result;
    }

    private boolean isMultipleWith(int i, int divisor) {
        return i % divisor == 0;
    }
}
