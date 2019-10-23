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

    // Table names and queries to create them
    private static Set<String> tableNames = new HashSet<String>();
    private static Set<String> tableCreatorStrings = new HashSet<String>();


    /** Initializer
     * @param context Context to work on
     */
    public OnlinePurchasingDBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method is called when the database is created for the first time.
     * This is where the creation of tables and the initial population
     * of the tables should happen.
     * @param db SQLite database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the tables
        for(String tableCreatorString : tableCreatorStrings){
            db.execSQL(tableCreatorString);
        }
    }

    /**
     * This method is called when the database needs to be upgraded.
     *  The implementation should use this method to drop tables,
     *  add tables, or do anything else it needs to upgrade
     *  to the new schema version.
     * @param db         SQLite database
     * @param oldVersion Old version
     * @param newVersion New version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)   {
        if(newVersion > oldVersion) {
            //drop table if existed
            for(String tableName : tableNames) {
                db.execSQL("DROP TABLE IF EXISTS " + tableName);
            }
            //recreate the table
            onCreate(db);
        }
    }

    /**
     * This method initialize parameters for the DB by adding tables
     * @param tableName          TableName
     * @param tableCreatorString Query to create a table with TableName
     */
    public void dbInitialize(String tableName, String tableCreatorString)
    {
        this.tableNames.add(tableName);
        this.tableCreatorStrings.add(tableCreatorString);
    }

    /**
     * This method add a record
     * @param values       Values to add. Key-Value pairs.
     * @param strTableName TableName of the record
     * @return             True if a record is added
     * @throws Exception
     */
    public boolean addRow(ContentValues values, String strTableName) throws Exception {
        long nr = -1;
        // Insert the row
        if(tableNames.contains(strTableName)) {
            SQLiteDatabase db = this.getWritableDatabase();
            nr = db.insert(strTableName, null, values);
            db.close(); //close database connection
        }
        return nr > -1;
    }

    /**
     * This method checks the existance of the record by it's id, fieldName, TableName
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

    /**
     * This method edit a record and return the number of rows affected
     * @param id           Object ID or a value of the FieldName
     * @param strFieldName FieldName of a record
     * @param values       Values to edit. Key-Value pairs.
     * @param strTableName TableName of a record
     * @return             The number of rows affected
     * @throws Exception
     */
    public long editRow (Object id, String strFieldName, ContentValues values, String strTableName) throws Exception {
        long nr = 0;
        if(tableNames.contains(strTableName)) {
            SQLiteDatabase db = this.getWritableDatabase();
            nr = db.update(strTableName, values, strFieldName + " = ?", new String[]{String.valueOf(id)});
            db.close(); //close database connection
        }
        return nr;
    }

    /**
     * This method deletes a record and return the number of rows affected
     * @param id           Object ID or a value of the FieldName
     * @param strFieldName FieldName of a record
     * @param strTableName TableName of a record
     * @return             The number of rows affected
     * @throws Exception
     */
    public long deleteRow(Object id, String strFieldName, String strTableName) throws Exception {
        long rc = 0;

        if(tableNames.contains(strTableName)) {
            SQLiteDatabase db = this.getWritableDatabase();
            rc = db.delete(strTableName, strFieldName + " = ?", new String[] { String.valueOf(id) });
            db.close(); //close database connection
        }

        return rc;
    }
}
