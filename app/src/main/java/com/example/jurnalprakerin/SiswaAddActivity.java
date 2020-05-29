package com.example.jurnalprakerin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SiswaAddActivity extends Activity implements View.OnClickListener
{
    Button add;
    EditText etnama, etnis, etnii;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
        setTitle("Tambah Data Siswa");
        setContentView(R.layout.siswa_add);
        add = findViewById(R.id.add_record);
        etnama = findViewById(R.id.etg_nama);
        etnis = findViewById(R.id.etg_nis);
        etnii = findViewById(R.id.etg_nii);
        dbManager = new DBManager(this);
        dbManager.open();
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.add_record:
                final String nama = etnama.getText().toString(),
                             nis = etnis.getText().toString(),
                             nii = etnii.getText().toString();
                dbManager.insertSiswa(nama, nis, nii);
                Intent main = new Intent(SiswaAddActivity.this, SiswaListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }
    }
}
