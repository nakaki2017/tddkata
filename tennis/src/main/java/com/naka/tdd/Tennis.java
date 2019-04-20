package com.naka.tdd;

public class Tennis {

    private static final int SET_POINT = 3;
    String[] scoreText = {"Love", "Fifteen", "Thirty", "Forty"};
    private ScoreTextRepo scoreTextRepo;
    private String rightName;
    private String leftName;
    private int leftScore;
    private int rightScore;


    public Tennis() {
    }

    public Tennis(String leftName, String rightName) {

        this.leftName = leftName;
        this.rightName = rightName;
    }


    public Tennis(String leftName, String rightName, ScoreTextRepo scoreTextRepo) {
        this.leftName = leftName;
        this.rightName = rightName;
        this.scoreTextRepo = scoreTextRepo;
    }

    public String getScore() {
        if (scoreTextRepo != null && leftScore == 0 && rightScore == 0) {
            return scoreTextRepo.findAllText()[0] + " All";
        }

        if (leftScore == 4 && rightScore < SET_POINT) {
            return leftName + " Win";
        }
        if (rightScore == 4 && leftScore < SET_POINT) {
            return rightName + " Win";
        }
        if (leftScore == rightScore && rightScore >= SET_POINT) {
            return "Deuce";
        }
        if (leftScore != rightScore) {
            return scoreText[leftScore] + " " + scoreText[rightScore];
        }

        return scoreText[leftScore] + " All";
    }

    public void addLeft() {
        leftScore++;
    }

    public void addRight() {
        rightScore++;
    }
}
