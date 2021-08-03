package com.miniproject.productifylife;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

public class RoutineAdapter extends FragmentPagerAdapter {
    private Context mycontext;
    int totalTabs;

    public RoutineAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.mycontext = context;
        this.totalTabs = totalTabs;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                RoutineRemainingFragment routineRemainingFragment = new RoutineRemainingFragment();
//                Log.println(Log.WARN,"tag","returning login fragment");
                System.err.println("----- rem return");
                return routineRemainingFragment;
            case 1:
                RoutineCompletedFragment routineCompletedFragment = new RoutineCompletedFragment();
//                Log.println(Log.WARN,"tag","returning login fragment");
                System.err.println("----- com return");

                return routineCompletedFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
