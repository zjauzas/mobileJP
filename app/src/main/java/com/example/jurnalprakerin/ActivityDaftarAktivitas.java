package com.example.jurnalprakerin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityDaftarAktivitas extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("aktivitasPrakerin/" + firebaseUser.getUid());

    ListView listView;

    List<Aktivitas> aktivitasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        setContentView(R.layout.activity_daftar_aktivitas);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle("Daftar Aktivitas");
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.raven));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.raven));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityDaftarAktivitas.this, ActivityMainPage.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDaftarAktivitas.this, ActivityDaftarAktivitasAdd.class);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.list_view);

        aktivitasList = new ArrayList<>();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Aktivitas aktivitas = aktivitasList.get(position);
                Intent intent = new Intent(getApplicationContext(), ActivityDaftarAktivitasUpdateDelete.class);
                intent.putExtra("nomorKegiatan", aktivitas.getNomorKegiatan());
                intent.putExtra("kegiatan", aktivitas.getKegiatan());
                intent.putExtra("deskripsi_kegiatan", aktivitas.getdeskripsi_kegiatan());
                intent.putExtra("tempat_kegiatan", aktivitas.gettempat_kegiatan());
                intent.putExtra("tanggal", aktivitas.getTanggal());
                intent.putExtra("waktu", aktivitas.getWaktu());
                startActivity(intent);
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    aktivitasList.clear();
                    for (DataSnapshot aktivitasSnapshot : dataSnapshot.getChildren()) {
                        Aktivitas aktivitas = aktivitasSnapshot.getValue(Aktivitas.class);
                        aktivitasList.add(aktivitas);
                    }
                    list list = new list(ActivityDaftarAktivitas.this, aktivitasList);
                    listView.setAdapter(list);
                } else {
                    TextView tvEmpty = findViewById(R.id.empty);
                    tvEmpty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
