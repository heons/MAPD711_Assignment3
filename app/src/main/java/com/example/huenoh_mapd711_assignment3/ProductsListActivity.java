package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProductsListActivity extends AppCompatActivity {

    /* Member variables */
    private ListView m_listViewProduct; // List view for products

    private Product[] m_products;       // Array for products
    private String[] m_strProductsList; // Array for products' display strings


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        // Get product list view
        m_listViewProduct = findViewById(R.id.listView);

        // Update product list view
        updateProductList();

        // Set the listener.
        m_listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Move to ProductDetailsActivity activity
                Intent intent = new Intent(ProductsListActivity. this, ProductDetailsActivity.class);
                intent.putExtra("classProduct", m_products[i]);
                startActivity(intent);
            }
        });
    }

    /**
     * onResume operation.
     */
    @Override
    protected void onResume() {
        super.onResume();

        // Update the product list view
        updateProductList();
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem menuItem){

        switch (menuItem.getItemId()){
            case R.id.profileItem:
                // Move to ProfileActivity
                Intent intent = new Intent(ProductsListActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.ordersItem:
                // Move to OrdersListActivity
                Intent intent1 = new Intent(ProductsListActivity.this, OrdersListActivity.class);
                startActivity(intent1);
                break;
        }

        return true;
    }


    /**
     * Update the product list view by loading data from the DB
     */
    private void updateProductList() {
        // Product Manager
        ProductManager productManager = new ProductManager(this);

        // Get all the products from the database.
        try {
            m_products = productManager.getAllProducts();
        }
        catch (Exception exception)
        {
            Log.i("Error: ",exception.getMessage());
        }


        // Update strings for the list adapter
        m_strProductsList = new String[m_products.length];
        for (int i = 0; i < m_products.length; ++i) {
            m_strProductsList[i] = Integer.toString(i + 1) + ". "
                    + m_products[i].getProductName()
                    + "(Quantity : " + m_products[i].getQuantity() + ")";
        }

        // Create array adapter for the list view
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, m_strProductsList);

        // Get list view for the products and set the adapter.
        m_listViewProduct.setAdapter(adapter);
    }

}
