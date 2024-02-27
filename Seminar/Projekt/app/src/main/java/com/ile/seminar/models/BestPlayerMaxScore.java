package com.ile.seminar.models;

import androidx.annotation.NonNull;

public class BestPlayerMaxScore {
    private String number;
    private final String name;
    private final String totalScore;
    private final Long numberOfGames;

    public BestPlayerMaxScore(String number, String name, String totalScore, Long numberOfGames) {
        this.number = number;
        this.name = name;
        this.totalScore = totalScore;
        this.numberOfGames = numberOfGames;
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

    public Long getNumberOfGames() {
        return numberOfGames;
    }

    @NonNull
    @Override
    public String toString() {
        return "\nRanking: " + this.number + "\nName: " + this.name + "\nTotal Score: " + this.totalScore + "\nNumber Of Games: " + this.numberOfGames;
    }
}
