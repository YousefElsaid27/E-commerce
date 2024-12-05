package com.example.e_comerce.DatabaseAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.e_comerce.JavaClasses.Customer;
import com.example.e_comerce.JavaClasses.date;
import com.example.e_comerce.JavaClasses.User;

public class CustomerDbAccess extends UserDbAccess {
    CustomerDatabase dbHelper;
    public CustomerDbAccess(Context context) {
        this.dbHelper = new CustomerDatabase(context);
    }

    // Register a new customer
    public boolean registerCustomer(String username, String email, String password, String birthdate) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CustomerDatabase.COLUMN_USERNAME, username);
        values.put(CustomerDatabase.COLUMN_EMAIL, email);
        values.put(CustomerDatabase.COLUMN_PASSWORD, password);
        values.put(CustomerDatabase.COLUMN_BIRTHDATE, birthdate);

        long result = db.insert(CustomerDatabase.TABLE_CUSTOMERS, null, values);
        db.close();

        // Return true if insertion was successful, false otherwise
        return result != -1;
    }
    // Check if user exists
    public User CheckUserExists(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + CustomerDatabase.TABLE_CUSTOMERS +
                " WHERE " + CustomerDatabase.COLUMN_USERNAME + " = ? AND " +
                CustomerDatabase.COLUMN_PASSWORD + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        Customer customer = null;

        if (cursor.moveToFirst()) {
            String email = cursor.getString(cursor.getColumnIndexOrThrow(CustomerDatabase.COLUMN_EMAIL));
            String birthdateStr = cursor.getString(cursor.getColumnIndexOrThrow(CustomerDatabase.COLUMN_BIRTHDATE));
            // Parse birthdate string into day, month, and year
            String[] dateParts = birthdateStr.split("/");
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            // Create the date object
            date birthdate = new date(day, month, year);

            customer = new Customer(username, email, password, birthdate);
        }

        cursor.close();
        db.close();

        return customer;  // Returns null if user does not exist
    }



}
