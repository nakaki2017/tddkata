package com.naka.fizzbuzz;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @description: FizzBuzzTest
 * @author: susie
 * @create: 2019-04-23 13:08
 **/
public class FizzBuzzWhizzTest {

    FizzBuzzWhizz stu = new FizzBuzzWhizz(3,5,7);

    /**
     * 第2阶段 - FizzBuzzWhizz
     * <p>
     * <p>
     * 你是一名体育老师，在某次课距离下课还有五分钟时，你决定搞一个游戏。此时有100名学生在上课。游戏的规则是：
     * <p>
     * 1. 你首先说出三个不同的特殊数，要求必须是个位数，比如3、5、7。
     * 2. 让所有学生拍成一队，然后按顺序报数。
     * 3. 学生报数时，如果所报数字是第一个特殊数（3）的倍数，那么不能说该数字，而要说Fizz；如果所报数字是第二个特殊数（5）的倍数，那么要说Buzz；如果所报数字是第三个特殊数（7）的倍数，那么要说Whizz。
     * 4. 学生报数时，如果所报数字同时是两个特殊数的倍数情况下，也要特殊处理，比如第一个特殊数和第二个特殊数的倍数，那么不能说该数字，而是要说FizzBuzz, 以此类推。如果同时是三个特殊数的倍数，那么要说FizzBuzzWhizz。
     * 5. 学生报数时，如果所报数字包含了第一个特殊数，那么也不能说该数字，而是要说相应的单词，比如本例中第一个特殊数是3，那么要报13的同学应该说Fizz。如果数字中包含了第一个特殊数，那么忽略规则3和规则4，比如要报35的同学只报Fizz，不报BuzzWhizz。
     * <p>
     * 初看题目，很多人会以为就是求个算法，其实不然，说白了算法很简单，先判断第5规则，再判断第3，第4，其实3和4可以利用字符串拼接，无需重复来进行判断。我们逐一来看题目吧。
     * <p>
     * 1、你首先说出三个不同的特殊数，要求必须是个位数，比如3、5、7
     * 这是我们的输入，3个特殊数，必须为个位数。因为是根据报数，肯定n > 0，我们的特殊数m 也肯定必须>0，根据题目3,4，我觉得可以把1排除在外，当然也可以包含在其中，每个人理解不一样吧。因为是个位数，所以1<m<10。因为对m有要求，所以必须对我们3个特殊数进行一个验证。
     * <p>
     * 2、让所有学生拍成一队，然后按顺序报数
     * 这个简单，循环到100，我看到有朋友用了for(int i = 1;i<101;i++) ，不是不可以，但题目写了100个学生，为何你要去<101呢？<=100就可以了，尽量符合题目。
     * <p>
     * 3、学生报数时，如果所报数字是第一个特殊数（3）的倍数，那么不能说该数字，而要说Fizz；如果所报数字是第二个特殊数（5）的倍数，那么要说Buzz；如果所报数字是第三个特殊数（7）的倍数，那么要说Whizz。
     * <p>
     * 4. 学生报数时，如果所报数字同时是两个特殊数的倍数情况下，也要特殊处理，比如第一个特殊数和第二个特殊数的倍数，那么不能说该数字，而是要说FizzBuzz, 以此类推。如果同时是三个特殊数的倍数，那么要说FizzBuzzWhizz。
     * 3和4的规则可以通过字符串拼接，合并在一起
     * <p>
     * if (m % num1 == 0) str += “Fizz”;
     * <p>
     * if (m % num2 == 0) str += “Buzz”;
     * <p>
     * if (m % num3 == 0) str += “Whizz”;
     * <p>
     * 这些都是个人喜爱问题，你要一条条去验证判断，也没什么问题。
     * <p>
     * 5、学生报数时，如果所报数字包含了第一个特殊数，那么也不能说该数字，而是要说相应的单词，比如本例中第一个特殊数是3，那么要报13的同学应该说Fizz。如果数字中包含了第一个特殊数，那么忽略规则3和规则4，比如要报35的同学只报Fizz，不报BuzzWhizz。
     * 这条规则很特殊，所以我们的计算必须以if（5）else（3,4）进行，5的权重最高。
     * <p>
     * 说完了规则，来看下其他的要求：
     * <p>
     * 代码要求：
     * <p>
     * 1，语言不限，Java, C#, Ruby, C++, Js, Python, Scala, objective-C统统可以，小语种也没问题，只要你擅长；
     * <p>
     * 2，强烈建议写单元测试；
     * <p>
     * 3，请展示出你超赞的面向对象/函数式编程功底；
     * <p>
     * 4，建议尽量减少圈复杂度；
     * <p>
     * 5，请提交可运行的代码，及相关构建脚本/说明文档（代码运行平台和环境）；
     * <p>
     * 2-4的要求非常重要，因为这是区别程序员级别的一个标准。测试代码尽量全面。
     */


