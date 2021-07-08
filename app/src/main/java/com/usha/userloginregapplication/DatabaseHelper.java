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
            + COLUMN_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_FNAME + " TEXT,"
            + COLUMN_LNAME + " TEXT,"
            + COLUMN_ADDRESS + " TEXT,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_PASS + " TEXT,"
            + COLUMN_PROFILE + " BLOB,"
            + COLUMN_DOB + " TEXT,"
            + COLUMN_QUE + " TEXT,"
            + COLUMN_ANS + " TEXT" + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public boolean addUser(UserModel c) {
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
        if (rowInserted != -1) {
            return true;
        } else {
            return false;
        }

    }


    public UserModel getUser() {
        try {
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
            user.setProfilePic(cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE)));
            user.setDob(cursor.getString(cursor.getColumnIndex(COLUMN_DOB)));
            user.setQuestions(cursor.getString(cursor.getColumnIndex(COLUMN_QUE)));
            user.setSec_ans(cursor.getString(cursor.getColumnIndex(COLUMN_ANS)));

            Log.d("LoginActivity", "User = " + user.getFirst_name() + " " + user.getLast_name());
            return userModel;


        } catch (Exception e) {

        }
        return null;

    }

    private static UserModel getUserDetails(Cursor cursor) {


        return new UserModel(
                cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_FNAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_LNAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)),
                cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PASS)),
                cursor.getString(cursor.getColumnIndex(COLUMN_PROFILE)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DOB)),
                cursor.getString(cursor.getColumnIndex(COLUMN_QUE)),

                cursor.getString(cursor.getColumnIndex(COLUMN_ANS)));
    }

    public ArrayList<UserModel> getAllUsers() {
        ArrayList<UserModel> userModels = new ArrayList<UserModel>();
        String selectQuery = "SELECT DISTINCT * FROM " + TABLE_NAME;
        try {

            Cursor c = this.getReadableDatabase()
                    .rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0) {
                while (c.moveToNext()) {
                    userModels.add(getUserDetails(c));
                }
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userModels;
    }

    public void updateUser(UserModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, model.getId());
        values.put(COLUMN_FNAME, model.getFirst_name());
        values.put(COLUMN_LNAME, model.getLast_name());
        values.put(COLUMN_ADDRESS, model.getAddress());
        values.put(COLUMN_USERNAME, model.getUsername());
        values.put(COLUMN_PASS, model.getPassword());
        values.put(COLUMN_PROFILE, model.getProfilePic());
        values.put(COLUMN_DOB, model.getDob());
        values.put(COLUMN_QUE, model.getQuestions());
        values.put(COLUMN_ANS, model.getSec_ans());

        db.update(TABLE_NAME, values, "username=?", new String[]{model.getUsername()});

        db.close();
    }

    public boolean ifExists(UserModel model) {
        Cursor cursor = null;
        String checkQuery = "select DISTINCT user from userdatabase where user = '" + TABLE_NAME + "'";
//        String checkQuery = "SELECT " + COLUMN_USERNAME + " FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + "= '"+model.getUsername() + "'";
        cursor = db.rawQuery(checkQuery, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public boolean isTableExists(String tableName, boolean openDb) {
        if (openDb) {
            if (this.db == null || !this.db.isOpen()) {
                this.db = getReadableDatabase();
            }

            if (!this.db.isReadOnly()) {
                this.db.close();
                this.db = getReadableDatabase();
            }
        }

        Cursor cursor = this.getReadableDatabase().
                rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                return true;
            }
        }
        return false;

    }
}
