package com.example.huenoh_mapd711_assignment3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class CustomerManager extends OnlinePurchasingDBManager{

    // TAG for the log
    private static final String TAG = "CustomerManager";

    // table name and table creator string (SQL statement to create the table)
    // should be set from within main activity
    public static final String TABLE_NAME = "Customer";
    public static final String TABLE_CREATE_STRING = "CREATE TABLE "+ TABLE_NAME
            + " (customerId integer primary key, userName text, password text" +
            ", firstName text, lastName text, address text, city text, postalCode text);";

    /**
     * Initializer
     * @param context Context to work on
     */
    public CustomerManager(Context context) {
        super(context);
        super.dbInitialize(TABLE_NAME, TABLE_CREATE_STRING);
    }

    /**
     * This method returns a customer object which holds the table row with the given id
     * @param id           Customer's ID or a value of the FieldName
     * @param strFieldName FieldName of a customer record
     * @return             Customer object or a null
     * @throws Exception
     */
    public Customer getCustomerById(Object id, String strFieldName) throws  Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "select * from " + TABLE_NAME + " where "+ strFieldName + "='"+String.valueOf(id)+"'", null );
        Customer customer = new Customer(); //create a new Customer object

        if (cursor.moveToFirst()) { //if a row exists
            cursor.moveToFirst(); //move to the first row
            //initialize the instance variables of a customer object
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

    /**
     * This method check the existance of a Customer record
     * @param id           Customer's ID or a value of the FieldName
     * @param strFieldName FieldName of a customer record to check
     * @return             True if the record exists, otherwise false
     */
    public boolean checkCustomerById(Object id, String strFieldName) {
        boolean rc = false;
        try {
            rc = super.checkItemById(id, strFieldName, this.TABLE_NAME);
            //rc = super.checkItemById(id, "tmp", this.TABLE_NAME); // This cause an exception
            //rc = super.checkItemById(id, strFieldName, "tmp");    // This cause an exception
        } catch (Exception exception) {
            Log.i(this.TAG + " Error: ", exception.getMessage());
            rc = false;
        }
        return rc;
    }

    /**
     * This method deletes all the records
     * @return True if it succeeded, False otherwise
     */
    public boolean deleteAll() {
        try {
            return super.deleteAllRows(this.TABLE_NAME);
        } catch (Exception exception) {
            Log.i(this.TAG + " Error: ", exception.getMessage());
            return false;
        }
    }
}
