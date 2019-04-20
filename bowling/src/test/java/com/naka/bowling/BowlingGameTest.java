package com.naka.bowling;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @description: bowling kata
 * <p>
 * Bob大叔的保龄球训练
 * 这是一道计算保龄球比赛一局总得分的编程题，保龄球的计分规则非常简单：
 * <p>
 * 每一局总共有十轮，每轮一开始会有十支球瓶，球手可以扔两次球，目标就是用尽量少的球把全部球瓶击倒。
 * 如果第一球就把全部的球瓶都击倒了，也就是STRIKE，画面出现“X”，就算完成一轮了，所得分数是10分再加后面两球的倒瓶数，
 * 如果第一球没有全倒，就要再打一球，如果第二球将剩下的球瓶全都击倒，也就是SPARE，画面出现“/”，也算完成一格，所得分数为10分再加下一格第一球的倒瓶数，
 * 如果第二球也没有把球瓶全部击倒的话，那分数就是第一球加第二球倒的瓶数，没有奖励（bonus），再接着打下一格。依此类推。
 * 第十轮有机会扔三次球。如果在第十轮出现STRIKE或者SPARE，则球手可再加打第三球。
 * 全部十轮的得分相加就等于这一局的总得分。
 * <p>
 * 题目要求我们提供一个名字为Game的类，这个类有两个方法：
 * <p>
 * roll(pins : int)：每次球员扔球后执行这个方法，入参是此次扔球击倒的球瓶数量。
 * score()：每局比赛结束时执行的方法，返回这局比赛的总得分。
 * <p>
 * <p>
 * <p>
 * 1、 保龄球比赛以局为单位，一局(one game)分10格，一格(one frame)分2节(block)。
 * <p>
 * 2、 保龄球按顺序每格允许投2个球（第10格例外），投完10格为1局。每击倒1个木瓶(
 * pin)得1分，投完一格将两个球的"所得分"相加，记为该格的"应得分"，10格依次累计为
 * 全局的总分。
 * <p>
 * 3、 保龄球运动有统一格式的记分表。每一格的第一球将全部木瓶击倒时，称为全中(s
 * trike)，应在记分表上部的左边小格内用符号"×"表示，该格所得分为10分；第二球不
 * 得再投，但按规则应奖励下两个球的所得分。以上所得分之和记为该轮的应得分。
 * <p>
 * 4、 连续两次全中计分器将显示(double)；连续三次全中计分器将显示(triple)。
 * <p>
 * 5、 每一格的第一球击倒部分木瓶时，应在计分表上部的左边小格内记上被击倒的木瓶
 * 数，作为第一球的所得分；如果第二球将剩余木瓶全部击倒，则称为补中(spare)，应在
 * 记分表上部的右边小格内用符号"/"表示。该轮所得分亦为10分，且按规则应奖励下一个
 * 球的所得分。以上所得分之和记为该轮的应得分。
 * <p>
 * 6、 在每一格的第一球击倒部分木瓶的情况下，如果第二球投完后还剩下几只木瓶未被
 * 击倒，叫做失误(error)。
 * <p>
 * 7、 如果每一格的第一球投完后剩余的木瓶不是紧挨着放置而是分离(split)的，那么第
 * 二球想补中就有一定难度，俗称"技术球"。
 * <p>
 * 8、 第10格有特别规定。投成失误时，仅可投两球；打出全中时，应在同一条球道上继
 * 续投完最后两个球结束全局，这两个球的所得分应累计在该局总分内；打出补中时，应
 * 在同一条球道上继续投完最后一个球结束全局，这一个球的所得分应累计在该局总分内
 * 。
 * <p>
 * 9、 如果投出去的球滚进边沟，则计分器将显示(gutter)。
 * <p>
 * 10、当计分表上部的小格内出现F时，表明该球犯规(foul)。
 *
 * @author: susie
 * @create: 2019-04-19 13:01
 **/
public class BowlingGameTest {

    Game bowlingGame = new Game();

    private void rollMany(int pins, int turns) {
        for (int i = 0; i < turns; i++) {
            bowlingGame.roll(pins);
        }
    }

    /**
     * 前9轮每轮可以打1-2次
     * 第十轮有机会3次
     * <p>
     * 每次都是0分，扔20次结束，得分0分
     */
    @Test
    public void testZeroAll() {
        rollMany(0, 20);
        assertEquals(0, bowlingGame.score());
    }

    @Test
    public void testOneAll() {
        rollMany(1, 20);
        assertEquals(20, bowlingGame.score());
    }


    /**
     * 补中
     * <p>
     * 5,/,3,0000000
     * 16
     * <p>
     * 10+下一次的3
     */
    @Test
    public void testSpareInFirstFrame() {
        rollMany(5, 1);
        rollMany(5, 1);
        rollMany(3, 1);
        rollMany(0, 17);
        assertEquals(16, bowlingGame.score());
    }

    /**
     * 全中
     * <p>
     * X,2,3,0000000
     * 20
     * <p>
     * 10+2+3  ，5
     */
    @Test
    public void testStrikeInFirstFrame() {
        rollMany(10, 1);
        rollMany(2, 1);
        rollMany(3, 1);
        rollMany(0, 16);
        assertEquals(20, bowlingGame.score());
    }

    @Test
    public void testPerfectGame() {
        rollMany(10, 12);
        assertEquals(300, bowlingGame.score());
    }


    @Test
    public void testGame() {
        bowlingGame.roll(1);
        bowlingGame.roll(4);
        bowlingGame.roll(4);
        bowlingGame.roll(5);
        bowlingGame.roll(6);
        bowlingGame.roll(4);
        bowlingGame.roll(5);
        bowlingGame.roll(5);
        bowlingGame.roll(10);
        bowlingGame.roll(0);
        bowlingGame.roll(1);
        bowlingGame.roll(7);
        bowlingGame.roll(3);
        bowlingGame.roll(6);
        bowlingGame.roll(4);
        bowlingGame.roll(10);
        bowlingGame.roll(2);
        bowlingGame.roll(8);
        bowlingGame.roll(6);
        assertEquals(133, bowlingGame.score());
    }


    @Test
    public void testNineZero() {
        for (int i = 0; i < 10; i++) {
            bowlingGame.roll(9);
            bowlingGame.roll(0);
        }
        assertEquals(90,bowlingGame.score());
    }

    @Test
    public void testFiveAll() {
        for (int i = 0; i < 21; i++) {
            bowlingGame.roll(5);
        }
        assertEquals(150,bowlingGame.score());
    }

}
