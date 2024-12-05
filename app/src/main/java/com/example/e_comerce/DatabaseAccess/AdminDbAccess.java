package com.example.e_comerce.DatabaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_comerce.JavaClasses.Admin;
import com.example.e_comerce.JavaClasses.User;

public class AdminDbAccess extends UserDbAccess {

    private AdminDatabase dbHelper;

    public AdminDbAccess(Context context) {
        dbHelper = new AdminDatabase(context);  // Use AdminDatabase as helper class
    }

    // Method to insert a new admin
    public boolean registerAdmin(String username, String email, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AdminDatabase.COLUMN_USERNAME, username);
        values.put(AdminDatabase.COLUMN_EMAIL, email);
        values.put(AdminDatabase.COLUMN_PASSWORD, password);

        long result = db.insert(AdminDatabase.TABLE_ADMINS, null, values);
        db.close();
        return result != -1; // Return true if the insert was successful
    }

    // Method to check if an admin exists by username and password
    public Admin CheckUserExists(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Query to check for admin using username and password
        String query = "SELECT * FROM " + AdminDatabase.TABLE_ADMINS +
                " WHERE " + AdminDatabase.COLUMN_USERNAME + " = ? AND " +
                AdminDatabase.COLUMN_PASSWORD + " = ?";

        // Execute the query with the provided username and password
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        Admin admin = null;

        // If an admin with the provided credentials is found, return the Admin object
        if (cursor.moveToFirst()) {
            String email = cursor.getString(cursor.getColumnIndexOrThrow(AdminDatabase.COLUMN_EMAIL));

            // Create Admin object with the retrieved data
            admin = new Admin(username, password, email);
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return admin;  // Return null if no admin found
    }



}
