package com.miniproject.productifylife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);

        Fragment firstFragment= new RegularFragment();
        Fragment secondFragment=new todayFragment();
        Fragment thirdFragment=new RewardsFragment();
        Fragment settingsFragment=new SettingsFragment();
getSupportFragmentManager().beginTransaction().add(R.id.flFragment, firstFragment, null).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.regular:
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.flFragment, firstFragment, null)
                                .commit();
                        break;
                    case R.id.today:

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.flFragment, secondFragment, null)
                                .commit();  break;
                    case R.id.rewards:

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.flFragment, thirdFragment, null)
                                .commit();
                        Toast.makeText(MainActivity.this, "Rewards", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:

                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.flFragment, settingsFragment, null)
                                .commit();
                        Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });


}}