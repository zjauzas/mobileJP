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

public class IndustriListActivity extends Activity implements SearchView.OnQueryTextListener
{
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[] {DBHelper._ID_INDUSTRI, DBHelper.NAMA_INDUSTRI, DBHelper.NII_INDUSTRI,
            DBHelper.NAMA_PIMPINAN, DBHelper.NOTLP_INDUSTRI, DBHelper.NOFAX_INDUSTRI, DBHelper.ALAMAT_INDUSTRI, DBHelper.NAMA_PEMBIMBING};
    final int[] to = new int[] {R.id.id, R.id.namaIndustri, R.id.nii, R.id.namaPimpinan, R.id.notlp, R.id.nofax, R.id.alamat, R.id.namaPembimbing};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
        setContentView(R.layout.industri_list);
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetchIndustri();
        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));
        adapter = new SimpleCursorAdapter(this, R.layout.industri_fragment, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId)
            {
                TextView tvId = view.findViewById(R.id.id);
                TextView tvNama = view.findViewById(R.id.namaIndustri);
                TextView tvNii = view.findViewById(R.id.nii);
                TextView tvNamaPimpinan = view.findViewById(R.id.namaPimpinan);
                TextView tvnotlp = view.findViewById(R.id.notlp);
                TextView tvalamat = view.findViewById(R.id.alamat);
                TextView tvnofax = view.findViewById(R.id.nofax);
                TextView tvNamaPembimbing = view.findViewById(R.id.namaPembimbing);
                String id = tvId.getText().toString();
                String nama = tvNama.getText().toString();
                String nii = tvNii.getText().toString();
                String namaPimpinan = tvNamaPimpinan.getText().toString();
                String notlp = tvnotlp.getText().toString();
                String alamat = tvalamat.getText().toString();
                String nofax = tvnofax.getText().toString();
                String namaPembimbing = tvNamaPembimbing.getText().toString();
                Intent modify_intent = new Intent(getApplicationContext(), IndustriUDActivity.class);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("nama", nama);
                modify_intent.putExtra("nii", nii);
                modify_intent.putExtra("namaPimpinan", namaPimpinan);
                modify_intent.putExtra("alamat", alamat);
                modify_intent.putExtra("notlp", notlp);
                modify_intent.putExtra("nofax", nofax);
                modify_intent.putExtra("namaPembimbing", namaPembimbing);
                startActivity(modify_intent);
            }
        });

        ImageButton fabbuat = findViewById(R.id.fabBuatan);
        fabbuat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(IndustriListActivity.this, IndustriAddActivity.class);
                startActivity(intent);
            }
        });

        ImageButton backindustri = findViewById(R.id.backIndustri);
        backindustri.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(IndustriListActivity.this, HomeActivity.class);
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
        Cursor cursor1 = dbManager.fetchFilterIndustri(search);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.industri_fragment, cursor1, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        return false;
    }
}
