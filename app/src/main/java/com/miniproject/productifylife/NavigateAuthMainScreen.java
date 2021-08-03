package com.miniproject.productifylife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NavigateAuthMainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Log.d("hello", "START");
        Intent i;
        if(user == null)
            i = new Intent(this, AuthActivity.class);
        else
            i = new Intent(this, MainActivity.class);

        startActivity(i);
        finish();
    }
}