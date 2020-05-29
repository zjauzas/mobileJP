package com.example.jurnalprakerin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GuruAddActivity extends Activity implements View.OnClickListener

{
    Button add;
    EditText etnama, etnip, etemail, etno_tlp, etalamat;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
        setTitle("Tambah Data Guru");
        setContentView(R.layout.guru_add);
        add = findViewById(R.id.add_record);
        etnama = findViewById(R.id.etg_nama);
        etnip = findViewById(R.id.etg_nip);
        etemail = findViewById(R.id.etg_email);
        etno_tlp = findViewById(R.id.etg_notlp);
        etalamat = findViewById(R.id.etg_alamat);
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
                        nip = etnip.getText().toString(),
                        email = etemail.getText().toString(),
                        no_tlp = etno_tlp.getText().toString(),
                        alamat = etalamat.getText().toString();
                dbManager.insertGuru(nama, nip, email, no_tlp, alamat);
                Intent main = new Intent(GuruAddActivity.this, GuruListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }
    }

}
