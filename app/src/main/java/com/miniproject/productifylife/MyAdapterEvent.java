package com.miniproject.productifylife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miniproject.productifylife.models.TempData;

import java.util.ArrayList;

class MyAdapterEvent extends RecyclerView.Adapter<MyAdapterEvent.MyViewHolderEvent>{

    ArrayList<TempData> tempDataArrayList;
    boolean checked;

    public MyAdapterEvent(ArrayList<TempData> tempDataArrayList, boolean checked) {
        this.tempDataArrayList = tempDataArrayList;
        this.checked = checked;
    }

    @NonNull
    @Override
    public MyViewHolderEvent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_listview, parent,false);
        return new MyViewHolderEvent(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderEvent holder, int position) {
        holder.eventTitle.setText(tempDataArrayList.get(position).getTitle());
        holder.eventCoins.setText(tempDataArrayList.get(position).getCoins());
    }

    @Override
    public int getItemCount() {
        return tempDataArrayList.size();
    }

    class MyViewHolderEvent extends RecyclerView.ViewHolder{

        TextView eventTitle;
        TextView eventCoins;
        public MyViewHolderEvent(@NonNull View itemView) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.event_title);
            eventCoins = itemView.findViewById(R.id.event_coin);
            System.err.println("@!@! Inside MyViewHolderEvent extends RecyclerView.ViewHolder");
        }
    }
}

