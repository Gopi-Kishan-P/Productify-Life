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

class MyAdapterTask extends RecyclerView.Adapter<MyAdapterTask.MyViewHolderTask>{

    ArrayList<TempData> dataArrayList;
    boolean checked;

    public MyAdapterTask(ArrayList<TempData> dataArrayList, boolean checked) {
        this.dataArrayList = dataArrayList;
        this.checked = checked;
    }

    @NonNull
    @Override
    public MyViewHolderTask onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_listview, parent,false);
        return new MyViewHolderTask(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderTask holder, int position) {
        holder.taskTitle.setText(dataArrayList.get(position).getTitle());
        holder.taskCoins.setText(dataArrayList.get(position).getCoins());
        holder.checkBox.setChecked(checked);
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    class MyViewHolderTask extends RecyclerView.ViewHolder{

        CheckBox checkBox;
        TextView taskTitle;
        TextView taskCoins;
        public MyViewHolderTask(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.task_checkBox);
            taskTitle = itemView.findViewById(R.id.task_title);
            taskCoins = itemView.findViewById(R.id.task_coins);
            System.err.println("@!@! Inside MyViewHolderTask extends RecyclerView.ViewHolder");
        }
    }
}

