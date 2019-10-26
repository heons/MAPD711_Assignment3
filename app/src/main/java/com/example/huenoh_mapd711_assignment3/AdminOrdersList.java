package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AdminOrdersList extends AppCompatActivity {

    /* Member variables */
    private ListView m_listViewOrders; // List view for orders

    private Order[]  m_orders;         // Array for orders
    private String[] m_strOrdersList;  // Array for orders' display strings


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_orders_list);

        // Get order list view
        m_listViewOrders = findViewById(R.id.ordersList);

        // Update the order list view
        updateOrderList();

        // Set the listener for the order list view.
        m_listViewOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Move to ProductsStatusActivity activity
                Intent intent = new Intent(AdminOrdersList. this,ProductsStatusActivity.class);
                intent.putExtra("classOrder", m_orders[i]);
                startActivity(intent);
            }
        });
    }


    /**
     * onResume operation.
     */
    @Override
    protected void onResume() {
        super.onResume();

        // Update the order list view
        updateOrderList();
    }

    /**
     * Update the order list view by loading data from the DB
     */
    private void updateOrderList() {
        // Order Manager
        OrderManager orderManager = new OrderManager(this);

        // Get all the products from the database.
        try {
            m_orders = orderManager.getAllOrders();
        }
        catch (Exception exception)
        {
            Log.i("Error: ",exception.getMessage());
        }

        // Update strings for the list adapter
        m_strOrdersList = new String[m_orders.length];
        for (int i = 0; i < m_orders.length; ++i) {
            m_strOrdersList[i] = m_orders[i].getOrderDate() + "(Status : " + m_orders[i].getStatus() + ")";
        }

        // Create array adapter for the list view
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, m_strOrdersList);

        // Get list view for the products and set the adapter.
        m_listViewOrders.setAdapter(adapter);
    }
}
