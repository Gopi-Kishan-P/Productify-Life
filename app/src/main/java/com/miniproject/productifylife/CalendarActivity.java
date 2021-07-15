package com.miniproject.productifylife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    int passData = 1;
    ImageButton back_btn;
    Calendar calendar;

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
        Toast.makeText(getBaseContext(), String.valueOf(passData), Toast.LENGTH_LONG).show();
    }
}