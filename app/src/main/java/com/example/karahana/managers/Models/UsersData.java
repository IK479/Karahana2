package com.example.karahana.managers.Models;

import java.util.HashMap;

public class UsersData {

    private String email = "";
    private HashMap<String, User> usersDataBase = new HashMap<>();

    public UsersData(){}

    public String getEmail() {
        return email;
    }

    public UsersData setEmail(String email) {
        this.email = email;
        return this;
    }

    public HashMap<String, User> getUsersDataBase() {
        return usersDataBase;
    }

    public UsersData setUsersDataBase(HashMap<String, User> usersDataBase) {
        this.usersDataBase = usersDataBase;
        return this;
    }
}
