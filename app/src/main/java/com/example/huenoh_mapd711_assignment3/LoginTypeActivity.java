package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class LoginTypeActivity extends AppCompatActivity {

    String checkIfAdminOrCustomer;
    int adminButtonValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_type);


        Intent intent = getIntent();
        adminButtonValue = intent.getIntExtra("intValue",0);
        System.out.println(adminButtonValue);
    }
}
