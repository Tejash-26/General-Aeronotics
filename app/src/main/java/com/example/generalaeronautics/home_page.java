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
    List<RetroPhoto> image_models;


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
        image_models=new ArrayList<>();
        recyclerView=findViewById(R.id.customRecyclerview);
        Intialize_Retrofit();
        progressDoalog.dismiss();

        }
    public void Intialize_Retrofit() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDataService2 myapi=retrofit.create(GetDataService2.class);

        Call<List<RetroUser>> call=myapi.getAllusers();

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
                for(RetroPhoto im:data2){
                    if(count<10){
                        image_models.add(im);}
                    count++;
                }
                intialize_userlist(users,image_models);
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {

            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
   /* private void generateDataList(List<RetroPhoto> image_models) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this,image_models);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(home_page.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
    private void generateDataList2(List<RetroUser> User) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this,User);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(home_page.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


*/

    private void intialize_userlist(List<RetroUser> users,List<RetroPhoto> image_models) {
        adapter=new CustomAdapter(users,image_models,this);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}

