package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Managers for DB
    private CustomerManager customerManager;
    private AdminManager adminManager;
    private ProductManager productManager;
    private OrderManager orderManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate the CustomerManager
        try {
            customerManager = new CustomerManager(this);
        }
        catch(Exception exception)
        {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ", exception.getMessage());
        }

        // instantiate the AdminManager
        try {
            adminManager = new AdminManager(this);
        }
        catch(Exception exception)
        {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ", exception.getMessage());
        }

        // instantiate the ProductManager
        try {
            productManager = new ProductManager(this);
        }
        catch(Exception exception)
        {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ", exception.getMessage());
        }

        // instantiate the OrderManager
        try {
            orderManager = new OrderManager(this);
        }
        catch(Exception exception)
        {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ", exception.getMessage());
        }

        // Test Customer Field in DB
        testAddCustomer();
        testRetrieveCustomer();

        // Test Admin Field in DB
        testAddAdmin();
        testRetrieveAdmin();

        // Test Product Field in DB
        testAddProduct();
        testRetrieveProduct();
    }


    // Test function to Add Customer
    public void testAddCustomer() {
        // Initialize ContentValues object with the new customer
        ContentValues contentValues = new ContentValues();
        contentValues.put("customerId", 1);
        contentValues.put("userName", "heon");
        contentValues.put("password", "pwd");
        contentValues.put("firstName", "huen");
        contentValues.put("lastName", "oh");
        contentValues.put("address", "28 uptown");
        contentValues.put("city", "markham");
        contentValues.put("postalCode", "L3R5M8");

        // Add to the DB
        try {
            customerManager.addRow(contentValues, CustomerManager.TABLE_NAME);
            Toast.makeText(MainActivity.this, "a customer added", Toast.LENGTH_SHORT).show();
        } catch (Exception exception) {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ", exception.getMessage());
        }
    }

    // Test function to Retrieve Customer
    public void testRetrieveCustomer()
    {
        try {
            Customer customer = customerManager.getCustomerById("1" , "customerId");
            Log.d("main", Integer.toString(customer.getCustomerId()));
            Log.d("main", customer.getUserName());
            Log.d("main", customer.getPassword());
            Log.d("main", customer.getFirstName());
            Log.d("main", customer.getLastName());
            Log.d("main", customer.getAddress());
            Log.d("main", customer.getCity());
            Log.d("main", customer.getPostalCode());
        }
        catch (Exception exception)
        {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }
    }


    // Test function to Add Admin
    public void testAddAdmin() {
        // Initialize ContentValues object with the new admin
        ContentValues contentValues = new ContentValues();
        contentValues.put("employeeId", 1);
        contentValues.put("userName", "heon_admin");
        contentValues.put("password", "pwd_amdin");
        contentValues.put("firstName", "huenAdmin");
        contentValues.put("lastName", "ohAdmin");

        // Add to the DB
        try {
            adminManager.addRow(contentValues, AdminManager.TABLE_NAME);
            Toast.makeText(MainActivity.this, "a admin added", Toast.LENGTH_SHORT).show();
        } catch (Exception exception) {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ", exception.getMessage());
        }
    }


    // Test function to Retrieve Admin
    public void testRetrieveAdmin()
    {
        try {
            Admin admin = adminManager.getAdminById("1" , "employeeId");
            Log.d("main", Integer.toString(admin.getEmployeeId()));
            Log.d("main", admin.getUserName());
            Log.d("main", admin.getPassword());
            Log.d("main", admin.getFirstName());
            Log.d("main", admin.getLastName());
        }
        catch (Exception exception)
        {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }
    }


    // Test function to Add Customer
    public void testAddProduct() {
        // Initialize ContentValues object with the new customer
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", 1);
        contentValues.put("productName", "iPhoneX");
        contentValues.put("price", 1000.9);
        contentValues.put("quantity", 1);
        contentValues.put("category", "Mobile");

        // Add to the DB
        try {
            productManager.addRow(contentValues, ProductManager.TABLE_NAME);
            Toast.makeText(MainActivity.this, "a product added", Toast.LENGTH_SHORT).show();
        } catch (Exception exception) {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ", exception.getMessage());
        }
    }

    // Test function to Retrieve Customer
    public void testRetrieveProduct()
    {
        try {
            Product product = productManager.getProductById("1" , "productId");
            Log.d("main", Integer.toString(product.getProductId()));
            Log.d("main", product.getProductName());
            Log.d("main", Double.toString(product.getPrice()));
            Log.d("main", Integer.toString(product.getQuantity()));
            Log.d("main", product.getCategory());
        }
        catch (Exception exception)
        {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }
    }


}
