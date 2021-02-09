package com.eea.allensellshomes.Model;

public class User {
    private String email;
    private  String fullName;
    private  String number;
    private String password;

    public User(String email, String fullName, String number, String password) {
        this.email = email;
        this.fullName = fullName;
        this.number = number;
        this.password = password;
    }

    public User() {
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
