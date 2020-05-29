package com.example.jurnalprakerin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfileEditActivity extends Activity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
        setContentView(R.layout.profile_edit);
        setTitle("Perbarui Profil");

        Button updateBtn = findViewById(R.id.btn_profileUpdateEdit);

        updateBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        EditText etnama = findViewById(R.id.etNama),
                etrole = findViewById(R.id.etRole),
                etemail = findViewById(R.id.etEmail);
        String nama = etnama.getText().toString(),
                role = etrole.getText().toString(),
                email = etemail.getText().toString();

        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.putExtra("namaada", nama);
        intent.putExtra("roleada", role);
        intent.putExtra("emailada", email);
        startActivity(intent);
    }

}
