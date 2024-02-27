package com.ile.seminar.models;

public class PlayerScore {
    private final String number;
    private final String correctAnswers;
    private final String finalScore;

    public PlayerScore(String number, String correctAnswers, String finalScore) {
        this.number = number;
        this.correctAnswers = correctAnswers;
        this.finalScore = finalScore;
    }

    public String getNumber() {
        return number;
    }

    public String getCorrectAnswers() {
        return correctAnswers;
    }

    public String getFinalScore() {
        return finalScore;
    }
}
