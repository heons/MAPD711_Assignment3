package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ProductInformation extends AppCompatActivity {

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
        setContentView(R.layout.activity_product_information);

        productName = findViewById(R.id.itemName);
        productDescription = findViewById(R.id.itemDescription);
        productPrice = findViewById(R.id.itemPrice);
        quantitySpinner = findViewById(R.id.quantitySpinner);


        // Get product from previous activity.
        Intent intent = getIntent();
        m_product = (Product) intent.getSerializableExtra("classProduct");;

        // Set product information
        productName.setText(m_product.getProductName());
        // TODO : add description to the database and use it.
        productDescription.setText("The iPhone is a smartphone made by Apple that combines a computer, iPod, digital camera and cellular phone into one device with a touchscreen interface. The iPhone runs the iOS operating system (OS), and as of 2017, there were 2.2 million apps available for it through the Apple App Store, according to Statista.");
        productPrice.setText(Double.toString(m_product.getPrice()));

        // Create array for spinner : Quantity selection for the product.
        arraySpinner = new String[m_product.getQuantity()];
        for (int i = 0; i < m_product.getQuantity(); ++i) {
            arraySpinner[i] = Integer.toString(i+1);
        }

        // Set Spinner with adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);
    }


    public void onClickButton(View view){
        // TODO : Create an order and put it to the db.

        
        Intent intent = new Intent(ProductInformation.this,OrderDetails.class);
        startActivity(intent);
    }






}
