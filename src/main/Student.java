/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author tana_
 */
public class Student {
    
    private int id;
    private String name;
    private double gpa;
    
    public Student() {
    }
    
    public Student(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.gpa = salary;
    }
    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
