package com.example.hotelbooking;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    Activity activity; ArrayList<Product_Room> arrayList;
    public RoomAdapter(Activity activity, ArrayList<Product_Room> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(activity.getLayoutInflater().inflate(R.layout.activity_roomlistview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product_Room u = arrayList.get(position);
        if (u.getBookingstatus().equals("Booked")){
            holder.roomstatus.setTextColor(Color.parseColor("#616161"));
        }else {
            holder.roomstatus.setTextColor(Color.parseColor("#2454ef"));
        }
        holder.name.setText("Room Name : "+u.getName());
        holder.dailyrent.setText(u.getRent()+"( "+u.getRoomtype()+" )");
        holder.roomstatus.setText(u.getBookingstatus());
        holder.description.setText(u.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(
                        new Intent(activity, UpdateRoomDetails.class)
                                .putExtra("roomlist",u)
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, dailyrent, roomstatus,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.roomname);
            dailyrent =  itemView.findViewById(R.id.dailyrent);
            roomstatus =  itemView.findViewById(R.id.roomstatus);
            description=  itemView.findViewById(R.id.description);

        }
    }
}
