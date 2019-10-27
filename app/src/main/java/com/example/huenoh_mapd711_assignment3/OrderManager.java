package com.example.huenoh_mapd711_assignment3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class OrderManager extends OnlinePurchasingDBManager {

    // TAG for the log
    private static final String TAG = "OrderManager";

    // table name and table creator string (SQL statement to create the table)
    // should be set from within main activity
    public static final String TABLE_NAME = "Orders";
    public static final String TABLE_CREATE_STRING = "CREATE TABLE "+ TABLE_NAME
            + " (orderId integer primary key, customerId integer, productId integer" +
            ", employeeId integer, quantity integer, orderDate text, status text);";

    /**
     * Initializer
     * @param context Context to work on
     */
    public OrderManager(Context context) {
        super(context);
        super.dbInitialize(TABLE_NAME, TABLE_CREATE_STRING);
    }

    /**
     * This method returns a order object which holds the table row with the given id
     * @param id           Order's ID or a value of the FieldName
     * @param strFieldName FieldName of a order record
     * @return             Order object or a null
     * @throws Exception
     */
    public Order getOrderById(Object id, String strFieldName) throws  Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "select * from " + TABLE_NAME + " where "+ strFieldName + "='"+String.valueOf(id)+"'", null );
        Order order = new Order(); //create a new Student object

        if (cursor.moveToFirst()) { //if row exists
            cursor.moveToFirst(); //move to first row
            //initialize the instance variables of order object
            order.setOrderId(cursor.getInt(0));
            order.setCustomerId(cursor.getInt(1));
            order.setProductId(cursor.getInt(2));
            order.setEmployeeId(cursor.getInt(3));
            order.setQuantity(cursor.getInt(4));
            order.setOrderDate(cursor.getString(5));
            order.setStatus(cursor.getString(6));
            cursor.close();

        } else {
            order = null;
        }

        db.close();
        return order;
    }

    /**
     * This method returns orders by fieldName and it's value
     * It may can be used in getOrdersByCustomerId, getOrdersByStatus, getOrdersEditable
     * @param value     
     * @param fieldName
     * @return
     * @throws Exception
     */
    public Order[] getOrdersByValue(Object value, String fieldName) throws  Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "select * from " + TABLE_NAME + " where "+ fieldName + "='"+String.valueOf(value)+"'", null );

        // Get number of rows
        final int cnt = cursor.getCount();

        // Get all products
        Order[] orders = new Order[cnt];
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            for (int i = 0; i < cnt; ++i) {
                Order order = new Order(
                        cursor.getInt(0)
                        , cursor.getInt(1)
                        , cursor.getInt(2)
                        , cursor.getInt(3)
                        , cursor.getInt(4)
                        , cursor.getString(5)
                        , cursor.getString(6));
                orders[i] = order;
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return orders;
    }

    /**
     * This method returns all the orders in the DB by a Customer ID
     * @param customerId Customer ID
     * @return Orders as an array
     */
    public Order[] getOrdersByCustomerId(int customerId) {
        try {
            return getOrdersByValue(customerId, "customerId");
        } catch (Exception exception) {
            Log.i(this.TAG + "Error: ", exception.getMessage());
        }
        return null;
    }

    /**
     * This method returns all the orders in the DB
     * @return Orders as an array
     * @throws Exception
     */
    public Order[] getAllOrders() throws  Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "select * from " + TABLE_NAME, null );

        // Get number of rows
        final int cnt = cursor.getCount();

        // Get all products
        Order[] orders = new Order[cnt];
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            for (int i = 0; i < cnt; ++i) {
                Order order = new Order(
                        cursor.getInt(0)
                        , cursor.getInt(1)
                        , cursor.getInt(2)
                        , cursor.getInt(3)
                        , cursor.getInt(4)
                        , cursor.getString(5)
                        , cursor.getString(6));
                orders[i] = order;
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return orders;
    }

}
