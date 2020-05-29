package com.example.jurnalprakerin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GuruUDActivity extends Activity implements View.OnClickListener
{
    private Button updateBtn, deleteBtn;
    private EditText etnama, etnip, etemail, etnotlp, etalamat;
    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
        setTitle("Update Data");
        setContentView(R.layout.guru_ud);
        dbManager = new DBManager(this);
        dbManager.open();
        etnama = findViewById(R.id.etg_nama);
        etnip = findViewById(R.id.etg_nip);
        etemail = findViewById(R.id.etg_email);
        etnotlp = findViewById(R.id.etg_notlp);
        etalamat = findViewById(R.id.etg_alamat);
        updateBtn = findViewById(R.id.btn_update);
        deleteBtn = findViewById(R.id.btn_delete);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nama = intent.getStringExtra("nama");
        String nip = intent.getStringExtra("nip");
        String email = intent.getStringExtra("email");
        String notlp = intent.getStringExtra("notlp");
        String alamat = intent.getStringExtra("alamat");
        _id = Long.parseLong(id);
        etnama.setText(nama);
        etnip.setText(nip);
        etemail.setText(email);
        etnotlp.setText(notlp);
        etalamat.setText(alamat);
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
                String nip = etnip.getText().toString();
                String email = etemail.getText().toString();
                String notlp = etnotlp.getText().toString();
                String alamat = etalamat.getText().toString();
                dbManager.updateGuru(_id, nama, nip, email, notlp, alamat);
                this.returnBack();
                break;
            case R.id.btn_delete:
                dbManager.deleteGuru(_id);
                this.returnBack();
                break;
        }
    }

    public void returnBack()
    {
        Intent back = new Intent(getApplicationContext(), GuruListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(back);
    }


}
