package com.ile.vjezba4.entities;

import androidx.annotation.NonNull;

public class Subject {
    private String id;
    private String name;
    private String professor;
    private long year;

    public Subject() {

    }

    public Subject(String id, String name, String professor, long year) {
        this.id = id;
        this.name = name;
        this.professor = professor;
        this.year = year;
    }

    public long getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return "\nId: " + this.id + "\nName: " + this.name + "\nYear: " + this.year + "\nProfessor: " + this.professor;
    }
}
