package com.example.generalaeronautics;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomHolder> {

    private List<RetroUser> user;
    private List<RetroPhoto> dataList;
    private Context context;

    public CustomAdapter(List<RetroUser> user,List<RetroPhoto> dataList ,Context context){
        this.context = context;
        this.dataList = dataList;
        this.user=user;
    }

    public CustomAdapter() {
    }

    public class CustomHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView name;
        private TextView address;
        private TextView id;
        private RelativeLayout singleitem;
        private TextView longitude;
        private TextView latitude;
        public CustomHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.user_image);
            name=itemView.findViewById(R.id.user_name);
            address=itemView.findViewById(R.id.user_address);
            id=itemView.findViewById(R.id.id);
            singleitem=itemView.findViewById(R.id.single_item);
            latitude=itemView.findViewById(R.id.lati);
            longitude=itemView.findViewById(R.id.longi);
        }


    }

    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout,parent,false);
        return new CustomHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, int link) {


        String longi=user.get(link).getAddress().getGeo().getLng();
        String lati=user.get(link).getAddress().getGeo().getLat();
        holder.name.setText(user.get(link).getName());
        String address=String.valueOf(user.get(link).getAddress().getStreet()+", "+user.get(link).getAddress().getSuit()+", "+
                user.get(link).getAddress().getCity());
        holder.id.setText(String.valueOf(user.get(link).getId()));
        holder.address.setText(address);
        holder.longitude.setText("Long: "+longi);
        holder.latitude.setText("Lat: "+lati);



        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(link).getThumbnailUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.image);
        holder.singleitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(context);
                dialog.setTitle("Select an option to navigate.")
                        .setCancelable(true)
                        .setPositiveButton("Google Maps", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", Double.parseDouble(lati),Double.parseDouble(longi));
                                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                                intent.setPackage("com.google.android.apps.maps");
                                context.startActivity(intent);
                            }
                        }).setNegativeButton("Inbuilt Map", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(context,MapsActivity.class);
                        intent.putExtra("lat",Double.parseDouble(lati));
                        intent.putExtra("long",Double.parseDouble(longi));
                        context.startActivity(intent);
                    }
                });
                dialog.create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return user.size();
    }



}
