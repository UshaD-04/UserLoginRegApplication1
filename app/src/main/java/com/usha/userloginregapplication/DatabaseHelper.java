package com.usha.userloginregapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userdatabase";
    private static final String TABLE_NAME = "user";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FNAME = "fname";
    private static final String COLUMN_LNAME = "lname";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_PROFILE = "profilepic";
    private static final String COLUMN_DOB = "dob";
    private static final String COLUMN_QUE = "que";
    private static final String COLUMN_ANS = "ans";
    SQLiteDatabase db;


    String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_FNAME + " TEXT,"
            + COLUMN_LNAME + " TEXT,"
            + COLUMN_ADDRESS + " TEXT,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_PASS + " TEXT,"
            + COLUMN_PROFILE + " BLOB,"
            + COLUMN_DOB + " TEXT,"
            + COLUMN_QUE + " TEXT,"
            + COLUMN_ANS + " TEXT" + ")";


    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }
    public boolean addUser(UserModel c)
    {
        boolean isSaved = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, c.getId());
        values.put(COLUMN_FNAME, c.getFirst_name());
        values.put(COLUMN_LNAME, c.getLast_name());
        values.put(COLUMN_ADDRESS, c.getAddress());
        values.put(COLUMN_USERNAME, c.getUsername());
        values.put(COLUMN_PASS, c.getPassword());
        values.put(COLUMN_PROFILE, c.getProfilePic());
        values.put(COLUMN_DOB, c.getDob());
        values.put(COLUMN_QUE, c.getQuestions());
        values.put(COLUMN_ANS, c.getSec_ans());

        long rowInserted = db.insert(TABLE_NAME, null, values);
        db.close();
        if(rowInserted != -1) {
            return true;
        }
        else {
            return false;
        }

    }


    public UserModel getUser() {
        UserModel userModel = new UserModel();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        UserModel user = new UserModel();
        user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        user.setFirst_name(cursor.getString(cursor.getColumnIndex(COLUMN_FNAME)));
        user.setLast_name(cursor.getString(cursor.getColumnIndex(COLUMN_LNAME)));
        user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
        user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASS)));
        user.setProfilePic(cursor.getBlob(cursor.getColumnIndex(COLUMN_PROFILE)));
        user.setDob(cursor.getString(cursor.getColumnIndex(COLUMN_DOB)));
        user.setQuestions(cursor.getString(cursor.getColumnIndex(COLUMN_QUE)));
        user.setSec_ans(cursor.getString(cursor.getColumnIndex(COLUMN_ANS)));

        Log.d("LoginActivity","User = "+user.getFirst_name()+" "+user.getLast_name());

        return userModel;
    }



}
