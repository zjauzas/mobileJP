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

public class GuruListActivity extends Activity implements SearchView.OnQueryTextListener

{
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[] {DBHelper._ID_GURU, DBHelper.NAMA_GURU, DBHelper.NIP,
            DBHelper.EMAIL, DBHelper.NO_TLP_GURU, DBHelper.ALAMAT_GURU};
    final int[] to = new int[] {R.id.id, R.id.namaGuru, R.id.nipGuru, R.id.email, R.id.notlp, R.id.alamat};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
        setContentView(R.layout.guru_list);
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetchGuru();
        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));
        adapter = new SimpleCursorAdapter(this, R.layout.guru_fragment, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId)
            {
                TextView tvId = view.findViewById(R.id.id);
                TextView tvNama = view.findViewById(R.id.namaGuru);
                TextView tvNip = view.findViewById(R.id.nipGuru);
                TextView tvemail = view.findViewById(R.id.email);
                TextView tvnotlp = view.findViewById(R.id.notlp);
                TextView tvalamat = view.findViewById(R.id.alamat);
                String id = tvId.getText().toString();
                String nama = tvNama.getText().toString();
                String nip = tvNip.getText().toString();
                String email = tvemail.getText().toString();
                String notlp = tvnotlp.getText().toString();
                String alamat = tvalamat.getText().toString();
                Intent modify_intent = new Intent(getApplicationContext(), GuruUDActivity.class);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("nama", nama);
                modify_intent.putExtra("nip", nip);
                modify_intent.putExtra("email", email);
                modify_intent.putExtra("notlp", notlp);
                modify_intent.putExtra("alamat", alamat);
                startActivity(modify_intent);
            }
        });

        ImageButton fabbuat = findViewById(R.id.fabBuatan);
        fabbuat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(GuruListActivity.this, GuruAddActivity.class);
                startActivity(intent);
            }
        });

        ImageButton backguru = findViewById(R.id.backGuru);
        backguru.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(GuruListActivity.this, HomeActivity.class);
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
        Cursor cursor1 = dbManager.fetchFilterGuru(search);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.guru_fragment, cursor1, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        return false;
    }
}
