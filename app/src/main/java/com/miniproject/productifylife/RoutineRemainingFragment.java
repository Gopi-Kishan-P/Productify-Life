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


public class RoutineRemainingFragment extends Fragment {

    RecyclerView recyclerView;

    ArrayList<TempData> dataArrayList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_routine_remaining_list, container, false);

        recyclerView = view.findViewById(R.id.routine_remaining_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataArrayList = new ArrayList<>();
        dataArrayList.add(new TempData("Yoga at 7:00 am", "4"));
        dataArrayList.add(new TempData("Have a healthy breakfast", "3"));
        dataArrayList.add(new TempData("Don't Waste Your Time", "6"));
        dataArrayList.add(new TempData("Don't Procrastinate", "7"));
        dataArrayList.add(new TempData("Spent time with Family", "5"));

        System.err.println("@!@! Inside class RoutineRemainingFragment extends Fragment");
        recyclerView.setAdapter(new MyAdapterTask(dataArrayList, false));
        return view;
    }
}

