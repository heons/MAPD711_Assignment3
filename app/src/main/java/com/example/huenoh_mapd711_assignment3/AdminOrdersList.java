package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AdminOrdersList extends AppCompatActivity {


    ListView listView;

    OrderManager orderManager = new OrderManager(this);
    Order[] orders;
    String[] strOrdersList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_orders_list);


        // Get all the products from the database.
        try {
            this.orders = orderManager.getAllOrders();
        }
        catch (Exception exception)
        {
            Log.i("Error: ",exception.getMessage());
        }


        // Update strings for the list adapter
        strOrdersList = new String[orders.length];
        for (int i = 0; i < orders.length; ++i) {
            strOrdersList[i] = orders[i].getOrderDate() + "(Status : " + orders[i].getStatus() + ")";
        }

        // Create array adapter for the list view
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, strOrdersList);


        // Get list view for the products and set the adapter.
        listView = findViewById(R.id.ordersList);
        listView.setAdapter(adapter);

        // Set the listener.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(),listView.getItemAtPosition(i).toString(),
                        Toast.LENGTH_LONG).show();

                String x = adapter.getItem(i);
                System.out.println(x);

                Intent intent = new Intent(AdminOrdersList. this,ProductsStatusActivity.class);
                startActivity(intent);

            }
        });



    }
}
