package com.example.jurnalprakerin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    public static final String TABLE_GURU = "tbl_guru", _ID_GURU = "_id", NAMA_GURU = "nama",
            NIP = "nip", EMAIL = "email", NO_TLP_GURU = "no_tlp", ALAMAT_GURU = "alamat";
    public static final String TABLE_SISWA = "tbl_siswa", _ID_SISWA = "_id", NAMA_SISWA = "nama",
            NIS_SISWA = "nis", TGL_LAHIR = "tgl_lahir", JK = "jk", NO_HP = "no_hp",
            NOTLP_ORTU = "notlp_ortu", ALAMAT_SISWA = "alamat", NII_SISWA = "nii";
    public static final String TABLE_AKTIVITASSISWA = "tbl_aktivitassiswa", _ID_AKTIVITAS = "_id",
            NIS_AKSIS = "nis", NII = "nii", KEGIATAN = "kegiatan", DES_KEG = "des_keg",
            TEMPAT = "tempat", TANGGAL = "tanggal", WAKTU = "waktu";
    public static final String TABLE_DAFTARNILAISISWA = "tbl_daftarnilaisiswa", _ID_DAFNIL = "_id",
            NAMA_SISWADAFNIL = "nama", NIS = "nis", NII_DAFNIL = "nii", PERIODE_PRAKERIN = "periode_prakerin",
            DIVISI_PRAKERIN = "divisi_prakerin", NILAI_PRAKERIN = "nilai_prakerin", REKOMENDASI = "rekomendasi";
    public static final String TABLE_INDUSTRI = "tbl_industri", _ID_INDUSTRI = "_id",
            NAMA_INDUSTRI = "nama", NII_INDUSTRI = "nii", NAMA_PIMPINAN = "nama_pimpinan",
            ALAMAT_INDUSTRI = "alamat", NOTLP_INDUSTRI = "no_tlp", NOFAX_INDUSTRI = "no_fax",
            NAMA_PEMBIMBING = "nama_pembimbing";

    static final String DB_NAME = "JURNALPRAKERIN.DB";
    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_GURU = "CREATE TABLE " + TABLE_GURU + "(" + _ID_GURU
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAMA_GURU + " TEXT NOT NULL, "
            + NIP + " TEXT NOT NULL, "+ EMAIL + " TEXT NOT NULL, "+ NO_TLP_GURU + " TEXT NOT NULL, "
            + ALAMAT_GURU + " TEXT NOT NULL);";
    private static final String CREATE_TABLE_SISWA = "CREATE TABLE " + TABLE_SISWA
            + "(" + _ID_SISWA + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAMA_SISWA
            + " TEXT NOT NULL, " + NIS_SISWA + " TEXT NOT NULL, "+ TGL_LAHIR + " TEXT, "
            + JK + " TEXT, " + NO_HP + " TEXT, "+ NOTLP_ORTU + " TEXT, "
            + ALAMAT_SISWA + " TEXT, " + NII_SISWA + " TEXT NOT NULL);";
    private static final String CREATE_TABLE_AKTIVITASSISWA = "CREATE TABLE " + TABLE_AKTIVITASSISWA
            + "(" + _ID_AKTIVITAS + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NIS_AKSIS
            + " TEXT NOT NULL, " + NII + " TEXT NOT NULL, "+ KEGIATAN + " TEXT NOT NULL, "
            + DES_KEG + " TEXT NOT NULL, " + TEMPAT + " TEXT NOT NULL, "+ TANGGAL
            + " TEXT NOT NULL, " + WAKTU + " TEXT NOT NULL);";
    private static final String CREATE_TABLE_DAFTARNILAISISWA = "CREATE TABLE "
            + TABLE_DAFTARNILAISISWA + "(" + _ID_DAFNIL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAMA_SISWADAFNIL + " TEXT NOT NULL, " + NIS + " TEXT NOT NULL, " + NII_DAFNIL
            + " TEXT NOT NULL, "+ PERIODE_PRAKERIN + " TEXT NOT NULL, "+ DIVISI_PRAKERIN
            + " TEXT NOT NULL, " + NILAI_PRAKERIN + " TEXT NOT NULL, " + REKOMENDASI
            + " TEXT NOT NULL);";
    private static final String CREATE_TABLE_INDUSTRI = "CREATE TABLE " + TABLE_INDUSTRI
            + "(" + _ID_INDUSTRI + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAMA_INDUSTRI
            + " TEXT NOT NULL, " + NII_INDUSTRI + " TEXT NOT NULL, "+ NAMA_PIMPINAN
            + " TEXT, "+ ALAMAT_INDUSTRI + " TEXT, " + NOTLP_INDUSTRI
            + " TEXT, "+ NOFAX_INDUSTRI + " TEXT, " + NAMA_PEMBIMBING
            + " TEXT);";

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_GURU);
        db.execSQL(CREATE_TABLE_SISWA);
        db.execSQL(CREATE_TABLE_AKTIVITASSISWA);
        db.execSQL(CREATE_TABLE_DAFTARNILAISISWA);
        db.execSQL(CREATE_TABLE_INDUSTRI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GURU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SISWA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AKTIVITASSISWA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAFTARNILAISISWA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INDUSTRI);
        onCreate(db);
    }
}
