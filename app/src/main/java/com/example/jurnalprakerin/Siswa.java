package com.example.jurnalprakerin;

public class Siswa {
    public String nama, email, nis, ttl, jk, nomortelepon, alamat, tempatPrakerin, namaPembimbing;

    public Siswa(){
    }

    public Siswa(String nama, String email, String nis, String ttl, String jk, String nomortelepon, String alamat, String tempatPrakerin, String namaPembimbing) {
        this.nama = nama;
        this.email = email;
        this.nis = nis;
        this.ttl = ttl;
        this.jk = jk;
        this.nomortelepon = nomortelepon;
        this.alamat = alamat;
        this.tempatPrakerin = tempatPrakerin;
        this.namaPembimbing = namaPembimbing;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getnomortelepon() {
        return nomortelepon;
    }

    public void setnomortelepon(String nomortelepon) {
        this.nomortelepon = nomortelepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTempatPrakerin() {
        return tempatPrakerin;
    }

    public void setTempatPrakerin(String tempatPrakerin) {
        this.tempatPrakerin = tempatPrakerin;
    }

    public String getNamaPembimbing() {
        return namaPembimbing;
    }

    public void setNamaPembimbing(String namaPembimbing) {
        this.namaPembimbing = namaPembimbing;
    }
}
