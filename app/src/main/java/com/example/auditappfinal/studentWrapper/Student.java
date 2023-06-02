package com.example.auditappfinal.studentWrapper;

import java.io.Serializable;

public class Student implements Serializable{

    String full_name, gender, std, division, email, UID, password_hash;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "full_name='" + full_name + '\'' +
                ", gender='" + gender + '\'' +
                ", std='" + std + '\'' +
                ", division='" + division + '\'' +
                ", email='" + email + '\'' +
                ", UID='" + UID + '\'' +
                ", password_hash='" + password_hash + '\'' +
                '}';
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public Student(String full_name, String gender, String std, String division, String email, String UID, String password_hash) {
        this.full_name = full_name;
        this.gender = gender;
        this.std = std;
        this.division = division;
        this.email = email;
        this.UID = UID;
        this.password_hash = password_hash;
    }
}
