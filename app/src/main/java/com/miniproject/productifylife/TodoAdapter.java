package com.miniproject.productifylife;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

public class TodoAdapter extends FragmentPagerAdapter {
    private Context mycontext;
    int totalTabs;

    public TodoAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.mycontext = context;
        this.totalTabs = totalTabs;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TodoRemainingFragment todoRemainingFragment = new TodoRemainingFragment();
//                Log.println(Log.WARN,"tag","returning login fragment");
                System.err.println("----- rem return");
                return todoRemainingFragment;
            case 1:
                TodoCompletedFragment todoCompletedFragment = new TodoCompletedFragment();
//                Log.println(Log.WARN,"tag","returning login fragment");
                System.err.println("----- com return");

                return todoCompletedFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
