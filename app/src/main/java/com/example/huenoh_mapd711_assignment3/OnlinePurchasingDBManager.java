package com.example.huenoh_mapd711_assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashSet;
import java.util.Set;

public class OnlinePurchasingDBManager extends SQLiteOpenHelper {
    //database name and version
    private static final String DATABASE_NAME = "OnlinePurchasingDB";
    private static final int DATABASE_VERSION = 1;

    private static Set<String> tableNames = new HashSet<String>();
    private static Set<String> tableCreatorStrings = new HashSet<String>();

    public OnlinePurchasingDBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created for the first time.
    // This is where the creation of tables and the initial population
    // of the tables should happen.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table
        for(String tableCreatorString : tableCreatorStrings){
            db.execSQL(tableCreatorString);
        }
    }

    // Called when the database needs to be upgraded.
    // The implementation should use this method to drop tables,
    // add tables, or do anything else it needs to upgrade
    // to the new schema version.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)   {
        if(newVersion > oldVersion) {
            //drop table if existed
            for(String tableName : tableNames){
                db.execSQL("DROP TABLE IF EXISTS " + tableName);
            }
            //recreate the table
            onCreate(db);
        }
    }

    public void dbInitialize(String tableName, String tableCreatorString)
    {
        this.tableNames.add(tableName);
        this.tableCreatorStrings.add(tableCreatorString);
    }


    // CRUD Operations
    // This method is called by the activity to add a row in the table
    // The following arguments should be passed:
    // values - a ContentValues object that holds row values
    public boolean addRow(ContentValues values, String strTableName) throws Exception {
        long nr = -1;
        // Insert the row
        if(tableNames.contains(strTableName))
        {
            SQLiteDatabase db = this.getWritableDatabase();
            nr = db.insert(strTableName, null, values);
            db.close(); //close database connection
        }
        return nr > -1;
    }

    /**
     * It check the existance of the record by it's id, fieldName, TableName
     * @param id           Object ID or a value of the FieldName
     * @param strFieldName FieldName of the record to check
     * @param strTableName TableName of the record to check
     * @return             True if the record exists, otherwise false
     * @throws Exception
     */
    public boolean checkItemById(Object id, String strFieldName, String strTableName) throws  Exception{
        boolean rc = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "select * from " + strTableName + " where "+ strFieldName + "='"+String.valueOf(id)+"'", null );

        if (cursor.moveToFirst()) { //if a row exists
            rc = true;
        } else {}

        cursor.close();
        db.close();
        return rc;
    }

    // id - an Object which holds the primary key value
    // fieldName - the  name of the primary key field
    // values - a ContentValues object that holds row values
    // Insert the row
    public boolean editRow (Object id, String fieldName, ContentValues values, String strTableName) throws Exception {
        long nr = -1;
        if(tableNames.contains(strTableName))
        {
            SQLiteDatabase db = this.getWritableDatabase();
            // TODO : check the existence of the data first.
            nr = db.update(strTableName, values, fieldName + " = ?", new String[]{String.valueOf(id)});
            db.close(); //close database connection
        }
        return nr > -1;
    }

    // delete a row
    public void deleteRow(Object id, String fieldName, String strTableName) throws Exception {
        if(tableNames.contains(strTableName))
        {
            SQLiteDatabase db = this.getWritableDatabase();
            // TODO : check the existence of the data first.
            db.delete(strTableName, fieldName + " = ?", new String[] { String.valueOf(id) });
            db.close(); //close database connection
        }
    }
}
