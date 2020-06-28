package com.example.jurnalprakerin;

public class Aktivitas {
//    public int ;
    public String nomorKegiatan, kegiatan, deskripsi_kegiatan, tempat_kegiatan, tanggal, waktu;

    public Aktivitas(String nomorKegiatan, String kegiatan, String deskripsi_kegiatan, String tempat_kegiatan, String tanggal, String waktu) {
        this.nomorKegiatan = nomorKegiatan;
        this.kegiatan = kegiatan;
        this.deskripsi_kegiatan = deskripsi_kegiatan;
        this.tempat_kegiatan = tempat_kegiatan;
        this.tanggal = tanggal;
        this.waktu = waktu;
    }

    public Aktivitas() {
    }

    public String getNomorKegiatan() {
        return nomorKegiatan;
    }

    public void setNomorKegiatan(String nomorKegiatan) {
        this.nomorKegiatan = nomorKegiatan;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    public String getdeskripsi_kegiatan() {
        return deskripsi_kegiatan;
    }

    public void setdeskripsi_kegiatan(String tempat_kegiatan) {
        this.deskripsi_kegiatan = tempat_kegiatan;
    }

    public String gettempat_kegiatan() {
        return tempat_kegiatan;
    }

    public void settempat_kegiatan(String tempat_kegiatan) {
        this.tempat_kegiatan = tempat_kegiatan;
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
