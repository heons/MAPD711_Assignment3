package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrderDetails extends AppCompatActivity {


    TextView userName,userPhone,userEmail,userAddress,userCity,userPostalCode;
    TextView productName,productPrice,productQuantity,productCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        userName = findViewById(R.id.name);
        userPhone = findViewById(R.id.phone);
        userEmail = findViewById(R.id.email);
        userAddress = findViewById(R.id.address);
        userCity = findViewById(R.id.city);
        userPostalCode = findViewById(R.id.postalCode);


        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        productQuantity = findViewById(R.id.productQuantity);
        productCategory = findViewById(R.id.productCategory);


        userName.setText("Angad");
        userPhone.setText("+1 2223234565");
        userEmail.setText("Angad@gmail.com");
        userAddress.setText("Ellesmere Road");
        userCity.setText("Scarborough");
        userPostalCode.setText("M1R3R7");

        productName.setText("iPhone");
        productPrice.setText("$800");
        productQuantity.setText("1");
        productCategory.setText("Electronics");


    }







}
