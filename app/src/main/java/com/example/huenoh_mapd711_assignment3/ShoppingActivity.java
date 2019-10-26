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

    private static Product m_product = new Product();

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

                // Get selected products
                m_product = products[i];

                // Move to ProductInformation activity
                Intent intent = new Intent(ShoppingActivity. this, ProductInformation.class);
                intent.putExtra("classProduct", m_product);
                startActivity(intent);
            }
        });

    }



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem menuItem){

        switch (menuItem.getItemId()){
            case R.id.profileItem:
                //TODO : move to profile activity.
                Toast.makeText(getApplicationContext(),"profileItem selected",Toast.LENGTH_LONG).show();
                break;
            case R.id.ordersItem:
                //TODO : move to orderlist activity -> AdminOrderList for now.
                Toast.makeText(getApplicationContext(),"ordersItem selected",Toast.LENGTH_LONG).show();
                break;
        }

        return true;
    }


}
