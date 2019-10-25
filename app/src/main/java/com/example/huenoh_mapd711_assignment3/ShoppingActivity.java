package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ShoppingActivity extends AppCompatActivity {


    ProductManager productManager = new ProductManager(this);
    Product product = new Product();
//    final ListView listView;
    ListView listView;

    String[] courses = {
            "iOS",
            "Samsung",
            "Flutter",
            "Angular",
            "angad",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,courses);


        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(),listView.getItemAtPosition(i).toString(),
                        Toast.LENGTH_LONG).show();

                String x = adapter.getItem(i);
                System.out.println(x);

                Intent intent = new Intent(ShoppingActivity.this,ProductInformation.class);
                intent.putExtra("itemName",x);
                startActivity(intent);

            }
        });


        try {
            Product[] products = productManager.getAllProducts();
            Log.d("","");


            System.out.println("products are");
            System.out.println(products);


//            String x = products.
        }
        catch (Exception exception)
        {
            Log.i("Error: ",exception.getMessage());
        }



    }
}
