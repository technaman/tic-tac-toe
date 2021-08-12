package com.ngdev;

import java.util.ArrayList;

public class User {

    private int userId;
    private String name;
    private String email;
    private String username;
    private String password;

    public User(String name, String email, String username, String password) {
        this.userId = createRandomUserId();
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public User() {
        this("Player", "player@example.com", "player", "password");
    }

    public int createRandomUserId(){
        return (int) (Math.random()*1000);
    }

    public static User getDummyUser(){
        return new User();
    }
}
