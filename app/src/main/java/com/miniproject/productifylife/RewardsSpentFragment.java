package com.miniproject.productifylife;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class RewardsSpentFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_rewards_spent_list, container, false);

        return root;
    }
}
