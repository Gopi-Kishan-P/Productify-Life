package com.miniproject.productifylife;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.miniproject.productifylife.data.GlobalData;
import com.miniproject.productifylife.models.RoutineModel;
import com.miniproject.productifylife.models.UserModel;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Toolbar appToolbar;
    FloatingActionButton fab;
    int currentFragment = 1;
    Dialog aboutDialog;
    ImageButton calendarBtn, logoutBtn;
    Button createRoutine;

    public static final String pass = "pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ProductifyLife);
        setContentView(R.layout.activity_main);

        aboutDialog = new Dialog(this);

        appToolbar = findViewById(R.id.appToolbar);
        setSupportActionBar(appToolbar);
        appToolbar.setTitle(R.string.routine);

        logoutBtn = findViewById(R.id.logoutbutton);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout = new Intent(MainActivity.this, AuthActivity.class);
                startActivity(logout);
                Toast.makeText(MainActivity.this, "Successfully!! Sign out", Toast.LENGTH_SHORT).show();
            }
        });
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
                        createRoutine=aboutDialog.findViewById(R.id.routine_create_task_btn);
                        createRoutine.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                CollectionReference ref= FirebaseFirestore.getInstance().collection("userRoutine");
                                TextInputLayout name=aboutDialog.findViewById(R.id.routine_edittext);
                                EditText coins=aboutDialog.findViewById(R.id.add_routine_coins);
                                String rname=name.getEditText().getText().toString();
                                String rcoins=coins.getText().toString();
                                UserModel userModel=GlobalData.cUser;
                                RoutineModel routineModel=new RoutineModel((userModel.id+"_"+rname).replaceAll("\\s+", "_").toLowerCase(),rname,userModel.email,"","0",rcoins);
                                ref.document(routineModel.id).set(routineModel.getMap());
                                Log.d("createRoutine","***************added routine");
                            }
                        });

                        break;
                    case 2:
                        aboutDialog.setContentView(R.layout.dialog_add_todo);
                        Log.d("TRY", "Case 1");
                        aboutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        aboutDialog.show();
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
}