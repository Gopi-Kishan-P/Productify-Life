package com.miniproject.productifylife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miniproject.productifylife.models.TempData;

import java.util.ArrayList;


public class RewardsEarnedFragment extends Fragment {

    RecyclerView recyclerView;

    ArrayList<TempData> dataArrayList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_routine_remaining_list, container, false);

        recyclerView = view.findViewById(R.id.routine_remaining_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataArrayList = new ArrayList<>();
        dataArrayList.add(new TempData("Wake up at 6:00 am", "10"));
        dataArrayList.add(new TempData("Meditate from 6:15 am to 6:45 am", "3"));
        dataArrayList.add(new TempData("Read a Book", "5"));
        dataArrayList.add(new TempData("Exercise at 7:00 pm", "6"));
        dataArrayList.add(new TempData("Spent time Alone", "2"));
        dataArrayList.add(new TempData("Iron Clothes", "3"));
        dataArrayList.add(new TempData("Complete Internship Assignment", "5"));


        System.err.println("@!@! Inside class RoutineRemainingFragment extends Fragment");
        recyclerView.setAdapter(new MyAdapterEvent(dataArrayList, false));
        return view;
    }
}

