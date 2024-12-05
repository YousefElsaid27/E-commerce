package com.example.e_comerce.DatabaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_comerce.JavaClasses.Admin;
import com.example.e_comerce.JavaClasses.User;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_comerce.JavaClasses.Admin;
import com.example.e_comerce.JavaClasses.User;

public class RememberedListAccess {

    private RememberMeDataBase dbHelper;
    List<RememberedUser> rememberedUsers;

    public RememberedListAccess(Context context) {
        dbHelper = new RememberMeDataBase(context);
        rememberedUsers=new ArrayList<>();
    // Use AdminDatabase as helper class

    }


    public List<RememberedUser> GetRememberedUsers() {
        rememberedUsers.clear(); // Clear any existing users


        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Query to select all remembered users
        Cursor cursor = db.query(
                RememberMeDataBase.TABLE_REMEMBERME,
                new String[]{RememberMeDataBase.COLUMN_USERNAME, RememberMeDataBase.COLUMN_PASSWORD},
                null,
                null,
                null,
                null,
                null
        );

        // Check if there are any users in the database
        if (cursor.moveToFirst()) {
            do {
                // Get username and password from cursor
                String username = cursor.getString(cursor.getColumnIndexOrThrow(RememberMeDataBase.COLUMN_USERNAME));
                String password = cursor.getString(cursor.getColumnIndexOrThrow(RememberMeDataBase.COLUMN_PASSWORD));

                // Add user to the list
                rememberedUsers.add(new RememberedUser(username, password));
            } while (cursor.moveToNext());
        } else {
            // If no users found, add default message
            rememberedUsers.add(new RememberedUser("No Remembered users yet", ""));
        }

        // Close cursor and database
        cursor.close();
        db.close();

        return rememberedUsers;
    }


    public boolean AddRememberedUser(User user) {   //function to push user to database
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RememberMeDataBase.COLUMN_USERNAME, user.UserName);
        values.put(RememberMeDataBase.COLUMN_PASSWORD, user.Password);
        long result = db.insert(RememberMeDataBase.TABLE_REMEMBERME, null, values);
        db.close();

        return result !=-1;

    }
    /*
    public void DeleteAllRememberedUsers() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(RememberMeDataBase.TABLE_REMEMBERME, null, null); // Deletes all rows
        db.close();
    }
*/
}