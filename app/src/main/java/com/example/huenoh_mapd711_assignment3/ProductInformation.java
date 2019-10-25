package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProductInformation extends AppCompatActivity {


    TextView productName,productDescription,productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);

        productName = findViewById(R.id.itemName);
        productDescription = findViewById(R.id.itemDescription);
        productPrice = findViewById(R.id.itemPrice);

        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        System.out.println(itemName);

        productName.setText(itemName);
        productDescription.setText("The iPhone is a smartphone made by Apple that combines a computer, iPod, digital camera and cellular phone into one device with a touchscreen interface. The iPhone runs the iOS operating system (OS), and as of 2017, there were 2.2 million apps available for it through the Apple App Store, according to Statista.");
        productPrice.setText("$33333");
    }


    public void onClickButton(View view){
          Intent intent = new Intent(ProductInformation.this,OrderDetails.class);
          startActivity(intent);
    }






}
