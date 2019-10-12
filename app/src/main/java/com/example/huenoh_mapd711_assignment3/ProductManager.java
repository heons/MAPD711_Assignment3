package com.example.huenoh_mapd711_assignment3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class ProductManager extends OnlinePurchasingDBManager {
    // table name and table creator string (SQL statement to create the table)
    // should be set from within main activity
    public static final String TABLE_NAME = "Product";
    public static final String TABLE_CREATE_STRING = "CREATE TABLE "+ TABLE_NAME
            + " (productId integer primary key, productName text, price double" +
            ", quantity integer, category text);";

    public ProductManager(Context context) {
        super(context);
        super.dbInitialize(TABLE_NAME, TABLE_CREATE_STRING);
    }

    // This method returns a student object which holds the table row with the given id
    // The following argument should be passed:
    // id - an Object which holds the primary key value
    // fieldName - the  name of the primary key field
    public Product getProductById(Object id, String fieldName) throws  Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "select * from " + TABLE_NAME + " where "+ fieldName + "='"+String.valueOf(id)+"'", null );
        Product product = new Product(); //create a new Student object
        if (cursor.moveToFirst()) { //if row exists
            cursor.moveToFirst(); //move to first row
            //initialize the instance variables of product object
            product.setProductId(cursor.getInt(0));
            product.setProductName(cursor.getString(1));
            product.setPrice(cursor.getDouble(2));
            product.setQuantity(cursor.getInt(3));
            product.setCategory(cursor.getString(4));
            cursor.close();

        } else {
            product = null;
        }

        db.close();
        return product;
    }
}
