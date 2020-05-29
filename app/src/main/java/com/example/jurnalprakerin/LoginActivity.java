package com.example.jurnalprakerin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends AppCompatActivity
{
    EditText etemail, etpw;
    Button loginbtn;
    TextView tvregistpage;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        etemail = findViewById(R.id.login_email);
        etpw = findViewById(R.id.login_pw);
        loginbtn = findViewById(R.id.btnlogin);
        tvregistpage = findViewById(R.id.tv_loginpage);
        progressBar = findViewById(R.id.progressbarLogin);

        firebaseAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String  email = etemail.getText().toString(),
                        pw = etpw.getText().toString();

                if (TextUtils.isEmpty(email)||TextUtils.isEmpty(pw))
                {
                    etemail.setError("Email harus diisi!");
                    etpw.setError("Password harus diisi!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginActivity.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        }
                        else
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginActivity.this, "Error "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            etemail.setText(null);
                            etpw.setText(null);
                        }
                    }
                });
            }
        });

        tvregistpage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

    }
}
