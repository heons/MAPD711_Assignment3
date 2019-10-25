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
            ", employeeId integer, orderDate text, status text);";

    public OrderManager(Context context) {
        super(context);
        super.dbInitialize(TABLE_NAME, TABLE_CREATE_STRING);
    }

    // This method returns a student object which holds the table row with the given id
    // The following argument should be passed:
    // id - an Object which holds the primary key value
    // fieldName - the  name of the primary key field
    public Order getOrderById(Object id, String fieldName) throws  Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "select * from " + TABLE_NAME + " where "+ fieldName + "='"+String.valueOf(id)+"'", null );
        Order order = new Order(); //create a new Student object
        if (cursor.moveToFirst()) { //if row exists
            cursor.moveToFirst(); //move to first row
            //initialize the instance variables of order object
            order.setOrderId(cursor.getInt(0));
            order.setCustomerId(cursor.getInt(1));
            order.setProductId(cursor.getInt(2));
            order.setEmployeeId(cursor.getInt(3));
            order.setOrderDate(cursor.getString(4));
            order.setStatus(cursor.getString(5));
            cursor.close();

        } else {
            order = null;
        }

        db.close();
        return order;
    }


    // Get orders by values
    // It may include  getOrdersById, getOrdersByStatus, getOrdersEditable
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
                        , cursor.getString(4)
                        , cursor.getString(5));
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
                        , cursor.getString(4)
                        , cursor.getString(5));
                orders[i] = order;
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return orders;
    }


    // TODO : add placeAnOrder()

}
