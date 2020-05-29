package com.example.jurnalprakerin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IndustriUDActivity extends Activity implements View.OnClickListener
{
    private Button updateBtn, deleteBtn;
    private EditText etnama, etnii, etnamaPimpinan, etalamat, etnotlp, etnofax, etnamaPembimbing;
    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
        setTitle("Update Data");
        setContentView(R.layout.industri_ud);
        dbManager = new DBManager(this);
        dbManager.open();
        etnama = findViewById(R.id.etg_nama);
        etnii = findViewById(R.id.etg_nii);
        etnamaPimpinan = findViewById(R.id.etg_namaPimpinan);
        etalamat = findViewById(R.id.etg_alamat);
        etnotlp = findViewById(R.id.etg_notlp);
        etnofax = findViewById(R.id.etg_nofax);
        etnamaPembimbing = findViewById(R.id.etg_namaPembimbing);
        updateBtn = findViewById(R.id.btn_update);
        deleteBtn = findViewById(R.id.btn_delete);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nama = intent.getStringExtra("nama");
        String nii = intent.getStringExtra("nii");
        String namapimpinan = intent.getStringExtra("namaPimpinan");
        String alamat = intent.getStringExtra("alamat");
        String notlp = intent.getStringExtra("notlp");
        String nofax = intent.getStringExtra("nofax");
        String namapembimbing = intent.getStringExtra("namaPembimbing");
        _id = Long.parseLong(id);
        etnama.setText(nama);
        etnii.setText(nii);
        etnamaPimpinan.setText(namapimpinan);
        etalamat.setText(alamat);
        etnotlp.setText(notlp);
        etnofax.setText(nofax);
        etnamaPembimbing.setText(namapembimbing);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_update:
                String nama = etnama.getText().toString();
                String nii = etnii.getText().toString();
                String namaPimpinan = etnamaPimpinan.getText().toString();
                String notlp = etnotlp.getText().toString();
                String alamat = etalamat.getText().toString();
                String nofax = etnofax.getText().toString();
                String namaPembimbing = etnamaPembimbing.getText().toString();
                dbManager.updateIndustri(_id, nama, nii, namaPimpinan, notlp, alamat, nofax, namaPembimbing);
                this.returnBack();
                break;
            case R.id.btn_delete:
                dbManager.deleteIndustri(_id);
                this.returnBack();
                break;
        }
    }

    public void returnBack()
    {
        Intent back = new Intent(getApplicationContext(), IndustriListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(back);
    }
}
