package com.miniproject.productifylife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.List;

public class AuthActivity extends AppCompatActivity {
    TabLayout tablayout;
    ViewPager viewPager;
    CardView google;
    float v = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_auth);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.blue_500, getTheme()));
//            getWindow().getDecorView().getWindowInsetsController().setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS);
        }

        tablayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        google = findViewById(R.id.continue_with_google);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(AuthActivity.this, GoogleSignInActivity.class);
//                startActivity(intent);
//                GoogleSignInActivity googleSignInActivity=new GoogleSignInActivity();
//                googleSignInActivity.onGoogleSignIn();
                googleSignIn();

            }
        });



        tablayout.addTab(tablayout.newTab().setText("Login"));
        tablayout.addTab(tablayout.newTab().setText("SignUp"));
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final AuthAdapter adapter = new AuthAdapter(this, getSupportFragmentManager(), tablayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
                    viewPager.setCurrentItem(tab.getPosition());
                    System.err.println("********************************tab selected");
                    System.err.println("****************" + viewPager.getCurrentItem());
                    System.err.println("****************" + tab.getPosition());

                }catch(Exception e){
                    System.err.println(e);
                    }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public void googleSignIn(){
        final String TAG = "GoogleActivity";
        final int RC_SIGN_IN = 9001;

        // [START declare_auth]
        // [END declare_auth]

        GoogleSignInClient mGoogleSignInClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [END config_signin]

        // [START initialize_auth]
        // Initialize Firebase Auth
//        FirebaseOptions ops=new FirebaseOptions();
//        FirebaseApp.initializeApp(this, ops,"fapp");
//        FirebaseApp firebaseApp=FirebaseApp.getInstance("fapp");

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 9001) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                assert account != null;
//                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
//                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(getBaseContext(), "Google Sign In Failed. Try Again", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        FirebaseApp.initializeApp(this);
        List<FirebaseApp> firebaseApps=FirebaseApp.getApps(this);
//        Log.d(TAG,""+firebaseApps.toString());
        FirebaseAuth mAuth;

        mAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
//        Log.d(TAG,"mAuth value="+mAuth.toString());
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        if(mAuth!=null)
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
//                                Log.d(TAG, "signInWithCredential:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                                updateUI(null);
                            }
                        }
                    });
//        else
        updateUI(null);
    }


    private void updateUI(FirebaseUser user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null) {
            String personName = account.getDisplayName();
            String personEmail = account.getEmail();
            Toast.makeText(this, "Welcome! " + personName, Toast.LENGTH_LONG).show();
        }
    }
}