package com.example.generalaeronautics;

public class RetroUser {
    private int id;
    private  String name;
    private  String username;
    private  String email;
    private RetroAddress address;

    public RetroUser(int id, String name, String username, String email, RetroAddress address) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public RetroAddress getAddress() {
        return address;
    }
}
