package com.example.generalaeronautics;

public class RetroGeo {
    private String lat;
    private  String lng;

    public RetroGeo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}
