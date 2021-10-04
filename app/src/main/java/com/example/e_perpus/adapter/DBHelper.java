package com.example.e_perpus.adapter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "db_perpus";
    public static final String tabel_name = "tabel_perpus";
    public static final String TABLE_USER = "tb_user";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_NAME = "name";
    public static final String row_id = "_id";
    public static final String row_nama = "Nama";
    public static final String row_judul = "Judul";
    public static final String row_pinjam = "TglPinjam";
    public static final String row_kembali = "TglKembali";
    public static final String row_status = "Status";

    private SQLiteDatabase db;


    public DBHelper(Context context) {
        super(context, database_name, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table " + TABLE_USER + " (" + COL_USERNAME + " TEXT PRIMARY KEY, " + COL_PASSWORD +
                " TEXT, " + COL_NAME + " TEXT)");
        db.execSQL( "CREATE TABLE " + tabel_name + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_nama + " TEXT," + row_judul + " TEXT," + row_pinjam + " TEXT," + row_kembali + " TEXT," + row_status + " TEXT) ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + tabel_name);
        onCreate(db);
    }
    public boolean Register(String username, String password, String name) throws SQLException {

        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("INSERT INTO " + TABLE_USER + "(" + COL_USERNAME + ", " + COL_PASSWORD + ", " + COL_NAME + ") VALUES (?,?,?)", new String[]{username, password, name});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }
    public boolean Login(String username, String password) throws SQLException {
        @SuppressLint("Recycle") Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COL_USERNAME + "=? AND " + COL_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            return mCursor.getCount() > 0;
        }
        return false;
    }


    //Get All SQLite Data
    public Cursor allData(){
        Cursor cur = db.rawQuery("SELECT * FROM " + tabel_name + " ORDER BY " + row_id + " DESC ", null);
        return cur;
    }

    //Get Data Dipinjam
    public Cursor dataPinjam(){
        Cursor cur = db.rawQuery("SELECT * FROM " + tabel_name + " WHERE " + row_status + "=" + "'Dipinjam'", null);
        return cur;
    }

    //Get Data Dikembalikan
    public Cursor dataDikembalikan(){
        Cursor cur = db.rawQuery("SELECT * FROM " + tabel_name + " WHERE " + row_status + "=" + "'Dikembalikan'", null);
        return cur;
    }


    //GET 1 DATA BY ID
    public Cursor oneData(long id){
        Cursor cur = db.rawQuery("SELECT * FROM " + tabel_name + " WHERE " + row_id + "=" + id, null);
        return cur;
    }

    //Insert Data
    public void insertData(ContentValues values){
        db.insert(tabel_name, null, values);
    }

    //Update Data
    public void updateData(ContentValues values, long id){
        db.update(tabel_name, values, row_id + "=" + id, null);
    }

    //Delete Data
    public void deleteData(long id){
        db.delete(tabel_name, row_id + "=" + id, null);
    }

}
