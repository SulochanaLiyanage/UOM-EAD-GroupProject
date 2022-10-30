package com.tournament.springbootcrud.models;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private String Name;
    private int Age;
    private String Subject;

    public Teacher() {
    }

    public Teacher(String name, int age, String subject) {
        Name = name;
        Age = age;
        Subject = subject;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
     public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
     public String getSubject() {
        return Subject;
    }

    public void setSubject(String grade) {
        Subject = subject;
    }
}   
