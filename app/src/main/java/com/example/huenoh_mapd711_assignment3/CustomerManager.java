package com.example.huenoh_mapd711_assignment3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class CustomerManager extends OnlinePurchasingDBManager{

    // table name and table creator string (SQL statement to create the table)
    // should be set from within main activity
    public static final String TABLE_NAME = "Customer";
    public static final String TABLE_CREATE_STRING = "CREATE TABLE "+ TABLE_NAME
            + " (customerId integer primary key, userName text, password text" +
            ", firstName text, lastName text, address text, city text, postalCode text);";

    public CustomerManager(Context context) {
        super(context);
        super.dbInitialize(TABLE_NAME, TABLE_CREATE_STRING);
    }

    // This method returns a student object which holds the table row with the given id
    // The following argument should be passed:
    // id - an Object which holds the primary key value
    // fieldName - the  name of the primary key field
    public Customer getCustomerById(Object id, String fieldName) throws  Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "select * from " + TABLE_NAME + " where "+ fieldName + "='"+String.valueOf(id)+"'", null );
        Customer customer = new Customer(); //create a new Student object
        if (cursor.moveToFirst()) { //if row exists
            cursor.moveToFirst(); //move to first row
            //initialize the instance variables of customer object
            customer.setCustomerId(cursor.getInt(0));
            customer.setUserName(cursor.getString(1));
            customer.setPassword(cursor.getString(2));
            customer.setFirstName(cursor.getString(3));
            customer.setLastName(cursor.getString(4));
            customer.setAddress(cursor.getString(5));
            customer.setCity(cursor.getString(6));
            customer.setPostalCode(cursor.getString(7));
            cursor.close();

        } else {
            customer = null;
        }

        db.close();
        return customer;
    }
}
