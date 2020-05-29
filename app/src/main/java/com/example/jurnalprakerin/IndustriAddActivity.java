package com.example.jurnalprakerin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IndustriAddActivity extends Activity implements View.OnClickListener
{
    Button add;
    EditText etnama, etnii;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
        setTitle("Tambah Data Industri");
        setContentView(R.layout.industri_add);
        add = findViewById(R.id.add_record);
        etnama = findViewById(R.id.etg_nama);
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
                             nii = etnii.getText().toString();
                dbManager.insertIndustri(nama, nii);
                Intent main = new Intent(IndustriAddActivity.this, IndustriListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }
    }
}
