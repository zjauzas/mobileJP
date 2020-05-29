package com.example.jurnalprakerin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
        setContentView(R.layout.activity_home);

        Button guru = findViewById(R.id.btndafgur), industri = findViewById(R.id.btndafin), siswa = findViewById(R.id.btndafsis);

        guru.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this, GuruListActivity.class);
                startActivity(intent);
            }
        });

        industri.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this, IndustriListActivity.class);
                startActivity(intent);
            }
        });

        siswa.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this, SiswaListActivity.class);
                startActivity(intent);
            }
        });

        Button profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        Button home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


    }
}
