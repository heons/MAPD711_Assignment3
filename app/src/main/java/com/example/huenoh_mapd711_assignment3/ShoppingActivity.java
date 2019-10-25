package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ShoppingActivity extends AppCompatActivity {


    ProductManager productManager = new ProductManager(this);
    Product[] products;
    String[] strProductsList;

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);


        // Get all the products from the database.
        try {
            this.products = productManager.getAllProducts();
        }
        catch (Exception exception)
        {
            Log.i("Error: ",exception.getMessage());
        }


        // Update strings for the list adapter
        strProductsList = new String[products.length];
        for (int i = 0; i < products.length; ++i) {
            strProductsList[i] = products[i].getProductName() + "(Quantity : " + products[i].getQuantity() + ")";
        }

        // Create array adapter for the list view
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, strProductsList);

        // Get list view for the products and set the adapter.
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // Set the listener.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(),listView.getItemAtPosition(i).toString(),
                        Toast.LENGTH_LONG).show();

                String x = adapter.getItem(i);
                System.out.println(x);

                Intent intent = new Intent(ShoppingActivity. this,ProductInformation.class);
                intent.putExtra("itemName",x);
                startActivity(intent);

            }
        });

    }



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

//    public boolean onOptionsItemSelected(MenuItem menuItem){
//
//        switch(menuItem.getItemId()){
//            case R.id.profile:
//                Toast.makeText(getApplicationContext(),"hello selected",Toast.LENGTH_LONG);
//
//                break;
//
//            case R.id.orders:
//                Toast.makeText(getApplicationContext(),"close selected",Toast.LENGTH_LONG);
//                break;
//        }
//
//        return true;
//
  //  }

    public boolean onOptionsItemSelected(MenuItem menuItem){

        switch (menuItem.getItemId()){
            case R.id.profileItem:
                Toast.makeText(getApplicationContext(),"profileItem selected",Toast.LENGTH_LONG);
                break;
            case R.id.ordersItem:
                Toast.makeText(getApplicationContext(),"ordersItem selected",Toast.LENGTH_LONG);
                break;
        }

        return true;
    }


}
