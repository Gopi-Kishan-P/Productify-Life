package com.miniproject.productifylife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    Toolbar appToolbar;
    FloatingActionButton fab;
    int currentFragment = 1;
    Dialog aboutDialog;
    ImageButton calendarBtn;
    
    public static final String pass = "pass";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ProductifyLife);
        setContentView(R.layout.activity_main);

        aboutDialog = new Dialog(this);

        appToolbar = findViewById(R.id.appToolbar);
        setSupportActionBar(appToolbar);
        appToolbar.setTitle(R.string.routine);

        calendarBtn = findViewById(R.id.calendarButton);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cal = new Intent(MainActivity.this, CalendarActivity.class);
                cal.putExtra(pass, currentFragment);
                startActivity(cal);
            }
        });

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);
        Fragment routineFragment= new RoutineFragment();
        Fragment todoFragment=new TodoFragment();
        Fragment rewardsFragment=new RewardsFragment();
        Fragment settingsFragment=new SettingsFragment();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentFragment == 4){
                    aboutDialog.setContentView(R.layout.dialog_about);
                    aboutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    aboutDialog.show();

                }
            }
        });

        getSupportFragmentManager().beginTransaction().add(R.id.flFragment, routineFragment, null).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.routine:
                        currentFragment = 1;
                        appToolbar.setTitle(R.string.routine);
                        fab.setImageResource(R.drawable.ic_add);
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.flFragment, routineFragment, null)
                                .commit();
                        break;
                    case R.id.todo:
                        currentFragment = 2;
                        appToolbar.setTitle(R.string.todo);
                        fab.setImageResource(R.drawable.ic_add);
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.flFragment, todoFragment, null)
                                .commit();  break;
                    case R.id.rewards:
                        currentFragment = 3;
                        appToolbar.setTitle(R.string.rewards);
                        fab.setImageResource(R.drawable.ic_add);
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.flFragment, rewardsFragment, null)
                                .commit();
                        break;
                    case R.id.settings:
                        currentFragment = 4;
                        appToolbar.setTitle(R.string.settings);
                        fab.setImageResource(R.drawable.ic_about);
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