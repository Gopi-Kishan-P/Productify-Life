//package com.miniproject.productifylife;
//
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.auth.AuthCredential;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.GoogleAuthProvider;
//
//import java.util.List;
//
///**
// * Demonstrate Firebase Authentication using a Google ID Token.
// */
//@SuppressWarnings("deprecation")
//public class GoogleSignInActivity extends AuthActivity {
//
//    private static final String TAG = "GoogleActivity";
//    private static final int RC_SIGN_IN = 9001;
//
//    // [START declare_auth]
//    private FirebaseAuth mAuth;
//    // [END declare_auth]
//
//    private GoogleSignInClient mGoogleSignInClient;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // [START config_signin]
//        // Configure Google Sign In
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//
//        // [END config_signin]
//
//        // [START initialize_auth]
//        // Initialize Firebase Auth
////        FirebaseOptions ops=new FirebaseOptions();
////        FirebaseApp.initializeApp(this, ops,"fapp");
////        FirebaseApp firebaseApp=FirebaseApp.getInstance("fapp");
//        FirebaseApp.initializeApp(this);
//        List<FirebaseApp> firebaseApps=FirebaseApp.getApps(this);
//        Log.d(TAG,""+firebaseApps.toString());
//
//        mAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
//        Log.d(TAG,"mAuth value="+mAuth.toString());
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//        // [END initialize_auth]
//    }
//
////    // [START on_start_check_user]
////    @Override
////    public void onStart() {
////        super.onStart();
////        // Check if user is signed in (non-null) and update UI accordingly.
////        FirebaseUser currentUser = mAuth.getCurrentUser();
////        updateUI(currentUser);
////    }
//    // [END on_start_check_user]
//
//    // [START onactivityresult]
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                assert account != null;
//                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
//                firebaseAuthWithGoogle(account.getIdToken());
//            } catch (ApiException e) {
//                // Google Sign In failed, update UI appropriately
//                Log.w(TAG, "Google sign in failed", e);
//                Toast.makeText(getBaseContext(), "Google Sign In Failed. Try Again", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//    // [END onactivityresult]
//
//    // [START auth_with_google]
//    private void firebaseAuthWithGoogle(String idToken) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        if(mAuth!=null)
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            updateUI(null);
//                        }
//                    }
//                });
////        else
//        updateUI(null);
//    }
//
//
//    private void updateUI(FirebaseUser user) {
//        Intent intent = new Intent(GoogleSignInActivity.this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
//        if (account != null) {
//            String personName = account.getDisplayName();
//            String personEmail = account.getEmail();
//            Toast.makeText(GoogleSignInActivity.this, "Welcome! " + personName, Toast.LENGTH_LONG).show();
//        }
//    }
//}