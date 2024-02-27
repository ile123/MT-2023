package com.ile.seminar.models;

public class BestPlayer {
    private String number;
    private final String name;
    private final String totalScore;

    public BestPlayer(String number, String name, String totalScore) {
        this.number = number;
        this.name = name;
        this.totalScore = totalScore;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getTotalScore() {
        return totalScore;
    }
}
