package com.example.quiz1;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
    String id;
    String name;
    int age;
    char gender;
    float gpa;
    ArrayList<Course> courses;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public Student(String id, String name, int age, char gender, float gpa, ArrayList<Course> courses) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.gpa = gpa;
        this.courses = courses;
    }
}
