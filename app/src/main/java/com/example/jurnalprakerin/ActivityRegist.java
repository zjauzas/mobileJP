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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityRegist extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText etnama, etemail, etpw;
    Button registbtn, buttongoogle;
    TextView tvloginpage;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

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
        final String email = etemail.getText().toString(),
                pw = etpw.getText().toString();

        if (email.isEmpty()) {
            etemail.setError("Email harus diisi!");
            etemail.requestFocus();
            return;
        }

        if (pw.isEmpty()) {
            etpw.setError("Password harus diisi!");
            etpw.requestFocus();
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
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(ActivityRegist.this, "Regist berhasil.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ActivityMainPage.class));
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(ActivityRegist.this, "Error " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    etemail.setText(null);
                    etpw.setText(null);
                }

            }
        });
    }

}
