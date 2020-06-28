package com.example.jurnalprakerin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Activity implements View.OnClickListener

public class ActivityProfile extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle("Data Diri");
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.raven));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.lightestDaisy));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityProfile.this, ActivityMainPage.class);
                startActivity(intent);
            }
        });

        Button btnedit = findViewById(R.id.btn_editprofile);
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityProfile.this, ActivityProfileEdit.class);
                startActivity(intent);
            }
        });

        CardView signout = findViewById(R.id.cvsignout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        display();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void display(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("siswa/"+firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String nama = dataSnapshot.child("nama").getValue().toString();
                    String nis = dataSnapshot.child("nis").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();
                    String ttl = dataSnapshot.child("ttl").getValue().toString();
                    String jk = dataSnapshot.child("jk").getValue().toString();
                    String nope = dataSnapshot.child("nomortelepon").getValue().toString();
                    String alamat = dataSnapshot.child("alamat").getValue().toString();
                    String tempatPrakerin = dataSnapshot.child("tempatPrakerin").getValue().toString();
                    String namaPembimbing = dataSnapshot.child("namaPembimbing").getValue().toString();

                    TextView tvnama = findViewById(R.id.nameprofile);
                    TextView tvnis = findViewById(R.id.nisprofile);
                    TextView tvemail = findViewById(R.id.emailprofile);
                    TextView tvttl = findViewById(R.id.birthdateprofile);
                    TextView tvjk = findViewById(R.id.genderprofile);
                    TextView tvnope = findViewById(R.id.phonenumprofile);
                    TextView tvalamat = findViewById(R.id.houseaddrprofile);
                    TextView tvTempatPrakerin = findViewById(R.id.industryaddrprofile);
                    TextView tvNamaPembimbing = findViewById(R.id.prakerinsupervisorprofile);

                    tvnama.setText(nama);
                    tvnis.setText(nis);
                    tvemail.setText(email);
                    tvttl.setText(ttl);
                    tvjk.setText(jk);
                    tvnope.setText(nope);
                    tvalamat.setText(alamat);
                    tvTempatPrakerin.setText(tempatPrakerin);
                    tvNamaPembimbing.setText(namaPembimbing);
                } else {
//                    Toast.makeText(ActivityProfile.this, "teu aya", Toast.LENGTH_LONG).show();
                    String email = firebaseUser.getEmail().toString();
                    TextView tvemail = findViewById(R.id.emailprofile);
                    tvemail.setText(email);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Yakin Ingin Keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                FirebaseAuth.getInstance().signOut();
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
    }

/*    @Override
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
    }*/
}
