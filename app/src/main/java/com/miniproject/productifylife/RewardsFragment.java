package com.miniproject.productifylife;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.miniproject.productifylife.placeholder.PlaceholderContent;

/**
 * A fragment representing a list of Items.
 */
public class RewardsFragment extends Fragment {

    TabLayout rewardsTabLayout;
    ViewPager rewardsViewPager;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RewardsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RewardsFragment newInstance(int columnCount) {
        RewardsFragment fragment = new RewardsFragment();
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
        View view = inflater.inflate(R.layout.fragment_rewards, container, false);
        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
//            recyclerView.setAdapter(new MyItemRecyclerViewAdapter3(PlaceholderContent.ITEMS));
//        }

        rewardsTabLayout = view.findViewById(R.id.rewards_tablayout);
        rewardsViewPager = view.findViewById(R.id.rewards_viewpager);

        rewardsTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final RewardsAdapter routineAdapter = new RewardsAdapter(getContext(), getChildFragmentManager(), rewardsTabLayout.getTabCount());
        rewardsViewPager.setAdapter(routineAdapter);
        rewardsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(rewardsTabLayout));

        rewardsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
                    rewardsViewPager.setCurrentItem(tab.getPosition());
                    System.err.println("********************************tab selected");
                    System.err.println("****************" + rewardsViewPager.getCurrentItem());
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
}