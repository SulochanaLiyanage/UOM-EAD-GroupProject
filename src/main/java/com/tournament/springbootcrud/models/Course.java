package com.tournament.springbootcrud.models;

import javax.persistence.*;

@Entity
@Table(name="Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String name;
    private int hours;

    public Course() {
    }

    public Course(String name, int hours) {
        this.name = name;
        this.hours = hours;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
