package com.example.generalaeronautics;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService2 {
    @GET("/users")
    Call<List<RetroUser>>getAllusers();
}
