package com.example.jurnalprakerin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.CollapsingToolbarLayout;

//Activity implements View.OnClickListener

public class ActivityProfile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle("Data Diri");
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.putih));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.abuabuprimary));

        Button btnedit = findViewById(R.id.btn_editprofile);
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityProfile.this, ActivityProfileEdit.class);
                startActivity(intent);
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


/*

        TextView tvnama = findViewById(R.id.tvNama),
                tvrole = findViewById(R.id.tvRole),
                tvemail = findViewById(R.id.tvEmail),
                tvloginnama = findViewById(R.id.tv_namaMain);

        tvloginnama.setText(Preferences.getLoggedInUser(getBaseContext()));

        */
/*findViewById(R.id.button_logoutMain).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });*//*


        Button update = findViewById(R.id.btn_profileUpdate);
        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ActivityProfile.this, ProfileEditActivity.class);
                startActivity(intent);
            }
        });

        Button activity_profile = findViewById(R.id.activity_profile);
        activity_profile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ActivityProfile.this, ActivityProfile.class);
                startActivity(intent);
            }
        });

        Button home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ActivityProfile.this, HomeActivity.class);
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
                startActivity(new Intent(getBaseContext(), ActivityLogin.class));
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
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menusetting:
                startActivity(new Intent(this, ActivityMainPage.class));
                return true;
            default:
                return true;
        }


    }
}
