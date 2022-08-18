package com.joseph.nontonbareng.config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbLoginUser";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_LOGIN = "loginuser";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "pass";

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE "
            +TABLE_LOGIN+ " ( " +KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +KEY_NAME+ " TEXT, "
            +KEY_USERNAME+ " TEXT, "
            +KEY_PASS+ " TEXT );";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_LOGIN + "'");
        onCreate(db);
    }

    public long regisUser(String name, String username, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_USERNAME, username);
        values.put(KEY_PASS, pass);
        long insert = db.insert(TABLE_LOGIN, null, values);

        return insert;
    }

    public boolean checkUser(String username, String password){
        String[] columns = {KEY_ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = KEY_USERNAME + "=?" + " and " + KEY_PASS + "=?";
        String[] selectionArgs = {username,password};
        Cursor cursor = db.query(TABLE_LOGIN, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;
    }
}
