package com.example.jurnalprakerin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SiswaUDActivity extends Activity implements View.OnClickListener
{
    private Button updateBtn, deleteBtn;
    private EditText etnama, etnis, ettgl_lahir, etjk, etno_hp, etnotlp_ortu, etalamat, etnii;
    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
        setTitle("Update Data");
        setContentView(R.layout.siswa_ud);
        dbManager = new DBManager(this);
        dbManager.open();
        etnama = findViewById(R.id.etg_nama);
        etnis = findViewById(R.id.etg_nis);
        ettgl_lahir = findViewById(R.id.etg_tl);
        etjk = findViewById(R.id.etg_jk);
        etno_hp = findViewById(R.id.etg_nohp);
        etnotlp_ortu = findViewById(R.id.etg_notlportu);
        etalamat = findViewById(R.id.etg_alamat);
        etnii = findViewById(R.id.etg_nii);
        updateBtn = findViewById(R.id.btn_update);
        deleteBtn = findViewById(R.id.btn_delete);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nama = intent.getStringExtra("nama");
        String nis = intent.getStringExtra("nis");
        String tgllahir = intent.getStringExtra("tgllahir");
        String jk = intent.getStringExtra("jk");
        String nohp = intent.getStringExtra("nohp");
        String notlp = intent.getStringExtra("notlp");
        String alamat = intent.getStringExtra("alamat");
        String nii = intent.getStringExtra("nii");
        _id = Long.parseLong(id);
        etnama.setText(nama);
        etnis.setText(nis);
        ettgl_lahir.setText(tgllahir);
        etjk.setText(jk);
        etno_hp.setText(nohp);
        etnotlp_ortu.setText(notlp);
        etalamat.setText(alamat);
        etnii.setText(nii);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_update:
                String nama = etnama.getText().toString(),
                        nis = etnis.getText().toString(),
                        tgllahir = ettgl_lahir.getText().toString(),
                        jk = etjk.getText().toString(),
                        nohp = etno_hp.getText().toString(),
                        notlp = etnotlp_ortu.getText().toString(),
                        alamat = etalamat.getText().toString(),
                        nii = etnii.getText().toString();
                dbManager.updateSiswa(_id, nama, nis, tgllahir, jk, nohp, notlp, alamat, nii);
                this.returnBack();
                break;
            case R.id.btn_delete:
                dbManager.deleteSiswa(_id);
                this.returnBack();
                break;
        }
    }

    public void returnBack()
    {
        Intent back = new Intent(getApplicationContext(), SiswaListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(back);
    }
}