    /**
     * 你首先说出三个不同的特殊数，要求必须是个位数，比如3、5、7。
     */
    @Test
    public void saynum() throws Exception {

        for (int i = 1; i <= 100 ; i++) {
            System.out.println(stu.sayNum(i));
        }
    }

    /**
     * 规则三
     * 学生报数时，如果所报数字是第一个特殊数（3）的倍数，那么不能说该数字，而要说Fizz；
     * 如果所报数字是第二个特殊数（5）的倍数，那么要说Buzz；
     * 如果所报数字是第三个特殊数（7）的倍数，那么要说Whizz。
     */
    @Test
    public void should_output_fizz_when_multiple_of_3() {
        assertEquals("Fizz", stu.sayNum(6));
    }
    @Test
    public void should_output_fizz_when_multiple_of_5() {
        assertEquals("Buzz", stu.sayNum(20));
    }
    @Test
    public void should_output_fizz_when_multiple_of_7() {
        assertEquals("Whizz", stu.sayNum(14));
    }


    /**
     * 规则四
     * 学生报数时，如果所报数字同时是两个特殊数的倍数情况下，也要特殊处理。
     * 比如第一个特殊数和第二个特殊数的倍数，那么不能说该数字，而是要说FizzBuzz, 以此类推。
     * 如果同时是三个特殊数的倍数，那么要说FizzBuzzWhizz。
     */
    @Test
    public void should_output_fizzbuzz_when_multiple_of_3_5() throws Exception {
        assertEquals("FizzBuzz", stu.sayNum(15));
    }
    @Test
    public void should_output_fizzwhizz_when_multiple_of_3_7() throws Exception {
        assertEquals("FizzWhizz", stu.sayNum(21));
    }

    /**
     * 在做到第四个规则的时候，35逻辑应该输出BuzzWhizz
     * 但是第五个规则优先级较高，35应输出Fizz
     * 因此不能用35做测试用例
     */
    @Test
    public void should_output_fizzwhizz_when_multiple_of_5_7() throws Exception {
        assertEquals("BuzzWhizz", stu.sayNum(175));
    }

    @Test
    public void should_output_fizzbuzzwhizz_when_multiple_of_3_5_7() throws Exception {
        assertEquals("FizzBuzzWhizz", stu.sayNum(105));
    }



    /**
     * 规则五
     * 学生报数时，如果所报数字包含了第一个特殊数，那么也不能说该数字，而是要说相应的单词。
     * 比如本例中第一个特殊数是3，那么要报13的同学应该说Fizz。
     * 如果数字中包含了第一个特殊数，那么忽略规则3和规则4，比如要报35的同学只报Fizz，不报BuzzWhizz。
     */

    @Test
    public void should_output_fizz_when_it_has_3() throws Exception {
        assertEquals("Fizz", stu.sayNum(37));
        assertEquals("Fizz", stu.sayNum(23));
        assertEquals("Fizz", stu.sayNum(35));
    }
}