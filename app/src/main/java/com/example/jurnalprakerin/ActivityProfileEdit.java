package com.example.jurnalprakerin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ActivityProfileEdit extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    private Siswa siswa = new Siswa();
    EditText etg_tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

        etg_tl = findViewById(R.id.etg_tl);
        etg_tl.setInputType(InputType.TYPE_NULL);
        etg_tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(etg_tl);
            }
        });

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

    private void showDateDialog(final EditText etg_tl) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                etg_tl.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };

        new DatePickerDialog(ActivityProfileEdit.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
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
                    AppCompatSpinner etgjk = findViewById(R.id.etg_jk);
                    EditText etgnope = findViewById(R.id.etg_nohp);
                    EditText etgalamat = findViewById(R.id.etg_alamat);
                    AppCompatSpinner etgTempatPrakerin = findViewById(R.id.etg_tempatprakerin);
                    EditText etgNamaPembimbing = findViewById(R.id.etg_namapembimbing);

                    etgnama.setText(nama);
                    etgnis.setText(nis);
                    etgttl.setText(ttl);
                    etgjk.setSelection(getjk(etgjk, jk));
                    etgnope.setText(nope);
                    etgalamat.setText(alamat);
                    etgTempatPrakerin.setSelection(gettp(etgTempatPrakerin, tempatPrakerin));

                    etgNamaPembimbing.setText(namaPembimbing);
                } else {
//                    Toast.makeText(ActivityProfileEdit.this, "ncan aya", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int gettp(Spinner etgTempatPrakerin, String tempatPrakerin) {

        int index = 0;

        for (int i = 0; i < etgTempatPrakerin.getCount(); i++) {
            if (etgTempatPrakerin.getItemAtPosition(i).equals(tempatPrakerin)) {
                index = i;
            }
        }
        return index;
    }

    private int getjk(Spinner etgjk, String jk) {

        int index = 0;

        for (int i = 0; i < etgjk.getCount(); i++) {
            if (etgjk.getItemAtPosition(i).equals(jk)) {
                index = i;
            }
        }
        return index;
    }

    private void addSiswa() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("siswa/");

        EditText etgnama = findViewById(R.id.etg_nama);
        EditText etgnis = findViewById(R.id.etg_nis);
        EditText etgttl = findViewById(R.id.etg_tl);
        Spinner etgjk = findViewById(R.id.etg_jk);
        EditText etgnope = findViewById(R.id.etg_nohp);
        EditText etgalamat = findViewById(R.id.etg_alamat);
        Spinner etgTempatPrakerin = findViewById(R.id.etg_tempatprakerin);
        EditText etgNamaPembimbing = findViewById(R.id.etg_namapembimbing);

        String nama = etgnama.getText().toString();
        String email = firebaseUser.getEmail().toString();
        String nis = etgnis.getText().toString();
        String ttl = etgttl.getText().toString();
        String jk = etgjk.getSelectedItem().toString();
        String nope = etgnope.getText().toString();
        String alamat = etgalamat.getText().toString();
        String tempatPrakerin = etgTempatPrakerin.getSelectedItem().toString();
        String namaPembimbing = etgNamaPembimbing.getText().toString();

        if (TextUtils.isEmpty(nama) || TextUtils.isEmpty(nis) ||
                TextUtils.isEmpty(ttl) || TextUtils.isEmpty(jk) ||
                TextUtils.isEmpty(nope) || TextUtils.isEmpty(alamat) ||
                TextUtils.isEmpty(tempatPrakerin) || TextUtils.isEmpty(namaPembimbing)) {
            etgnama.setError("Nama harus diisi!");
            etgnis.setError("NIS harus diisi!");
            etgttl.setError("Tanggal Lahir harus diisi!");
            etgjk.setSelection(0);
            etgnope.setError("Nomor Telepon harus diisi!");
            etgalamat.setError("Alamat harus diisi!");
            etgTempatPrakerin.setSelection(0);
            etgNamaPembimbing.setError("Nama Pembimbing harus diisi!");
            return;
        } else {
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
    }

    private void updateSiswa() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("siswa/" + firebaseUser.getUid());

//        databaseReference = FirebaseDatabase.getInstance().getReference().child("siswa/");

        EditText etgnama = findViewById(R.id.etg_nama);
        EditText etgnis = findViewById(R.id.etg_nis);
        EditText etgttl = findViewById(R.id.etg_tl);
        Spinner etgjk = findViewById(R.id.etg_jk);
        EditText etgnope = findViewById(R.id.etg_nohp);
        EditText etgalamat = findViewById(R.id.etg_alamat);
        Spinner etgTempatPrakerin = findViewById(R.id.etg_tempatprakerin);
        EditText etgNamaPembimbing = findViewById(R.id.etg_namapembimbing);

        String nama = etgnama.getText().toString();
        String email = firebaseUser.getEmail().toString();
        String nis = etgnis.getText().toString();
        String ttl = etgttl.getText().toString();
        String jk = etgjk.getSelectedItem().toString();
        String nope = etgnope.getText().toString();
        String alamat = etgalamat.getText().toString();
        String tempatPrakerin = etgTempatPrakerin.getSelectedItem().toString();
        String namaPembimbing = etgNamaPembimbing.getText().toString();

        if (TextUtils.isEmpty(nama) || TextUtils.isEmpty(nis) ||
                TextUtils.isEmpty(ttl) || TextUtils.isEmpty(jk) ||
                TextUtils.isEmpty(nope) || TextUtils.isEmpty(alamat) ||
                TextUtils.isEmpty(tempatPrakerin) || TextUtils.isEmpty(namaPembimbing)) {
            etgnama.setError("Nama harus diisi!");
            etgnis.setError("NIS harus diisi!");
            etgttl.setError("Tanggal Lahir harus diisi!");
            etgjk.setSelection(0);
            etgnope.setError("Nomor Telepon harus diisi!");
            etgalamat.setError("Alamat harus diisi!");
            etgTempatPrakerin.setSelection(0);
            etgNamaPembimbing.setError("Nama Pembimbing harus diisi!");
            return;
        } else {
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
}