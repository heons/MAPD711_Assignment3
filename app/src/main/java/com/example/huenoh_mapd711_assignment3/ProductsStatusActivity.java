package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ProductsStatusActivity extends AppCompatActivity {

    Spinner spinner;
    String[] arraySpinner = new String[] {
            "In-Process","Delivery"
    };
    // Product information from previous activity
    Order m_order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_status);

        // Get views
        spinner = findViewById(R.id.statusSpinner);

        // Get product from previous activity.
        Intent intent = getIntent();
        m_order = (Order) intent.getSerializableExtra("classOrder");

        // Set spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        if ( m_order.getStatus().equals(getString(R.string.status_inProgress)) ) {
            spinner.setSelection(0);
        } else {
            spinner.setSelection(1);
        }

    }


    public void onClickBtnUpdate(View view){

        // Update the order
        OrderManager orderManager = new OrderManager(this);
        // Initialize ContentValues for the order
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", spinner.getSelectedItem().toString());

        try {
            orderManager.editRow(m_order.getOrderId(), "orderId", contentValues, OrderManager.TABLE_NAME);
        } catch (Exception exception) {
            Log.i("Error: ", exception.getMessage());
        }

        // Go back to previous activity.
        finish();
    }


}
