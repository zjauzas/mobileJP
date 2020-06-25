package com.example.jurnalprakerin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class ActivityMainPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        //configuring collapsing toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Beranda");
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.raven));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.raven));

        //creating event listener for clickable cardview
        CardView btn_list = findViewById(R.id.listaktifitas);
        CardView btn_profile = findViewById(R.id.profile);
        /*btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainPage.this, SiswaListActivity.class);
                startActivity(intent);
            }
        });*/

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainPage.this, ActivityProfile.class);
                startActivity(intent);
            }
        });

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainPage.this, ActivityDaftarAktivitas.class);
                startActivity(intent);
            }
        });

        //configuring animation for main page
        Animation uptodown, downtoup;
        AppBarLayout abl;
        NestedScrollView nsv;
        abl = findViewById(R.id.appbar);
        nsv = findViewById(R.id.nestedscrollview);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        abl.setAnimation(uptodown);
        nsv.setAnimation(downtoup);

    }
}