package com.miniproject.productifylife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class AuthActivity extends AppCompatActivity {
    TabLayout tablayout;
    ViewPager viewPager;
    CardView google;
    float v=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.blue_500, getTheme()));
        }

        tablayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        google = findViewById(R.id.continue_with_google);

        tablayout.addTab(tablayout.newTab().setText("Login"));
        tablayout.addTab(tablayout.newTab().setText("SignUp"));
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final AuthAdapter adapter = new AuthAdapter(this, getSupportFragmentManager(), tablayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
                    viewPager.setCurrentItem(tab.getPosition());
                    System.err.println("********************************tab selected");
                    System.err.println("****************" + viewPager.getCurrentItem());
                    System.err.println("****************" + tab.getPosition());

                }catch(Exception e){
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
//        google.setTranslationY(300);
//        tablayout.setTranslationY(300);
//
//        google.setAlpha(v);
//        tablayout.setAlpha(v);
//
//        google.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
//        tablayout.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
    }
}