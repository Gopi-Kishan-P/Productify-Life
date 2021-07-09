package com.miniproject.productifylife;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {

    EditText password, email;
    Button login;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);
        email = root.findViewById(R.id.email1);
        password = root.findViewById(R.id.password1);
        login = root.findViewById(R.id.login);
        return root;
    }
}

