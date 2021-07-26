package com.miniproject.productifylife;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miniproject.productifylife.placeholder.PlaceholderContent;

/**
 * A fragment representing a list of Items.
 */
public class RoutineFragment extends Fragment {
    private static View view;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RoutineFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RoutineFragment newInstance(int columnCount) {
        RoutineFragment fragment = new RoutineFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Fragment fragment= getSupportFragmentManager().findFragmentById(R.id.fragmentListRoutine);
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_routine, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        return view;
    }
//    @Override
//    public void onDestroyView() {
//
//        FragmentManager fm = getFragmentManager();
//
//        Fragment xmlFragment = fm.findFragmentById(R.id.fragmentListRoutine);
//        if (xmlFragment != null) {
//            fm.beginTransaction().remove(xmlFragment).commit();
//        }
//        xmlFragment = fm.findFragmentById(R.id.fragmentListTodo);
//        if (xmlFragment != null) {
//            fm.beginTransaction().remove(xmlFragment).commit();
//        }
//
//
//        super.onDestroyView();
//    }
}