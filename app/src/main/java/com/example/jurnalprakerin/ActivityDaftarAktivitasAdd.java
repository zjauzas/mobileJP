package com.example.jurnalprakerin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ActivityDaftarAktivitasAdd extends AppCompatActivity {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("aktivitasPrakerin/" + firebaseUser.getUid());

    private Aktivitas aktivitas = new Aktivitas();

    long nomorKegiatan;
    EditText etgtanggal;
    EditText etgwaktu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aktivitas);

        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

        etgtanggal = findViewById(R.id.etg_tanggal);
        etgwaktu = findViewById(R.id.etg_waktu);
        etgtanggal.setInputType(InputType.TYPE_NULL);
        etgwaktu.setInputType(InputType.TYPE_NULL);

        etgtanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(etgtanggal);
            }
        });

        etgwaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(etgwaktu);
            }
        });

        findViewById(R.id.btncloseaddaktivitas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), ActivityDaftarAktivitas.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
            }
        });

        findViewById(R.id.btnAddAktivitas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAktivitas();
            }
        });

        nomorKegiatan = 1;
        databaseReference.limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {
                    if (dataSnapshot.hasChild("nomorKegiatan")) {
                        String nokeg = (String) dataSnapshot.child("nomorKegiatan").getValue().toString();
                        nomorKegiatan = (Long.valueOf(nokeg) + 1);
//                        Toast.makeText(ActivityDaftarAktivitasAdd.this, "anjohaaa", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void showTimeDialog(final EditText etgwaktu) {
        final Calendar calendar = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                etgwaktu.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new TimePickerDialog(ActivityDaftarAktivitasAdd.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }

    private void showDateDialog(final EditText etgtanggal) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                etgtanggal.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        new DatePickerDialog(ActivityDaftarAktivitasAdd.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void addAktivitas() {
//        Toast.makeText(ActivityDaftarAktivitasAdd.this, String.valueOf(nomorKegiatan), Toast.LENGTH_LONG).show();

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

        if (TextUtils.isEmpty(kegiatan) || TextUtils.isEmpty(deskeg) ||
                TextUtils.isEmpty(tempatkegiatan) || TextUtils.isEmpty(tanggal) ||
                TextUtils.isEmpty(waktu)) {
            etgkegiatan.setError("Kegiatan harus diisi!");
            etgdeskeg.setError("Deskripsi Kegiatan harus diisi!");
            etgtempatkegiatan.setError("Tempat Kegiatan harus diisi!");
            etgtanggal.setError("Tanggal harus diisi!");
            etgwaktu.setError("Waktu harus diisi!");
            return;
        } else {
            aktivitas.setNomorKegiatan(String.valueOf(nomorKegiatan));
            aktivitas.setKegiatan(kegiatan);
            aktivitas.setdeskripsi_kegiatan(deskeg);
            aktivitas.settempat_kegiatan(tempatkegiatan);
            aktivitas.setTanggal(tanggal);
            aktivitas.setWaktu(waktu);

            databaseReference.child(String.valueOf(nomorKegiatan)).setValue(aktivitas).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(ActivityDaftarAktivitasAdd.this, "Aktivitas ditambahkan.", Toast.LENGTH_SHORT).show();
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

}