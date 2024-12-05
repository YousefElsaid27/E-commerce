package com.example.e_comerce.DatabaseAccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;





public class RememberMeDataBase extends SQLiteOpenHelper {

    // Database version and name
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RememberMeDatabase.db";

    // Admin table details
    public static final String TABLE_REMEMBERME = "Rememberme";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";

    public static final String COLUMN_PASSWORD = "password";

    // Create table SQL statement for Admin
    private static final String CREATE_ADMINS_TABLE =
            "CREATE TABLE " + TABLE_REMEMBERME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_USERNAME + " TEXT UNIQUE NOT NULL, "
                    + COLUMN_PASSWORD + " TEXT NOT NULL)";

    public RememberMeDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL(CREATE_ADMINS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMEMBERME);

        // Recreate tables
        onCreate(db);
    }
}
