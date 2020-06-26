package com.example.jurnalprakerin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityDaftarAktivitasAdd extends Activity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    private Aktivitas aktivitas = new Aktivitas();

    long nomorKegiatan=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aktivitas);

        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

        findViewById(R.id.btncloseaddaktivitas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), ActivityProfile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("aktivitasPrakerin/" + firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    nomorKegiatan= (long) dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        findViewById(R.id.btnAddAktivitas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAktivitas();
            }
        });
    }

    private void addAktivitas() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("aktivitasPrakerin/"+firebaseUser.getUid());

        EditText etgkegiatan = findViewById(R.id.etg_keg);
        EditText etgdeskeg = findViewById(R.id.etg_desKeg);
        EditText etgtempatkegiatan = findViewById(R.id.etg_tempat);
        EditText etgtanggal = findViewById(R.id.etg_tanggal);
        EditText etgwaktu = findViewById(R.id.etg_waktu);

        String kegiatan = etgkegiatan.getText().toString();
        String deskeg = etgdeskeg.getText().toString();
        String tempatkegiatan = etgtempatkegiatan.getText().toString();
        String tanggal = etgtanggal.getText().toString();
        String waktu = etgwaktu.getText().toString();


        aktivitas.setNomorKegiatan(String.valueOf(nomorKegiatan));
        aktivitas.setKegiatan(kegiatan);
        aktivitas.setDeskripsiKegiatan(deskeg);
        aktivitas.setTempatKegiatan(tempatkegiatan);
        aktivitas.setTanggal(tanggal);
        aktivitas.setWaktu(waktu);

        databaseReference.child(String.valueOf(nomorKegiatan+1)).setValue(aktivitas).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ActivityDaftarAktivitasAdd.this, "Aktivitas ditambahkan.", Toast.LENGTH_LONG).show();
                Intent back = new Intent(getApplicationContext(), ActivityDaftarAktivitas.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ActivityDaftarAktivitasAdd.this, "Error " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    
}
