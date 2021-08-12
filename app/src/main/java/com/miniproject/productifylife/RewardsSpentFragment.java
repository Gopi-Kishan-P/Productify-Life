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


public class RewardsSpentFragment extends Fragment {

    RecyclerView recyclerView;

    ArrayList<TempData> dataArrayList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_routine_remaining_list, container, false);

        recyclerView = view.findViewById(R.id.routine_remaining_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataArrayList = new ArrayList<>();
        dataArrayList.add(new TempData("Bought Oreo Biscuit", "20"));
        dataArrayList.add(new TempData("Bought Burger", "70"));


        System.err.println("@!@! Inside class RoutineRemainingFragment extends Fragment");
        recyclerView.setAdapter(new MyAdapterEvent(dataArrayList, false));
        return view;
    }
}

