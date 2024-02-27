package com.example.myapplication;

import androidx.annotation.NonNull;

public class Person {

    String name, occupation, imageUrl;

    public Person() {

    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return name + " " +  occupation + "\n";
    }
}
