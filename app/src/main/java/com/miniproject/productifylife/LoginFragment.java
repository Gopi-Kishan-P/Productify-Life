package com.miniproject.productifylife;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;

public class LoginFragment extends Fragment {

    TextInputLayout password, email;
    Button login;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);
        email = root.findViewById(R.id.login_email);
        password = root.findViewById(R.id.login_password);
        login = root.findViewById(R.id.login_btn);


        return root;
    }
}

