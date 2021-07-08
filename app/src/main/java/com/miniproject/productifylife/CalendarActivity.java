package com.miniproject.productifylife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {

    int passData = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        passData = getIntent().getIntExtra(MainActivity.pass, 1);
        Toast.makeText(getBaseContext(), String.valueOf(passData), Toast.LENGTH_LONG).show();
    }
}