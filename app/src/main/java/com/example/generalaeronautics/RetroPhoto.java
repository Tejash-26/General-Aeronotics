package com.example.generalaeronautics;

import com.google.gson.annotations.SerializedName;

public class RetroPhoto {


    private String thumbnailUrl;

    public RetroPhoto(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    }

