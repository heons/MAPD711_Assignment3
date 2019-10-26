package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ProductInformation extends AppCompatActivity {


    TextView productName,productDescription,productPrice;
    Spinner quantitySpinner;
    String[] arraySpinner = new String[] {
            "1", "2", "3", "4", "5", "6", "7"
    };


    Product m_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);

        productName = findViewById(R.id.itemName);
        productDescription = findViewById(R.id.itemDescription);
        productPrice = findViewById(R.id.itemPrice);
        quantitySpinner = findViewById(R.id.quantitySpinner);

//        quantitySpinner.ad

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(adapter);


        // Get product from previous activity.
        Intent intent = getIntent();
        m_product = (Product) intent.getSerializableExtra("classProduct");;

        productName.setText(m_product.getProductName());
        productDescription.setText("The iPhone is a smartphone made by Apple that combines a computer, iPod, digital camera and cellular phone into one device with a touchscreen interface. The iPhone runs the iOS operating system (OS), and as of 2017, there were 2.2 million apps available for it through the Apple App Store, according to Statista.");
        productPrice.setText(Double.toString(m_product.getPrice()));

    }


    public void onClickButton(View view){
        // TODO : Create an order and put it to the db.

        
        Intent intent = new Intent(ProductInformation.this,OrderDetails.class);
        startActivity(intent);
    }






}
