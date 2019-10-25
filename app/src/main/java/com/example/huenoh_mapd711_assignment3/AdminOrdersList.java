package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AdminOrdersList extends AppCompatActivity {


    ListView listView;

    String[] list = {
            "Iphone 8",
            "Samsung Microwave",
            "Rice",
            "Laptop",
            "Wooden Table",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_orders_list);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,list);


        listView = findViewById(R.id.ordersList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(),listView.getItemAtPosition(i).toString(),
                        Toast.LENGTH_LONG).show();

                String x = adapter.getItem(i);
                System.out.println(x);


            }
        });

    }
}
