package com.example.question2_student_api.model;


public class Student {
    private long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    private double gpa;

    public Student(long studentId, String firstName, String lastName, String email, String major, double gpa){
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.major = major;
        this.gpa = gpa;
    }

    public long getId(){
        return this.studentId;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getMajor(){
        return this.major;
    }

    public double getGPA(){
        return this.gpa;
    }

    public void setId(long id){
        this.studentId = id;
    }

    public void setFirstName(String firstname){
        this.firstName = firstname;
    }

    public void setLastName(String lastname){
        this.lastName = lastname;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setMajor(String major){
        this.major = major;
    }

    public void setGPA(double gpa){
        this.gpa = gpa;
    }
}
