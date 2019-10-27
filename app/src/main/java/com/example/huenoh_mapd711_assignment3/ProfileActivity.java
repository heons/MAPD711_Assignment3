package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {

    EditText name,email,phoneNo,address,city,postalCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        name = findViewById(R.id.profileName);
        email = findViewById(R.id.profileEmail);
        phoneNo = findViewById(R.id.profilePhone);
        address = findViewById(R.id.profileAddress);
        city = findViewById(R.id.profileCity);
        postalCode = findViewById(R.id.profilePostalCode);


    }
}
