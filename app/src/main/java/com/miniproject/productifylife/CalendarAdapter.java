package com.miniproject.productifylife;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

public class CalendarAdapter extends FragmentPagerAdapter {
    private Context mycontext;
    int totalTabs;

    public CalendarAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.mycontext = context;
        this.totalTabs = totalTabs;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CalendarRoutineFragment calendarRoutineFragment = new CalendarRoutineFragment();
//                Log.println(Log.WARN,"tag","returning login fragment");
                System.err.println("----- rem return");
                return calendarRoutineFragment;
            case 1:
                CalendarTodoFragment calendarTodoFragment = new CalendarTodoFragment();
//                Log.println(Log.WARN,"tag","returning login fragment");
                System.err.println("----- com return");
                return calendarTodoFragment;
            case 2:
                CalendarRewardFragment calendarRewardFragment = new CalendarRewardFragment();
//                Log.println(Log.WARN,"tag","returning login fragment");
                System.err.println("----- com return");
                return calendarRewardFragment;
            case 3:
                CalendarAllFragment calendarAllFragment = new CalendarAllFragment();
//                Log.println(Log.WARN,"tag","returning login fragment");
                System.err.println("----- com return");
                return calendarAllFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
