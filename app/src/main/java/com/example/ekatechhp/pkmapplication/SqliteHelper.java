package com.example.ekatechhp.pkmapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SqliteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "pkm_database";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USERS = "users";
    public static final String KEY_ID = "id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String SQL_TABLE_USERS = " CREATE TABLE "
            + TABLE_USERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USERNAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT" + ")";
    public static final String TABLE_SETUPUSERS = "setupUsers";
    public static final String KEY_NICKNAME = "nickname";
    public static final String KEY_ORGANIZATION = "organization";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_DESIGNATION = "designation";
    public static final String KEY_JOBLEVEL = "joblevel";
    public static final String KEY_SHAREKBWITH = "sharekbwith";
    public static final String SQL_TABLE_SETUP_USERS = " CREATE TABLE "
            + TABLE_SETUPUSERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_NICKNAME + " TEXT, "
            + KEY_ORGANIZATION + " TEXT, "
            + KEY_MOBILE + " TEXT, "
            + KEY_DESIGNATION + " TEXT, "
            + KEY_JOBLEVEL + " TEXT, "
            + KEY_SHAREKBWITH + " TEXT " + ")";
    public static final String TABLE_BUILDKB = "buildkb";
    public static final String KEY_NODENAME = "nodename";
    public static final String KEY_KNOWLEDGE = "knowledge";
    public static final String SQL_TABLE_BUILDKB = " CREATE TABLE "
            + TABLE_BUILDKB + "("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_EMAIL + " TEXT, "
            + KEY_NODENAME + " TEXT, "
            + KEY_KNOWLEDGE + " TEXT" + ")";
    public static final String TABLE_SHARETO = "shareto";
    public static final String KEY_SHAREWITH = "sharewith";
    public static final String SQL_TABLE_SHARETO = " CREATE TABLE "
            + TABLE_SHARETO + "("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_SHAREWITH + " TEXT" + ")";
    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_SETUP_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_BUILDKB);
        sqLiteDatabase.execSQL(SQL_TABLE_SHARETO);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_SETUPUSERS);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_BUILDKB);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_SHARETO);
        onCreate(sqLiteDatabase);
    }
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.userName);
        values.put(KEY_EMAIL, user.email);
        values.put(KEY_PASSWORD, user.password);
        long todo_id = db.insert(TABLE_USERS, null, values);
        db.close();
    }
    public void addSetupUser(SetupUser user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NICKNAME, user.nickName);
        values.put(KEY_ORGANIZATION, user.organization);
        values.put(KEY_MOBILE, user.mobile);
        values.put(KEY_DESIGNATION, user.designation);
        values.put(KEY_JOBLEVEL, user.joblevel);
        values.put(KEY_SHAREKBWITH, user.sharewith);
        long todo_id = db.insert(TABLE_SETUPUSERS, null, values);
        db.close();
    }
    public void updatePassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PASSWORD, password);
        db.update(TABLE_USERS, values, KEY_EMAIL+" =?",new String[] {email});
        db.close();
    }
    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{KEY_ID, KEY_USERNAME, KEY_EMAIL, KEY_PASSWORD},
                KEY_EMAIL + "=?",
                new String[]{user.email},
                null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            User user1 = new User(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }
        return null;
    }
    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{KEY_ID, KEY_USERNAME, KEY_EMAIL, KEY_PASSWORD},
                KEY_EMAIL + "=?",
                new String[]{email},
                null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            return true;
        }
        return false;
    }
    public ArrayList<String> readEmails() {
        ArrayList<String> emailArrayList = new ArrayList<String>();
        String email = "";
        String selectQuery = "SELECT email FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if(c.moveToFirst()) {
            do {
                emailArrayList.add(c.getString(c.getColumnIndex(KEY_EMAIL)));
            } while(c.moveToNext());
        }
        return emailArrayList;
    }
}
