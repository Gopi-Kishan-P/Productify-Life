package com.miniproject.productifylife;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.miniproject.productifylife.services.dbServices;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Toolbar appToolbar;
    FloatingActionButton fab;
    int currentFragment = 1;
    Dialog aboutDialog;
    ImageButton calendarBtn;
    Button logoutBtn;

    Button createRoutine;

    public static final String pass = "pass";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ProductifyLife);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        aboutDialog = new Dialog(this);
        dbServices.fetchUser();
        appToolbar = findViewById(R.id.appToolbar);
        setSupportActionBar(appToolbar);
        appToolbar.setTitle(R.string.routine);


//        logoutBtn = findViewById(R.id.logout_btn);
//        logoutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent logout = new Intent(MainActivity.this, AuthActivity.class);
//                startActivity(logout);
//                Toast.makeText(MainActivity.this, "Successfully!! Sign out", Toast.LENGTH_SHORT).show();
//            }
//        });
        calendarBtn = findViewById(R.id.calendarButton);
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cal = new Intent(MainActivity.this, CalendarActivity.class);
                cal.putExtra(pass, currentFragment);
                startActivity(cal);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        Fragment routineFragment = new RoutineFragment();
        Fragment todoFragment = new TodoFragment();
        Fragment rewardsFragment = new RewardsFragment();
        Fragment settingsFragment = new SettingsFragment();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (currentFragment) {
                    case 1:
                        aboutDialog.setContentView(R.layout.dialog_add_routine);
                        Log.d("TRY", "Case 1");
                        aboutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        aboutDialog.show();
                        createRoutine = aboutDialog.findViewById(R.id.routine_create_task_btn);
                        createRoutine.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                TextInputLayout name = aboutDialog.findViewById(R.id.routine_edittext);
                                EditText coins = aboutDialog.findViewById(R.id.add_routine_coins);
                                String rname = name.getEditText().getText().toString();
                                String rcoins = coins.getText().toString();
                                dbServices.createRoutine(rname, rcoins);

                            }
                        });

                        break;
                    case 2:
                        aboutDialog.setContentView(R.layout.dialog_add_todo);
                        Log.d("TRY", "Case 1");
                        aboutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        aboutDialog.show();
                        createRoutine = aboutDialog.findViewById(R.id.routine_create_task_btn);
                        createRoutine.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                TextInputLayout name = aboutDialog.findViewById(R.id.todoname);
                                EditText coins = aboutDialog.findViewById(R.id.todocoins);
                                String rname = name.getEditText().getText().toString();
                                String rcoins = coins.getText().toString();
                                dbServices.createTodo(rname, rcoins);

                            }
                        });
                        break;
                    case 3:
                        aboutDialog.setContentView(R.layout.dialog_add_reward);
                        Log.d("TRY", "Case 1");
                        aboutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        aboutDialog.show();
                        break;

                    case 4:
                        Log.d("TRY", "Case 4");
                        aboutDialog.setContentView(R.layout.dialog_about);
                        aboutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        aboutDialog.show();
                        break;

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
                                .commit();
                        break;
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
//                        logoutBtn = findViewById(R.id.logout_btn);
//                        logoutBtn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Intent logout = new Intent(MainActivity.this, AuthActivity.class);
//                                startActivity(logout);
//                                Toast.makeText(MainActivity.this, "Successfully!! Sign out", Toast.LENGTH_SHORT).show();
//                            }
//                        });
                        break;
                }
                return true;
            }
        });

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1) {
            Intent main = new Intent(this, MainActivity.class);
            main.setAction(Intent.ACTION_VIEW);
            Intent cal = new Intent(this, CalendarActivity.class);
            cal.setAction(Intent.ACTION_VIEW);
            ShortcutManager sm = getSystemService(ShortcutManager.class);
            ShortcutInfo shortcut = new ShortcutInfo.Builder(this, "id1")
                    .setShortLabel("Calendar")
                    .setLongLabel("Calendar")
                    .setIcon(Icon.createWithResource(this, R.mipmap.adaptive_calendar_icon))
                    .setIntents(new Intent[]{
                            main, cal
                    })
                    .build();
            sm.setDynamicShortcuts(Collections.singletonList(shortcut));
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "taskAddRemainder";
            String description = "Add Tomorrow's To-Do Task";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel("notifyChannelId", name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}