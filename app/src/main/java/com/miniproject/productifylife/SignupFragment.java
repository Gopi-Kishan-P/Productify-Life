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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.miniproject.productifylife.models.UserModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {

    TextInputLayout name, email, password, confirmPassword;
    Button signUpBtn;
    FirebaseAuth mAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_signup, container, false);
        email = root.findViewById(R.id.signup_email);
        name = root.findViewById(R.id.signup_name);
        password = root.findViewById(R.id.signup_password);
        confirmPassword = root.findViewById(R.id.signup_confirm_password);
        signUpBtn = root.findViewById(R.id.signup_btn);

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
        name.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                confirmPassword.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mAuth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailString = email.getEditText().getText().toString();
                String nameString = name.getEditText().getText().toString();
                String passwordString = password.getEditText().getText().toString();
                String confirmPasswordString = confirmPassword.getEditText().getText().toString();

                email.setError(null);
                name.setError(null);
                password.setError(null);
                confirmPassword.setError(null);

                if(emailString.isEmpty()){
                    email.setError("Enter the Email");
                    return;
                }
                if(passwordString.isEmpty()){
                    password.setError("Enter the Password");
                    return;
                }
                if(nameString.isEmpty()){
                    name.setError("Enter the Name");
                    return;
                }
                if(confirmPasswordString.isEmpty()){
                    confirmPassword.setError("Enter the Confirm Password");
                    return;
                }

                if(passwordString.compareTo(confirmPasswordString) != 0){
                    confirmPassword.setError("Password Not Matching");
                    confirmPassword.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    UserModel userModel = new UserModel(user.getEmail(), nameString, user.getEmail(), "");
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    db.collection("users").document(userModel.id).set(userModel.getMap());
                                    Toast.makeText(getContext(),"Welcome! " + nameString, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getContext(), NavigateAuthMainScreen.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } else {
                                    try {
                                        throw task.getException();
                                    } catch(FirebaseAuthWeakPasswordException e) {
                                        password.setError("Weak Password");
                                        password.requestFocus();
                                    } catch(FirebaseAuthInvalidCredentialsException e) {
                                        email.setError("Invalid Email");
                                        email.requestFocus();
                                    } catch(FirebaseAuthUserCollisionException e) {
                                        email.setError("Email Exists, Try to Login");
                                        email.requestFocus();
                                    } catch(Exception e) {
                                        Toast.makeText(getContext(), "Sign Up Failed, Try Again Later", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });

        return root;
    }
}