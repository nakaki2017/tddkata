package com.naka.tdd;


import org.junit.Test;
import static org.junit.Assert.assertEquals;



public class TennisTest {

    Tennis tennis = new Tennis();

    @Test
    public void loveAllWithRepo() {
        ScoreTextRepo stubRepo = new StubScoreTextRepo();
        tennis = new Tennis("Joseph", "Feng", stubRepo);
        assertEquals("AnotherLove All", tennis.getScore());
    }

    @Test
    public void loveAll() {
        assertEquals("Love All", tennis.getScore());
    }

    @Test
    public void fifteenLove() {

        addLeft(1);
        assertEquals("Fifteen Love", tennis.getScore());
    }

    @Test
    public void fifteenAll() {
        addLeft(1);
        addRight(1);
        assertEquals("Fifteen All", tennis.getScore());
    }

    @Test
    public void thirtyLove() {
        addLeft(2);
        assertEquals("Thirty Love", tennis.getScore());
    }

    @Test
    public void loveFifteen() {
        addRight(1);
        assertEquals("Love Fifteen", tennis.getScore());
    }


    @Test
    public void fortyLove() {
        addLeft(3);
        assertEquals("Forty Love", tennis.getScore());
    }

    @Test
    public void deuce() {
        addToDeuce();
        assertEquals("Deuce", tennis.getScore());
    }

    @Test
    public void deuceAgain() {
        addToDeuce();
        addLeft(1);
        addRight(1);
        assertEquals("Deuce", tennis.getScore());
    }

    @Test
    public void win() {
        tennis = new Tennis("Joseph", "Feng");
        addLeft(4);
        assertEquals("Joseph Win", tennis.getScore());
    }

    @Test
    public void win2() {
        tennis = new Tennis("Joseph", "Feng");
        addLeft(4);
        addRight(2);
        assertEquals("Joseph Win", tennis.getScore());
    }

    @Test
    public void rightWin() {
        tennis = new Tennis("Joseph", "Feng");
        addLeft(2);
        addRight(4);
        assertEquals("Feng Win", tennis.getScore());
    }

    private void addToDeuce() {
        addLeft(3);
        addRight(3);
    }

    private void addRight(int point) {
        for (int i = 0; i < point; i++) {
            tennis.addRight();
        }
    }

    private void addLeft(int point) {
        for (int i = 0; i < point; i++) {
            tennis.addLeft();
        }
    }


}
