package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Managers for DB
    private CustomerManager customerManager;
    private AdminManager adminManager;
    private ProductManager productManager;
    private OrderManager orderManager;


    Button managerBtn,userButton;
    int checkIfAdminOrCustomer = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        managerBtn = findViewById(R.id.managerBtn);
        userButton = findViewById(R.id.userBtn);

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

        // To delete all
        //boolean checkDeleteCustomer = customerManager.deleteAll();

        // Add to the DB
        addCustomer(); // Add a customer
        addAdmin();    // Add an admin
        addProducts(); // Add products

    }



    public void onClickAdminButton(View view){
        Intent intent = new Intent(MainActivity.this,LoginTypeActivity.class);
        intent.putExtra("intValue",0);
        startActivity(intent);
    }


    public void onClickCustomerButton(View view){
        Intent intent = new Intent(MainActivity.this,LoginTypeActivity.class);
        intent.putExtra("intValue",1);
        startActivity(intent);
    }


    /**
     * This method try login as Customer
     * @param strUserName UserName of the customer
     * @param strPassword Password of the customer
     * @return            True if it succeeded, false otherwise.
     */
    public boolean loginCustomer(String strUserName, String strPassword)
    {
        boolean rc = false;

        try {
            Customer customer = customerManager.getCustomerById(strUserName , "userName");
            if (null != customer) {
                if(customer.getPassword().equals(strPassword)) {
                    rc = true;
                } else {}
            } else {}
        }
        catch (Exception exception)
        {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }

        return rc;
    }

    /**
     * This method try login as Admin
     * @param strUserName UserName of the admin
     * @param strPassword Password of the admin
     * @return            True if it succeeded, false otherwise.
     */
    public boolean loginAdmin(String strUserName, String strPassword)
    {
        boolean rc = false;

        try {
            Admin admin = adminManager.getAdminById(strUserName , "userName");
            if (null != admin) {
                if(admin.getPassword().equals(strPassword)) {
                    rc = true;
                } else {}
            } else {}
        }
        catch (Exception exception)
        {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }

        return rc;
    }

    /**
     * Add a customer
     */
    public void addCustomer() {
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
        } catch (Exception exception) {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ", exception.getMessage());
        }
    }

    /**
     * Add an admin
     */
    public void addAdmin() {
        // Initialize ContentValues object with the new admin
        ContentValues contentValues = new ContentValues();
        contentValues.put("employeeId", 1);
        contentValues.put("userName", "angad");
        contentValues.put("password", "admin");
        contentValues.put("firstName", "Angadjot");
        contentValues.put("lastName", "Modi");

        // Add to the DB
        try {
            adminManager.addRow(contentValues, AdminManager.TABLE_NAME);
            Toast.makeText(MainActivity.this, "a admin added", Toast.LENGTH_SHORT).show();
        } catch (Exception exception) {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ", exception.getMessage());
        }
    }

    /**
     * Add products
     */
    public void addProducts() {
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

        // Initialize ContentValues object with the new customer
        contentValues = new ContentValues();
        contentValues.put("productId", 2);
        contentValues.put("productName", "iPhoneXR");
        contentValues.put("price", 1100.9);
        contentValues.put("quantity", 10);
        contentValues.put("category", "Mobile");

        // Add to the DB
        try {
            productManager.addRow(contentValues, ProductManager.TABLE_NAME);
            Toast.makeText(MainActivity.this, "a product added", Toast.LENGTH_SHORT).show();
        } catch (Exception exception) {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ", exception.getMessage());
        }

        // Initialize ContentValues object with the new customer
        contentValues = new ContentValues();
        contentValues.put("productId", 3);
        contentValues.put("productName", "LG v30");
        contentValues.put("price", 500);
        contentValues.put("quantity", 10);
        contentValues.put("category", "Mobile");

        // Add to the DB
        try {
            productManager.addRow(contentValues, ProductManager.TABLE_NAME);
            Toast.makeText(MainActivity.this, "a product added", Toast.LENGTH_SHORT).show();
        } catch (Exception exception) {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ", exception.getMessage());
        }

        // Initialize ContentValues object with the new customer
        contentValues = new ContentValues();
        contentValues.put("productId", 4);
        contentValues.put("productName", "LG G7");
        contentValues.put("price", 610.10);
        contentValues.put("quantity", 10);
        contentValues.put("category", "Mobile");

        // Add to the DB
        try {
            productManager.addRow(contentValues, ProductManager.TABLE_NAME);
            Toast.makeText(MainActivity.this, "a product added", Toast.LENGTH_SHORT).show();
        } catch (Exception exception) {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ", exception.getMessage());
        }

        // Initialize ContentValues object with the new customer
        contentValues = new ContentValues();
        contentValues.put("productId", 5);
        contentValues.put("productName", "Samsung S9");
        contentValues.put("price", 610.10);
        contentValues.put("quantity", 10);
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

}
