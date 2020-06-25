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

import java.util.HashMap;
import java.util.Map;

public class ActivityProfileEdit extends Activity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    private Siswa siswa = new Siswa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

        display();

        findViewById(R.id.btncloseprofile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), ActivityProfile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
            }
        });

        findViewById(R.id.btnupdateprofile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("siswa/" + firebaseUser.getUid());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            updateSiswa();
                        } else {
                            addSiswa();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void display() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("siswa/" + firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String nama = dataSnapshot.child("nama").getValue().toString();
                    String nis = dataSnapshot.child("nis").getValue().toString();
                    String ttl = dataSnapshot.child("ttl").getValue().toString();
                    String jk = dataSnapshot.child("jk").getValue().toString();
                    String nope = dataSnapshot.child("nomortelepon").getValue().toString();
                    String alamat = dataSnapshot.child("alamat").getValue().toString();
                    String tempatPrakerin = dataSnapshot.child("tempatPrakerin").getValue().toString();
                    String namaPembimbing = dataSnapshot.child("namaPembimbing").getValue().toString();

                    EditText etgnama = findViewById(R.id.etg_nama);
                    EditText etgnis = findViewById(R.id.etg_nis);
                    EditText etgttl = findViewById(R.id.etg_tl);
                    EditText etgjk = findViewById(R.id.etg_jk);
                    EditText etgnope = findViewById(R.id.etg_nohp);
                    EditText etgalamat = findViewById(R.id.etg_alamat);
                    EditText etgTempatPrakerin = findViewById(R.id.etg_tempatprakerin);
                    EditText etgNamaPembimbing = findViewById(R.id.etg_namapembimbing);

                    etgnama.setText(nama);
                    etgnis.setText(nis);
                    etgttl.setText(ttl);
                    etgjk.setText(jk);
                    etgnope.setText(nope);
                    etgalamat.setText(alamat);
                    etgTempatPrakerin.setText(tempatPrakerin);
                    etgNamaPembimbing.setText(namaPembimbing);
                } else {
                    Toast.makeText(ActivityProfileEdit.this, "ncan aya", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addSiswa() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("siswa/");

        EditText etgnama = findViewById(R.id.etg_nama);
        EditText etgnis = findViewById(R.id.etg_nis);
        EditText etgttl = findViewById(R.id.etg_tl);
        EditText etgjk = findViewById(R.id.etg_jk);
        EditText etgnope = findViewById(R.id.etg_nohp);
        EditText etgalamat = findViewById(R.id.etg_alamat);
        EditText etgTempatPrakerin = findViewById(R.id.etg_tempatprakerin);
        EditText etgNamaPembimbing = findViewById(R.id.etg_namapembimbing);

        String nama = etgnama.getText().toString();
        String email = firebaseUser.getEmail().toString();
        String nis = etgnis.getText().toString();
        String ttl = etgttl.getText().toString();
        String jk = etgjk.getText().toString();
        String nope = etgnope.getText().toString();
        String alamat = etgalamat.getText().toString();
        String tempatPrakerin = etgTempatPrakerin.getText().toString();
        String namaPembimbing = etgNamaPembimbing.getText().toString();

        siswa.setNama(nama);
        siswa.setEmail(email);
        siswa.setNis(nis);
        siswa.setTtl(ttl);
        siswa.setJk(jk);
        siswa.setnomortelepon(nope);
        siswa.setAlamat(alamat);
        siswa.setTempatPrakerin(tempatPrakerin);
        siswa.setNamaPembimbing(namaPembimbing);

        databaseReference.child(String.valueOf(firebaseUser.getUid())).setValue(siswa).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ActivityProfileEdit.this, "Data Diri diperbarui.", Toast.LENGTH_LONG).show();
                Intent back = new Intent(getApplicationContext(), ActivityProfile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ActivityProfileEdit.this, "Error " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateSiswa() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("siswa/" + firebaseUser.getUid());

//        databaseReference = FirebaseDatabase.getInstance().getReference().child("siswa/");

        EditText etgnama = findViewById(R.id.etg_nama);
        EditText etgnis = findViewById(R.id.etg_nis);
        EditText etgttl = findViewById(R.id.etg_tl);
        EditText etgjk = findViewById(R.id.etg_jk);
        EditText etgnope = findViewById(R.id.etg_nohp);
        EditText etgalamat = findViewById(R.id.etg_alamat);
        EditText etgTempatPrakerin = findViewById(R.id.etg_tempatprakerin);
        EditText etgNamaPembimbing = findViewById(R.id.etg_namapembimbing);

        String nama = etgnama.getText().toString();
        String email = firebaseUser.getEmail().toString();
        String nis = etgnis.getText().toString();
        String ttl = etgttl.getText().toString();
        String jk = etgjk.getText().toString();
        String nope = etgnope.getText().toString();
        String alamat = etgalamat.getText().toString();
        String tempatPrakerin = etgTempatPrakerin.getText().toString();
        String namaPembimbing = etgNamaPembimbing.getText().toString();

        Map<String, Object> hashmap = new HashMap<>();
        hashmap.put("nama", nama);
        hashmap.put("email", email);
        hashmap.put("nis", nis);
        hashmap.put("ttl", ttl);
        hashmap.put("jk", jk);
        hashmap.put("nomortelepon", nope);
        hashmap.put("alamat", alamat);
        hashmap.put("tempatPrakerin", tempatPrakerin);
        hashmap.put("namaPembimbing", namaPembimbing);
        databaseReference.updateChildren(hashmap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ActivityProfileEdit.this, "Data Diri diperbarui.", Toast.LENGTH_LONG).show();
                Intent back = new Intent(getApplicationContext(), ActivityProfile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ActivityProfileEdit.this, "Error " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}