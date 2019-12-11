package com.example.hieu.todoapp.models;

public class SignUp {
    String userName;
    String password;

    public SignUp() {

    }

    public SignUp(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "NguoiDung {" +
                "userName = '" + userName + '\'' +
                ", password = ' " + password + '\'' +
                '}';
    }
}
