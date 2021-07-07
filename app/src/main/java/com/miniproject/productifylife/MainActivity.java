package com.miniproject.productifylife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Toolbar appToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ProductifyLife);
        setContentView(R.layout.activity_main);
        appToolbar = findViewById(R.id.appToolbar);
        setSupportActionBar(appToolbar);
        appToolbar.setTitle(R.string.routine);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);
        Fragment routineFragment= new RoutineFragment();
        Fragment todoFragment=new TodoFragment();
        Fragment rewardsFragment=new RewardsFragment();
        Fragment settingsFragment=new SettingsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.flFragment, routineFragment, null).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.routine:
                        appToolbar.setTitle(R.string.routine);
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.flFragment, routineFragment, null)
                                .commit();
                        break;
                    case R.id.todo:
                        appToolbar.setTitle(R.string.todo);
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.flFragment, todoFragment, null)
                                .commit();  break;
                    case R.id.rewards:
                        appToolbar.setTitle(R.string.rewards);
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.flFragment, rewardsFragment, null)
                                .commit();
                        break;
                    case R.id.settings:
                        appToolbar.setTitle(R.string.settings);
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.flFragment, settingsFragment, null)
                                .commit();
                        break;
                }
                return true;
            }
        });


}}