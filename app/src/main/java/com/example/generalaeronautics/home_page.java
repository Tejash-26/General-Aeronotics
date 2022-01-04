package com.example.generalaeronautics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class home_page extends AppCompatActivity {


    LinearLayoutManager layoutManager;
    List<RetroUser> userlist;
    String id="";
    String name="";
    List<RetroUser> users;
    List<RetroPhoto> image;


    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window=this.getWindow();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        progressDoalog = new ProgressDialog(home_page.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        users=new ArrayList<>();
        image=new ArrayList<>();
        recyclerView=findViewById(R.id.customRecyclerview);
        IntializeRetrofit();
        progressDoalog.dismiss();

        }
    public void IntializeRetrofit() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDataService2 tejasapi=retrofit.create(GetDataService2.class);

        Call<List<RetroUser>> call=tejasapi.getAllusers();

        call.enqueue(new Callback<List<RetroUser>>() {
            @Override
            public void onResponse(Call<List<RetroUser>> call, Response<List<RetroUser>> response) {
                if(response.code()!=200){
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG);
                    return;

                }

                List<RetroUser> data=response.body();
                for(RetroUser model: data){

                    users.add(model);

                }

            }

            @Override
            public void onFailure(Call<List<RetroUser>> call, Throwable t) {

            }
        });

        Retrofit retrofit1=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDataService api=retrofit1.create(GetDataService.class);
        Call<List<RetroPhoto>> call1=api.getAllPhotos();
        call1.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                if(response.code()!=200){
                    Toast.makeText(getApplicationContext(),"Error on accessing JSON Images",Toast.LENGTH_LONG);
                    return;
                }
                List<RetroPhoto> data2=response.body();
                int count=0;
                for(RetroPhoto i2:data2){
                    if(count<10){
                        image.add(i2);}
                    count++;
                }
                intializeuserlist(users,image);
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {

            }
        });
    }

    private void intializeuserlist(List<RetroUser> users,List<RetroPhoto> image_models) {
        adapter=new CustomAdapter(users,image_models,this);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}

