package com.example.jurnalprakerin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class ActivityDaftarAktivitasUpdateDelete extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    EditText etgtanggal;
    EditText etgwaktu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        setContentView(R.layout.activity_update_delete_aktivitas);

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

        findViewById(R.id.btncloseudaktivitas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), ActivityDaftarAktivitas.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
            }
        });

        Intent intent = getIntent();
        final String nomorKegiatan = intent.getStringExtra("nomorKegiatan");
//        String kegiatan = intent.getStringExtra("kegiatan");
//        String deskripsi_kegiatan = intent.getStringExtra("deskripsi_kegiatan");
//        String tempat_kegiatan = intent.getStringExtra("tempat_kegiatan");
//        String tanggal = intent.getStringExtra("tanggal");
//        String waktu = intent.getStringExtra("waktu");

        databaseReference = FirebaseDatabase.getInstance().getReference("aktivitasPrakerin/" + firebaseUser.getUid()).child(nomorKegiatan);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String kegiatan = dataSnapshot.child("kegiatan").getValue().toString();
                    String deskripsi_kegiatan = dataSnapshot.child("deskripsi_kegiatan").getValue().toString();
                    String tempat_kegiatan = dataSnapshot.child("tempat_kegiatan").getValue().toString();
                    String tanggal = dataSnapshot.child("tanggal").getValue().toString();
                    String waktu = dataSnapshot.child("waktu").getValue().toString();

                    final EditText etgkegiatan = findViewById(R.id.etg_keg);
                    final EditText etgdeskeg = findViewById(R.id.etg_desKeg);
                    final EditText etgtempat_kegiatan = findViewById(R.id.etg_tempat);
                    final EditText etgtanggal = findViewById(R.id.etg_tanggal);
                    final EditText etgwaktu = findViewById(R.id.etg_waktu);

                    etgkegiatan.setText(kegiatan);
                    etgdeskeg.setText(deskripsi_kegiatan);
                    etgtempat_kegiatan.setText(tempat_kegiatan);
                    etgtanggal.setText(tanggal);
                    etgwaktu.setText(waktu);

                    final EditText etgtanggalPicker;
                    final EditText etgwaktuPicker;

                    etgtanggalPicker = findViewById(R.id.etg_tanggal);
                    etgwaktuPicker = findViewById(R.id.etg_waktu);
                    etgtanggalPicker.setInputType(InputType.TYPE_NULL);
                    etgwaktuPicker.setInputType(InputType.TYPE_NULL);

                    etgtanggalPicker.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDateDialog(etgtanggalPicker);
                        }
                    });

                    etgwaktuPicker.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showTimeDialog(etgwaktuPicker);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final EditText etgkegiatan = findViewById(R.id.etg_keg);
        final EditText etgdeskeg = findViewById(R.id.etg_desKeg);
        final EditText etgtempat_kegiatan = findViewById(R.id.etg_tempat);
        final EditText etgtanggal = findViewById(R.id.etg_tanggal);
        final EditText etgwaktu = findViewById(R.id.etg_waktu);

        final Button btnUpdate = findViewById(R.id.btnUpdateAktivitas);
        final Button btnDelete = findViewById(R.id.btnDeleteAktivitas);
        final Button btnClose = findViewById(R.id.btncloseudaktivitas);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kegiatan = etgkegiatan.getText().toString();
                String deskeg = etgdeskeg.getText().toString();
                String tempatkegiatan = etgtempat_kegiatan.getText().toString();
                String tanggal = etgtanggal.getText().toString();
                String waktu = etgwaktu.getText().toString();

                if (TextUtils.isEmpty(kegiatan) || TextUtils.isEmpty(deskeg) ||
                        TextUtils.isEmpty(tempatkegiatan) || TextUtils.isEmpty(tanggal) ||
                        TextUtils.isEmpty(waktu)) {
                    etgkegiatan.setError("Kegiatan harus diisi!");
                    etgdeskeg.setError("Deskripsi KEgiatan harus diisi!");
                    etgtempat_kegiatan.setError("Tempat Kegiatan harus diisi!");
                    etgtanggal.setError("Tanggal harus diisi!");
                    etgwaktu.setError("Waktu harus diisi!");
                    return;
                } else {
                    updateAktivitas(nomorKegiatan, kegiatan, deskeg, tempatkegiatan, tanggal, waktu);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAktivitas(nomorKegiatan);
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), ActivityDaftarAktivitas.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
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

        new TimePickerDialog(ActivityDaftarAktivitasUpdateDelete.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
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

        new DatePickerDialog(ActivityDaftarAktivitasUpdateDelete.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private boolean updateAktivitas(String nomorKegiatan, String kegiatan, String deskripsi_kegiatan, String tempat_kegiatan, String tanggal, String waktu) {
        databaseReference = FirebaseDatabase.getInstance().getReference("aktivitasPrakerin/" + firebaseUser.getUid()).child(nomorKegiatan);
        Map<String, Object> hashmap = new HashMap<>();
        hashmap.put("nomorKegiatan", nomorKegiatan);
        hashmap.put("kegiatan", kegiatan);
        hashmap.put("deskripsi_kegiatan", deskripsi_kegiatan);
        hashmap.put("tempat_kegiatan", tempat_kegiatan);
        hashmap.put("tanggal", tanggal);
        hashmap.put("waktu", waktu);
        databaseReference.updateChildren(hashmap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Aktivitas diperbarui", Toast.LENGTH_LONG).show();
                Intent back = new Intent(getApplicationContext(), ActivityDaftarAktivitas.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return true;
    }

    private boolean deleteAktivitas(String nomorKegiatan) {
        databaseReference = FirebaseDatabase.getInstance().getReference("aktivitasPrakerin/" + firebaseUser.getUid()).child(nomorKegiatan);
        databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Aktivitas dihapus.", Toast.LENGTH_LONG).show();
                Intent back = new Intent(getApplicationContext(), ActivityDaftarAktivitas.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(back);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return true;
    }


}
