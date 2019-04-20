package com.naka.bowling;

import lombok.Data;

/**
 * @description: 保龄球积分规则实现
 * @author: susie
 * @create: 2019-04-19 14:54
 **/
@Data
public class Game {

    private int score = 0;
    // 前9轮每轮可以打1-2次
    // 第十轮有机会3次
    // 因此最多21次
    private int[] rollScore = new int[21];
    // 标识当前第几次
    private int rollIndex = 0;

    // 共10轮
    private int[] frameScore = new int[10];
    // 标识当前第几轮
    private int frameIndex = 0;


    public void roll(int pins) {
        rollScore[rollIndex++] = pins;
    }

    public int score() {
        int i = 0;
        for (int frame = 0; frame < 10; frame++) {

            if (isStrike(i)) {//strike
                score += 10 + strikeBonus(i);
                i++;
            } else if (isSpare(i)) {//spare
                score += 10 + spareBonus(i);
                i += 2;
            } else {
                score += sumFrameScore(i, i + 1);
                i += 2;
            }

        }


        return score;
    }

    private int sumFrameScore(int i, int i2) {
        return rollScore[i] + rollScore[i2];
    }

    private int strikeBonus(int i) {
        return sumFrameScore(i + 1, i + 2);
    }

    private int spareBonus(int i) {
        return rollScore[i + 2];
    }

    private boolean isStrike(int i) {
        return rollScore[i] == 10;
    }

    private boolean isSpare(int rollIndex) {
        return sumFrameScore(rollIndex, rollIndex + 1) == 10;
    }

}