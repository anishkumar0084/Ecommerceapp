package com.ecommericeapp;

public class User {

    public String name,email,password,mobile;


    public User(String name, String email,String password,String mobile) {
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
    }

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }





    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMobile() {
        return mobile;
    }




}
