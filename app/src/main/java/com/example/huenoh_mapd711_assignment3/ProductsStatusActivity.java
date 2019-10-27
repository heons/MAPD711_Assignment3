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
import android.widget.TextView;

public class ProductsStatusActivity extends AppCompatActivity {

    /* Member variables */
    // Array for quantity spinner
    private String[] m_arrSpinnerQuantity;

    // View Object
    private TextView m_productName, m_productDescription, m_productPrice;
    private Spinner m_spinnerQuantity, m_spinnerStatus;

    // Product information from previous activity
    private Order m_order;

    private SharedPreferences m_pref;
    private String m_userType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_status);

        // Get views
        m_productName = findViewById(R.id.itemName);
        m_productDescription = findViewById(R.id.itemDescription);
        m_productPrice = findViewById(R.id.itemPrice);
        m_spinnerQuantity = findViewById(R.id.quantitySpinner1);
        m_spinnerStatus = findViewById(R.id.statusSpinner);

        // Get shared preference for UserType and UserID
        m_pref = getApplicationContext().getSharedPreferences("MyPreferences", 0); // 0 - for private mode
        m_userType = m_pref.getString("UserType", "");

        // Get product from previous activity.
        Intent intent = getIntent();
        m_order = (Order) intent.getSerializableExtra("classOrder");


        // Product Manager
        ProductManager productManager = new ProductManager(this);

        // Get the product from the database.
        try {
            Product product = productManager.getProductById(m_order.getProductId(), "productId");

            // Update product information
            m_productName.setText(product.getProductName());
            m_productDescription.setText(getString(R.string.dummy_description));
            m_productPrice.setText(Double.toString(product.getPrice()));

            // Set status spinner
            // Create array for spinner : Quantity selection for the product.
            int totalQuantity = product.getQuantity() + m_order.getQuantity();
            m_arrSpinnerQuantity = new String[totalQuantity + 1];
            for (int i = 0; i < totalQuantity + 1; ++i) {
                m_arrSpinnerQuantity[i] = Integer.toString(i);
            }

            ArrayAdapter<String> adapterQuantity = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, m_arrSpinnerQuantity);
            adapterQuantity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            m_spinnerQuantity.setAdapter(adapterQuantity);
            m_spinnerQuantity.setSelection(m_order.getQuantity());

        } catch (Exception exception) {
            Log.i("Error: ",exception.getMessage());
        }


        // Spinner selection for status
        if ( m_order.getStatus().equals(getString(R.string.status_inProgress)) ) {
            m_spinnerStatus.setSelection(0);
        } else {
            m_spinnerStatus.setSelection(1);
        }

        // Disable buttons by UserType
        if (m_userType.equals("admin")) {
            m_spinnerQuantity.setEnabled(false);
        } else if (m_userType.equals("customer")) {
            m_spinnerStatus.setEnabled(false);
        } else {

        }

    }


    /**
     * This method is for the click action of the update button
     * @param view update button
     */
    public void onClickBtnUpdate(View view){

        // Initialize ContentValues for the order
        ContentValues contentValues;
        contentValues = new ContentValues();
        contentValues.put("status", m_spinnerStatus.getSelectedItem().toString());
        contentValues.put("quantity", m_spinnerQuantity.getSelectedItem().toString());

        // Update the order
        OrderManager orderManager = new OrderManager(this);
        try {
            orderManager.editRow(m_order.getOrderId(), "orderId", contentValues, OrderManager.TABLE_NAME);
        } catch (Exception exception) {
            Log.i("Error: ", exception.getMessage());
        }


        // Update product quantity
        ProductManager productManager = new ProductManager(this);
        try {
            // get product
            Product product = productManager.getProductById(m_order.getProductId(), "productId");

            contentValues = new ContentValues();
            int quantityChanged = (int)m_spinnerQuantity.getSelectedItemId() - m_order.getQuantity();
            contentValues.put("quantity", product.getQuantity() - quantityChanged);

            // Update product
            try {
                productManager.editRow(m_order.getProductId(), "productId", contentValues, ProductManager.TABLE_NAME);
            } catch (Exception exception) {
                Log.i("Error: ", exception.getMessage());
            }

        } catch (Exception exception) {
            Log.i("Error: ", exception.getMessage());
        }


        // Delete the order if the quantity is 0
        if (0 == m_spinnerQuantity.getSelectedItemId()) {
            try {
                orderManager.deleteRow(m_order.getOrderId(), "orderId", OrderManager.TABLE_NAME);
            } catch (Exception exception) {
                Log.i("Error: ", exception.getMessage());
            }
        }

        // Go back to previous activity.
        finish();
    }


}
