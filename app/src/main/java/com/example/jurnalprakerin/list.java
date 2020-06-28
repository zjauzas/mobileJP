package com.example.jurnalprakerin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class list extends ArrayAdapter<Aktivitas> {
    private Activity context;
    private List<Aktivitas> aktivitasList;

    public list(Activity context, List<Aktivitas> aktivitasList) {
        super(context, R.layout.list_fragment, aktivitasList);
        this.context = context;
        this.aktivitasList = aktivitasList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.list_fragment, null, true);

        TextView nokegTV = view.findViewById(R.id.nomorKegiatanTV);
        TextView kegTV = view.findViewById(R.id.kegiatanTV);
        TextView desKegTV = view.findViewById(R.id.deskripsiTV);
        TextView tempatTV = view.findViewById(R.id.tempatTV);
        TextView tangalTV = view.findViewById(R.id.tanggalTV);
        TextView waktuTV = view.findViewById(R.id.waktuTV);

        Aktivitas aktivitas = aktivitasList.get(position);

        nokegTV.setText(String.valueOf(aktivitas.getNomorKegiatan()));
        kegTV.setText(aktivitas.getKegiatan());
        desKegTV.setText(aktivitas.getdeskripsi_kegiatan());
        tempatTV.setText(aktivitas.gettempat_kegiatan());
        tangalTV.setText(aktivitas.getTanggal());
        waktuTV.setText(aktivitas.getWaktu());

        return view;
    }
}
