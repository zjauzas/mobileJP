package com.example.jurnalprakerin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityRegist extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText etnama, etemail, etpw;
    Button registbtn, buttongoogle;
    TextView tvloginpage;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        etnama = findViewById(R.id.regist_nama);
        etemail = findViewById(R.id.regist_email);
        etpw = findViewById(R.id.regist_pw);
        registbtn = findViewById(R.id.btn_signup);
        tvloginpage = findViewById(R.id.tv_loginpage);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbarRegist);

        registbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to method regist below this oncreate method
                registUser();
            }
        });

        // create intent for textview login to go to login page
        tvloginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityLogin.class));
            }
        });

/*        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        buttongoogle = findViewById(R.id.btngoogleregist);
        buttongoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });*/
    }

    //method to check the current user, if user logged in then go to main page
    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), ActivityMainPage.class));
        }
    }

    //validation dan regist method
    private void registUser() {
        final String nama = etnama.getText().toString(),
                email = etemail.getText().toString(),
                pw = etpw.getText().toString();

        //validation form
        if (nama.isEmpty()) {
            etnama.setError("Nama harus diisi!");
            etnama.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            etnama.setError("Email harus diisi!");
            etnama.requestFocus();
            return;
        }

        if (pw.isEmpty()) {
            etnama.setError("Password harus diisi!");
            etnama.requestFocus();
            return;
        }

        if (pw.length() < 6) {
            etpw.setError("Password minimal 6 karakter!");
            etnama.requestFocus();
            return;
        }

        //set progress bar to visible
        progressBar.setVisibility(View.VISIBLE);

        //regist action to store email and pw
        //AUTH WITH EMAIL AND PW
        firebaseAuth.createUserWithEmailAndPassword(email, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //creating the object Siswa and pass 3 parameters
                    Siswa obj_siswa = new Siswa(nama, email, pw);

                    //getting reference from firebase db, creating note named Siswa, go to the unique user id by creating user fb auth
                    FirebaseDatabase.getInstance().getReference("Siswa")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(obj_siswa).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //set progress bar to invisible cause the task is finish
                                progressBar.setVisibility(View.INVISIBLE);

                                Toast.makeText(ActivityRegist.this, "Registerasi berhasil!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), ActivityMainPage.class));
                            } else {
                                Toast.makeText(ActivityRegist.this, "Error " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(ActivityRegist.this, "Error " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    etnama.setText(null);
                    etemail.setText(null);
                    etpw.setText(null);
                }

            }
        });
    }

/*    //GOOGLE AUTH
    //signin method that contain intent to google signin
    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 101);
    }

    //method result from signin()
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    //method fb auth with google
    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success");
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    startActivity(new Intent(getApplicationContext(), ActivityMainPage.class));
                    finish();
                    Toast.makeText(ActivityRegist.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                    Toast.makeText(ActivityRegist.this, "Error " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }*/

}
