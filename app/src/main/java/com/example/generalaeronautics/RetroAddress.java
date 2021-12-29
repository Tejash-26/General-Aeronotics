package com.example.generalaeronautics;

public class RetroAddress {
    private String street;
    private  String suite;
    private  String city;
    private RetroGeo geo;

    public RetroAddress(String street, String suite, String city, RetroGeo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.geo = geo;
    }

    public String getStreet() {
        return street;
    }

    public String getSuit() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public RetroGeo getGeo() {
        return geo;
    }
}
