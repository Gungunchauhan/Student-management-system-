package com.student;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "marks")
    private double marks;

    public Student() {}

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getters and setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double getMarks() { return marks; }

    public void setMarks(double marks) { this.marks = marks; }

    @Override
    public String toString() {
        return "ID=" + id + ", Name='" + name + "', Marks=" + marks;
    }
}
