package com.miniproject.productifylife;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    TextInputLayout password, email;
    Button logInBtn;
    FirebaseAuth mAuth;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);
        email = root.findViewById(R.id.login_email);
        password = root.findViewById(R.id.login_password);
        logInBtn = root.findViewById(R.id.login_btn);
        mAuth = FirebaseAuth.getInstance();

        email.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                email.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setError(null);
                password.setError(null);
                String emailString = email.getEditText().getText().toString();
                String passwordString = password.getEditText().getText().toString();
                if(emailString.isEmpty()){
                    email.setError("Enter the Email");
                    return;
                }
                if(passwordString.isEmpty()){
                    password.setError("Enter the Password");
                    return;
                }

                mAuth.signInWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if(user!=null)
                                        Toast.makeText(getContext(), "Logged In Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getContext(), NavigateAuthMainScreen.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } else {
                                    try{
                                        throw task.getException();
                                    }catch (FirebaseAuthInvalidUserException e){
                                        email.setError("Email Does Not Exists, Try to Sign Up or Enter Valid Email");
                                        email.requestFocus();
                                    }
                                    catch (FirebaseAuthInvalidCredentialsException e){
                                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                    catch (Exception e) {
                                        Toast.makeText(getContext(), "Log In Failed, Try Again Later", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });

        return root;
    }
}

