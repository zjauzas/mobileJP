package com.example.jurnalprakerin;

public class Aktivitas {
    public int nomorKegiatan;
    public String kegiatan, deskripsiKegiatan, tempatKegiatan, tanggal, waktu;

    public Aktivitas() {
    }

    public int getNomorKegiatan() {
        return nomorKegiatan;
    }

    public void setNomorKegiatan(int nomorKegiatan) {
        this.nomorKegiatan = nomorKegiatan;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    public String getDeskripsiKegiatan() {
        return deskripsiKegiatan;
    }

    public void setDeskripsiKegiatan(String deskripsiKegiatan) {
        this.deskripsiKegiatan = deskripsiKegiatan;
    }

    public String getTempatKegiatan() {
        return tempatKegiatan;
    }

    public void setTempatKegiatan(String tempatKegiatan) {
        this.tempatKegiatan = tempatKegiatan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
