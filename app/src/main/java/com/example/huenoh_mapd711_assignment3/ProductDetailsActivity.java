package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailsActivity extends AppCompatActivity {

    // View Object
    TextView productName,productDescription,productPrice;
    Spinner quantitySpinner;

    // Array for a spinner
    String[] arraySpinner;

    // Product information from previous activity
    Product m_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Set title.
        getSupportActionBar().setTitle(R.string.activity_title_productDetails);

        // Get views
        productName = findViewById(R.id.itemName);
        productDescription = findViewById(R.id.itemDescription);
        productPrice = findViewById(R.id.itemPrice);
        quantitySpinner = findViewById(R.id.quantitySpinner);


        // Get product from previous activity.
        Intent intent = getIntent();
        m_product = (Product) intent.getSerializableExtra("classProduct");

        // Set product information
        productName.setText(m_product.getProductName());
        productDescription.setText(m_product.getDescription());
        productPrice.setText(Double.toString(m_product.getPrice()));

        // Create array for spinner : Quantity selection for the product.
        arraySpinner = new String[m_product.getQuantity() + 1];
        for (int i = 0; i < m_product.getQuantity() + 1; ++i) {
            arraySpinner[i] = Integer.toString(i);
        }

        // Set Spinner with adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);
    }


    public void onClickButton(View view){

        // Check selected quantity.
        int selQuantity = (int)(quantitySpinner.getSelectedItemId());
        if(selQuantity != 0) {
            // Send product information and quantity to order detail.
            // TODO : need to check quantity of the product and reserve it in DB.
            Intent intent = new Intent(ProductDetailsActivity.this, OrderDetailsActivity.class);
            intent.putExtra("classProduct", m_product);
            intent.putExtra("quantity", selQuantity);
            startActivity(intent);

            // Done. Go back to previous page.
            finish();

        } else {
            //TODO : use string.xml for the message
            Toast.makeText(this, "select product quantity", Toast.LENGTH_SHORT).show();
        }
    }






}
