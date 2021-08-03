package com.miniproject.productifylife;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

public class RewardsAdapter extends FragmentPagerAdapter {
    private Context mycontext;
    int totalTabs;

    public RewardsAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.mycontext = context;
        this.totalTabs = totalTabs;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                RewardsEarnedFragment rewardsEarnedFragment = new RewardsEarnedFragment();
//                Log.println(Log.WARN,"tag","returning login fragment");
                System.err.println("----- rem return");
                return rewardsEarnedFragment;
            case 1:
                RewardsSpentFragment rewardsSpentFragment = new RewardsSpentFragment();
//                Log.println(Log.WARN,"tag","returning login fragment");
                System.err.println("----- com return");

                return rewardsSpentFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
