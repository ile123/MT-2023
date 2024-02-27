package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PersonList {
        @SerializedName(value = "persons")
        List<Person> osobe;

    public PersonList() {
        this.osobe = new ArrayList<Person>();
    }

    @Override
    public String toString() {
       String ispis = "";
       for (Person person : osobe){
           ispis += person;
       }
       return ispis;
    }
}
