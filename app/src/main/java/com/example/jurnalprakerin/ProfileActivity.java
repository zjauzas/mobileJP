package com.example.jurnalprakerin;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends Activity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
        setContentView(R.layout.profile);

        TextView tvnama = findViewById(R.id.tvNama),
                tvrole = findViewById(R.id.tvRole),
                tvemail = findViewById(R.id.tvEmail),
                tvloginnama = findViewById(R.id.tv_namaMain);

        tvloginnama.setText(Preferences.getLoggedInUser(getBaseContext()));

        /*findViewById(R.id.button_logoutMain).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });*/

        Button update = findViewById(R.id.btn_profileUpdate);
        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ProfileActivity.this, ProfileEditActivity.class);
                startActivity(intent);
            }
        });

        Button profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        Button home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        String nama, role, email;
        Intent intent = getIntent();
        nama = intent.getStringExtra("namaada");
        role = intent.getStringExtra("roleada");
        email = intent.getStringExtra("emailada");

        if(nama == null && role == null && email == null)
        {
            tvnama.setText("Nama");
            tvrole.setText("Role");
            tvemail.setText("Email");
        }
        else
        {
            tvnama.setText(nama);
            tvrole.setText(role);
            tvemail.setText(email);
        }

        //Toast.makeText(this, nama+role+email, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_profileUpdate:
                TextView tvnama = findViewById(R.id.tvNama),
                        tvrole = findViewById(R.id.tvRole),
                        tvemail = findViewById(R.id.tvEmail);

                String nama, role, email;

                Intent intent = getIntent();
                nama = intent.getStringExtra("namaada");
                role = intent.getStringExtra("roleada");
                email = intent.getStringExtra("emailada");
                tvnama.setText(nama);
                tvrole.setText(role);
                tvemail.setText(email);

                Toast.makeText(this, nama+role+email, Toast.LENGTH_LONG).show();
                break;
        }

    }

    public void logout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        onBackPressed();
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Yakin Ingin Keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                Preferences.clearLoggedInUser(getBaseContext());
                startActivity(new Intent(getBaseContext(),LoginActivity.class));
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
