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

public class RegisterActivity extends AppCompatActivity
{
    EditText etnama, etemail, etpw;
    Button registbtn;
    TextView tvloginpage;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        etnama = findViewById(R.id.regist_nama);
        etemail = findViewById(R.id.regist_email);
        etpw = findViewById(R.id.regist_pw);
        registbtn = findViewById(R.id.btn_signup);
        tvloginpage = findViewById(R.id.tv_loginpage);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbarRegist);

        if(firebaseAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }

        registbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String  nama = etnama.getText().toString(),
                        email = etemail.getText().toString(),
                        pw = etpw.getText().toString();

                if (TextUtils.isEmpty(nama)||TextUtils.isEmpty(email)||TextUtils.isEmpty(pw))
                {
                    etnama.setError("Nama harus diisi!");
                    etemail.setError("Email harus diisi!");
                    etpw.setError("Password harus diisi!");
                    return;
                }
                else if (pw.length() < 8)
                {
                    etpw.setError("Password minimal 8 karakter!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(email,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(RegisterActivity.this, "Registerasi berhasil!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        }
                        else
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(RegisterActivity.this, "Error "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            etnama.setText(null);
                            etemail.setText(null);
                            etpw.setText(null);
                        }
                    }
                });
            }
        });

        tvloginpage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });


    }
}
