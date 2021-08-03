package com.miniproject.productifylife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    int passData = 1;
    ImageButton back_btn;
    Calendar calendar;
    TabLayout calendarTabLayout;
    ViewPager calendarViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        back_btn = findViewById(R.id.cal_back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        passData = getIntent().getIntExtra(MainActivity.pass, 4);
//        Toast.makeText(getBaseContext(), String.valueOf(passData), Toast.LENGTH_LONG).show();

        calendarTabLayout = findViewById(R.id.cal_tablayout);
        calendarViewPager = findViewById(R.id.calendarViewPager);

        calendarTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final CalendarAdapter adapter = new CalendarAdapter(this, getSupportFragmentManager(), calendarTabLayout.getTabCount());
        calendarViewPager.setAdapter(adapter);
        calendarViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(calendarTabLayout));
        calendarViewPager.setCurrentItem(passData-1);

        calendarTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
                    calendarViewPager.setCurrentItem(tab.getPosition());
                    System.err.println("********************************tab selected");
                    System.err.println("****************" + calendarViewPager.getCurrentItem());
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
    }
}