package com.example.jurnalprakerin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager
{
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c)
    {
        context = c;
    }

    public DBManager open() throws SQLException
    {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }
    /*

        TABEL GURU

    */
    public void insertGuru(String nama, String nip, String email, String no_tlp, String alamat)
    {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.NAMA_GURU, nama);
        contentValue.put(DBHelper.NIP, nip);
        contentValue.put(DBHelper.EMAIL, email);
        contentValue.put(DBHelper.NO_TLP_GURU, no_tlp);
        contentValue.put(DBHelper.ALAMAT_GURU, alamat);
        database.insert(DBHelper.TABLE_GURU, null, contentValue);
    }

    public Cursor fetchGuru()
    {
        String[] columns = new String[] {DBHelper._ID_GURU, DBHelper.NAMA_GURU, DBHelper.NIP, DBHelper.EMAIL, DBHelper.NO_TLP_GURU, DBHelper.ALAMAT_GURU};
        Cursor cursor = database.query(DBHelper.TABLE_GURU, columns,null, null, null, null, null);
        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateGuru(long _id, String nama, String nip, String email, String no_tlp, String alamat)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAMA_GURU, nama);
        contentValues.put(DBHelper.NIP, nip);
        contentValues.put(DBHelper.EMAIL, email);
        contentValues.put(DBHelper.NO_TLP_GURU, no_tlp);
        contentValues.put(DBHelper.ALAMAT_GURU, alamat);
        int i = database.update(DBHelper.TABLE_GURU, contentValues, DBHelper._ID_GURU + " = " + _id, null);
        return i;
    }

    public void deleteGuru(long _id)
    {
        database.delete(DBHelper.TABLE_GURU, DBHelper._ID_GURU + "=" + _id, null);
    }
    /*

    TABEL SISWA

    */
    public void insertSiswa(String nama, String nis, String nii)
    {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.NAMA_SISWA, nama);
        contentValue.put(DBHelper.NIS_SISWA, nis);
        contentValue.put(DBHelper.NII_SISWA, nii);
        database.insert(DBHelper.TABLE_SISWA, null, contentValue);
    }

    public Cursor fetchSiswa()
    {
        String[] columns = new String[] {DBHelper._ID_SISWA, DBHelper.NAMA_SISWA, DBHelper.NIS_SISWA, DBHelper.TGL_LAHIR, DBHelper.JK, DBHelper.NO_HP, DBHelper.NOTLP_ORTU, DBHelper.ALAMAT_SISWA, DBHelper.NII_SISWA};
        Cursor cursor = database.query(DBHelper.TABLE_SISWA, columns,null, null, null, null, null);
        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateSiswa(long _id, String nama, String nis, String tgl_lahir, String jk, String no_hp, String notlp_ortu, String alamat, String nii)
    {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.NAMA_SISWA, nama);
        contentValue.put(DBHelper.NIS_SISWA, nis);
        contentValue.put(DBHelper.TGL_LAHIR, tgl_lahir);
        contentValue.put(DBHelper.JK, jk);
        contentValue.put(DBHelper.NO_HP, no_hp);
        contentValue.put(DBHelper.NOTLP_ORTU, notlp_ortu);
        contentValue.put(DBHelper.ALAMAT_SISWA, alamat);
        contentValue.put(DBHelper.NII_SISWA, nii);
        int i = database.update(DBHelper.TABLE_SISWA, contentValue, DBHelper._ID_SISWA + " = " + _id, null);
        return i;
    }

    public void deleteSiswa(long _id)
    {
        database.delete(DBHelper.TABLE_SISWA, DBHelper._ID_SISWA + "=" + _id, null);
    }
    /*

    TABEL AKTIVITAS SISWA

    */
    public void insertAktivitasSiswa(String nis, String nii, String kegiatan, String des_keg, String tempat, String tanggal, String waktu)
    {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.NIS_AKSIS, nis);
        contentValue.put(DBHelper.NII, nii);
        contentValue.put(DBHelper.KEGIATAN, kegiatan);
        contentValue.put(DBHelper.DES_KEG, des_keg);
        contentValue.put(DBHelper.TEMPAT, tempat);
        contentValue.put(DBHelper.TANGGAL, tanggal);
        contentValue.put(DBHelper.WAKTU, waktu);
        database.insert(DBHelper.TABLE_AKTIVITASSISWA, null, contentValue);
    }

    public Cursor fetchAktivitasSiswa()
    {
        String[] columns = new String[] {DBHelper._ID_AKTIVITAS, DBHelper.NIS_AKSIS, DBHelper.NII, DBHelper.KEGIATAN, DBHelper.DES_KEG, DBHelper.TEMPAT, DBHelper.TANGGAL, DBHelper.WAKTU};
        Cursor cursor = database.query(DBHelper.TABLE_AKTIVITASSISWA, columns,null, null, null, null, null);
        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateAktivitasSiswa(long _id, String nis, String nii, String kegiatan, String des_keg, String tempat, String tanggal, String waktu)
    {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper._ID_AKTIVITAS, _id);
        contentValue.put(DBHelper.NIS_AKSIS, nis);
        contentValue.put(DBHelper.NII, nii);
        contentValue.put(DBHelper.KEGIATAN, kegiatan);
        contentValue.put(DBHelper.DES_KEG, des_keg);
        contentValue.put(DBHelper.TEMPAT, tempat);
        contentValue.put(DBHelper.TANGGAL, tanggal);
        contentValue.put(DBHelper.WAKTU, waktu);
        int i = database.update(DBHelper.TABLE_AKTIVITASSISWA, contentValue, DBHelper._ID_AKTIVITAS + " = " + _id, null);
        return i;
    }

    public void deleteAktivitasSiswa(long _id)
    {
        database.delete(DBHelper.TABLE_AKTIVITASSISWA, DBHelper._ID_AKTIVITAS + "=" + _id, null);
    }
    /*

        TABEL DAFTAR NILAI SISWA

    */
    public void insertDafNilSiswa(String nama, String nis, String nii, String period_prakerin, String divisi_prakerin, String nilai_prakerin, String rekomendasi)
    {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.NAMA_SISWADAFNIL, nama);
        contentValue.put(DBHelper.NIS, nis);
        contentValue.put(DBHelper.NII_DAFNIL, nii);
        contentValue.put(DBHelper.PERIODE_PRAKERIN, period_prakerin);
        contentValue.put(DBHelper.DIVISI_PRAKERIN, divisi_prakerin);
        contentValue.put(DBHelper.NILAI_PRAKERIN, nilai_prakerin);
        contentValue.put(DBHelper.REKOMENDASI, rekomendasi);
        database.insert(DBHelper.TABLE_DAFTARNILAISISWA, null, contentValue);
    }

    public Cursor fetchDafNilSiswa()
    {
        String[] columns = new String[] {DBHelper._ID_DAFNIL, DBHelper.NAMA_SISWADAFNIL, DBHelper.NIS, DBHelper.NII_DAFNIL, DBHelper.PERIODE_PRAKERIN, DBHelper.DIVISI_PRAKERIN, DBHelper.NILAI_PRAKERIN, DBHelper.REKOMENDASI};
        Cursor cursor = database.query(DBHelper.TABLE_DAFTARNILAISISWA, columns,null, null, null, null, null);
        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateDafNilSiswa(long _id, String nama, String nis, String nii, String period_prakerine, String divisi_prakerin, String nilai__prakerin, String rekomendasi)
    {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.NAMA_SISWADAFNIL, nama);
        contentValue.put(DBHelper.NIS, nis);
        contentValue.put(DBHelper.NII_DAFNIL, nii);
        contentValue.put(DBHelper.PERIODE_PRAKERIN, period_prakerine);
        contentValue.put(DBHelper.DIVISI_PRAKERIN, divisi_prakerin);
        contentValue.put(DBHelper.NILAI_PRAKERIN, nilai__prakerin);
        contentValue.put(DBHelper.REKOMENDASI, rekomendasi);
        int i = database.update(DBHelper.TABLE_DAFTARNILAISISWA, contentValue, DBHelper._ID_DAFNIL + " = " + _id, null);
        return i;
    }

    public void deleteDafNilSiswa(long _id)
    {
        database.delete(DBHelper.TABLE_DAFTARNILAISISWA, DBHelper._ID_DAFNIL + "=" + _id, null);
    }
    /*

        TABEL INDUSTRI

    */
    public void insertIndustri(String nama, String nii)
    {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.NAMA_INDUSTRI, nama);
        contentValue.put(DBHelper.NII_INDUSTRI, nii);
        database.insert(DBHelper.TABLE_INDUSTRI, null, contentValue);
    }

    public Cursor fetchIndustri()
    {
        String[] columns = new String[] {DBHelper._ID_INDUSTRI, DBHelper.NAMA_INDUSTRI, DBHelper.NII_INDUSTRI, DBHelper.NAMA_PIMPINAN, DBHelper.ALAMAT_INDUSTRI, DBHelper.NOTLP_INDUSTRI, DBHelper.NOFAX_INDUSTRI, DBHelper.NAMA_PEMBIMBING};
        Cursor cursor = database.query(DBHelper.TABLE_INDUSTRI, columns,null, null, null, null, null);
        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateIndustri(long _id, String nama, String nii, String nama_pimpinan, String alamat, String no_tlp, String no_fax, String nama_pembimbing)
    {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.NAMA_INDUSTRI, nama);
        contentValue.put(DBHelper.NII_INDUSTRI, nii);
        contentValue.put(DBHelper.NAMA_PIMPINAN, nama_pimpinan);
        contentValue.put(DBHelper.ALAMAT_INDUSTRI, alamat);
        contentValue.put(DBHelper.NOTLP_INDUSTRI, no_tlp);
        contentValue.put(DBHelper.NOFAX_INDUSTRI, no_fax);
        contentValue.put(DBHelper.NAMA_PEMBIMBING, nama_pembimbing);
        int i = database.update(DBHelper.TABLE_INDUSTRI, contentValue, DBHelper._ID_INDUSTRI + " = " + _id, null);
        return i;
    }

    public void deleteIndustri(long _id)
    {
        database.delete(DBHelper.TABLE_INDUSTRI, DBHelper._ID_INDUSTRI + "=" + _id, null);
    }
    /*

    SEARCH


     */
    public Cursor fetchFilterGuru(String input)
    {
        Cursor row = null;
        String query = "SELECT * FROM "+DBHelper.TABLE_GURU;
        if (input == null || input.length()==0)
        {
            row = database.rawQuery(query, null);
        }
        else
        {
            query = "SELECT * FROM "+DBHelper.TABLE_GURU+" WHERE "+DBHelper.NAMA_GURU+" like '%"+input+"%'";
            row = database.rawQuery(query, null);
        }
        if (row!=null)
        {
            row.moveToFirst();
        }
        return row;
    }

    public Cursor fetchFilterIndustri(String input)
    {
        Cursor row = null;
        String query = "SELECT * FROM "+DBHelper.TABLE_INDUSTRI;
        if (input == null || input.length()==0)
        {
            row = database.rawQuery(query, null);
        }
        else
        {
            query = "SELECT * FROM "+DBHelper.TABLE_INDUSTRI+" WHERE "+DBHelper.NAMA_INDUSTRI+" like '%"+input+"%'";
            row = database.rawQuery(query, null);
        }
        if (row!=null)
        {
            row.moveToFirst();
        }
        return row;
    }

    public Cursor fetchFilterSiswa(String input)
    {
        Cursor row = null;
        String query = "SELECT * FROM "+DBHelper.TABLE_SISWA;
        if (input == null || input.length()==0)
        {
            row = database.rawQuery(query, null);
        }
        else
        {
            query = "SELECT * FROM "+DBHelper.TABLE_SISWA+" WHERE "+DBHelper.NAMA_SISWA+" like '%"+input+"%'";
            row = database.rawQuery(query, null);
        }
        if (row!=null)
        {
            row.moveToFirst();
        }
        return row;
    }

}
