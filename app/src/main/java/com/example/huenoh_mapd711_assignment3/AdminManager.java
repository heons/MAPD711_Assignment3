package com.example.huenoh_mapd711_assignment3;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AdminManager extends OnlinePurchasingDBManager {

    // table name and table creator string (SQL statement to create the table)
    // should be set from within main activity
    public static final String TABLE_NAME = "Admin";
    public static final String TABLE_CREATE_STRING = "CREATE TABLE "+ TABLE_NAME
            + " (employeeId integer primary key, userName text, password text" +
            ", firstName text, lastName text);";

    public AdminManager(Context context) {
        super(context);
        super.dbInitialize(TABLE_NAME, TABLE_CREATE_STRING);
    }

    // This method returns a student object which holds the table row with the given id
    // The following argument should be passed:
    // id - an Object which holds the primary key value
    // fieldName - the  name of the primary key field
    public Admin getAdminById(Object id, String fieldName) throws  Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "select * from " + TABLE_NAME + " where "+ fieldName + "='"+String.valueOf(id)+"'", null );
        Admin admin = new Admin(); //create a new Student object
        if (cursor.moveToFirst()) { //if row exists
            cursor.moveToFirst(); //move to first row
            //initialize the instance variables of admin object
            admin.setEmployeeId(cursor.getInt(0));
            admin.setUserName(cursor.getString(1));
            admin.setPassword(cursor.getString(2));
            admin.setFirstName(cursor.getString(3));
            admin.setLastName(cursor.getString(4));
            cursor.close();

        } else {
            admin = null;
        }

        db.close();
        return admin;
    }

    // TODO : Override addRow() adding checking Unique UserName
}
