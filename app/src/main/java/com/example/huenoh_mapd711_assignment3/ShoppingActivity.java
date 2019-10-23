package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class ShoppingActivity extends AppCompatActivity {


    ProductManager product=new ProductManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);



        try {
            Product[] products = product.getAllProducts();
            Log.d("","");
        }
        catch (Exception exception)
        {
            Log.i("Error: ",exception.getMessage());
        }
    }




}
