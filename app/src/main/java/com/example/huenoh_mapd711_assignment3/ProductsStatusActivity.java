package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ProductsStatusActivity extends AppCompatActivity {

    /* Member variables */
    // Array for status spinner
    private static final String[] m_arrSpinnerStatus = new String[] {
            "In-Process", "Delivery"
    };

    // Spinner for the status
    private Spinner m_spinnerStatus;

    // Product information from previous activity
    private Order m_order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_status);

        // Get views
        m_spinnerStatus = findViewById(R.id.statusSpinner);

        // Get product from previous activity.
        Intent intent = getIntent();
        m_order = (Order) intent.getSerializableExtra("classOrder");

        // Set status spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, m_arrSpinnerStatus);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_spinnerStatus.setAdapter(adapter);

        if ( m_order.getStatus().equals(getString(R.string.status_inProgress)) ) {
            m_spinnerStatus.setSelection(0);
        } else {
            m_spinnerStatus.setSelection(1);
        }

        //TODO : angard - display other order information as well
    }


    /**
     * This method is for the click action of the update button
     * @param view update button
     */
    public void onClickBtnUpdate(View view){

        // Initialize ContentValues for the order
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", m_spinnerStatus.getSelectedItem().toString());

        // Update the order
        OrderManager orderManager = new OrderManager(this);
        try {
            orderManager.editRow(m_order.getOrderId(), "orderId", contentValues, OrderManager.TABLE_NAME);
        } catch (Exception exception) {
            Log.i("Error: ", exception.getMessage());
        }

        // Go back to previous activity.
        finish();
    }


}
