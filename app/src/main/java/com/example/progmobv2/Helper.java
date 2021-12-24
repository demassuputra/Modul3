package com.example.progmobv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

//membuat database
public class Helper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "FormSewaPancing";
    private static final String TABLE_NAME = "tbl_penyewa";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAMALENGKAP = "nama_lengkap";
    private static final String KEY_TGLSEWA = "tgl_sewa";
    private static final String KEY_UMUR = "umur";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_PENYEWAAN = "penyewaan";


    public Helper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTable = "CREATE TABLE " + TABLE_NAME +
                " (" + KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NAMALENGKAP + " TEXT, " +
                KEY_TGLSEWA + " TEXT, " +
                KEY_UMUR + " TEXT, " +
                KEY_GENDER + " TEXT, " +
                KEY_PENYEWAAN + " TEXT " + ")";
        sqLiteDatabase.execSQL(createUserTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;

        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    // fungsi insert data
    public void insert(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAMALENGKAP, user.getTxtNamaLengkap());
        values.put(KEY_TGLSEWA, user.getInputtglsewa());
        values.put(KEY_UMUR, user.getNilaiUmur());
        values.put(KEY_GENDER, user.getRadioButton());
        values.put(KEY_PENYEWAAN, user.getmResult());

        db.insert(TABLE_NAME, null, values);
    }

    //menampilkan data
    public List<User> selectUserData() {
        ArrayList<User> users = new ArrayList<User>();

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {KEY_ID, KEY_NAMALENGKAP, KEY_TGLSEWA, KEY_UMUR, KEY_GENDER, KEY_PENYEWAAN};

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String namaLengkap = cursor.getString(1);
            String tglLahir = cursor.getString(2);
            String umur = cursor.getString(3);
            String gender = cursor.getString(4);
            String service = cursor.getString(5);

            User user = new User();
            user.setId(id);
            user.setTxtNamaLengkap(namaLengkap);
            user.setInputtglsewa(tglLahir);
            user.setNilaiUmur(umur);
            user.setRadioButton(gender);
            user.setmResult(service);

            users.add(user);
        }

        return users;
    }
}


