package com.example.jurnalprakerin;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SiswaListActivity extends Activity implements SearchView.OnQueryTextListener
{
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[] {DBHelper._ID_SISWA, DBHelper.NAMA_SISWA, DBHelper.NIS_SISWA,
            DBHelper.TGL_LAHIR, DBHelper.JK, DBHelper.NO_HP, DBHelper.NOTLP_ORTU, DBHelper.ALAMAT_SISWA, DBHelper.NII_SISWA};
    final int[] to = new int[] {R.id.id, R.id.namaSiswa, R.id.nis, R.id.tgl_lahir, R.id.jk, R.id.nohp, R.id.notlp, R.id.alamat, R.id.nii};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
        setContentView(R.layout.siswa_list);
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetchSiswa();
        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));
        adapter = new SimpleCursorAdapter(this, R.layout.siswa_fragment, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId)
            {
                TextView tvId = view.findViewById(R.id.id),
                        tvnama = view.findViewById(R.id.namaSiswa),
                        tvnis = view.findViewById(R.id.nis),
                        tvtgllahir = view.findViewById(R.id.tgl_lahir),
                        tvjk = view.findViewById(R.id.jk),
                        tvnohp = view.findViewById(R.id.nohp),
                        tvnotlp = view.findViewById(R.id.notlp),
                        tvtvalamat = view.findViewById(R.id.alamat),
                        tvnii = view.findViewById(R.id.nii);
                String id = tvId.getText().toString(),
                        nama = tvnama.getText().toString(),
                        nis = tvnis.getText().toString(),
                        tgllahir = tvtgllahir.getText().toString(),
                        jk = tvjk.getText().toString(),
                        nohp = tvnohp.getText().toString(),
                        notlp = tvnotlp.getText().toString(),
                        alamat = tvtvalamat.getText().toString(),
                        nii = tvnii.getText().toString();
                Intent modify_intent = new Intent(getApplicationContext(), SiswaUDActivity.class);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("nama", nama);
                modify_intent.putExtra("nis", nis);
                modify_intent.putExtra("tgllahir", tgllahir);
                modify_intent.putExtra("jk", jk);
                modify_intent.putExtra("nohp", nohp);
                modify_intent.putExtra("notlp", notlp);
                modify_intent.putExtra("alamat", alamat);
                modify_intent.putExtra("nii", nii);
                startActivity(modify_intent);
            }
        });

        ImageButton fabbuat = findViewById(R.id.fabBuatan);
        fabbuat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(SiswaListActivity.this, SiswaAddActivity.class);
                startActivity(intent);
            }
        });

        ImageButton backsiswa = findViewById(R.id.backSiswa);
        backsiswa.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SiswaListActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        SearchView sv = (SearchView) findViewById(R.id.search);
        sv.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        SearchView sv = (SearchView) findViewById(R.id.search);
        String search = sv.getQuery().toString();
        Cursor cursor1 = dbManager.fetchFilterSiswa(search);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.siswa_fragment, cursor1, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        return false;
    }
}
