package com.miniproject.productifylife;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.miniproject.productifylife.placeholder.PlaceholderContent;

/**
 * A fragment representing a list of Items.
 */
public class TodoFragment extends Fragment {
    private static View view;

    TabLayout todoTabLayout;
    ViewPager todoViewPager;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TodoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TodoFragment newInstance(int columnCount) {
        TodoFragment fragment = new TodoFragment();
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
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_todo, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }


        todoTabLayout = view.findViewById(R.id.todo_tablayout);
        todoViewPager = view.findViewById(R.id.todo_viewpager);

        todoTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final TodoAdapter routineAdapter = new TodoAdapter(getContext(), getChildFragmentManager(), todoTabLayout.getTabCount());
        todoViewPager.setAdapter(routineAdapter);
        todoViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(todoTabLayout));

        todoTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
                    todoViewPager.setCurrentItem(tab.getPosition());
                    System.err.println("********************************tab selected");
                    System.err.println("****************" + todoViewPager.getCurrentItem());
                    System.err.println("****************" + tab.getPosition());

                } catch (Exception e) {
                    System.err.println(e);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }
//    @Override
//    public void onDestroyView() {
//
//        FragmentManager fm = getFragmentManager();
//
//        Fragment xmlFragment = fm.findFragmentById(R.id.fragmentListTodo);
//        if (xmlFragment != null) {
//            fm.beginTransaction().remove(xmlFragment).commit();
//        }
//        xmlFragment = fm.findFragmentById(R.id.fragmentListRoutine);
//        if (xmlFragment != null) {
//            fm.beginTransaction().remove(xmlFragment).commit();
//        }
//
//        super.onDestroyView();
//    }
}