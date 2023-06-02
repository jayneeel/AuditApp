package com.example.auditappfinal.teacherWrapper;

public class Teacher {
    String full_name;
    String gender;
    String email;
    String UID;

    @Override
    public String toString() {
        return "Teacher{" +
                "full_name='" + full_name + '\'' +
                ", gender='" + gender + '\'' +
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

    public Teacher(String full_name, String gender, String email, String UID, String password_hash) {
        this.full_name = full_name;
        this.gender = gender;
        this.email = email;
        this.UID = UID;
        this.password_hash = password_hash;
    }

    String password_hash;


    public Teacher() {
    }
}
