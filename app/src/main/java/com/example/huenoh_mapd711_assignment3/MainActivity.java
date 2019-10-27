package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Managers for DB
    private CustomerManager customerManager;
    private AdminManager adminManager;
    private ProductManager productManager;
    private OrderManager orderManager;


    EditText editTextName,editTextPassword;
    Button loginButton;
    RadioButton radioButtonAdmin,radioButtonUsers;
    int radioButtonValue = 0; // 0-admin, 1-customer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get views
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);
        radioButtonAdmin = findViewById(R.id.radioAdmin);
        radioButtonUsers = findViewById(R.id.radioUsers);


        // Set listener for the radio buttons
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGrp);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb1 = (RadioButton) findViewById(R.id.radioAdmin);
                if(rb1.isChecked()){
                    radioButtonValue = 0;
                    Toast.makeText(getApplicationContext(),"Yes", Toast.LENGTH_LONG).show();
                } else {
                    radioButtonValue = 1;
                }
            }
        });


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


    /**
     * This method is called when Login button is clicked.
     * @param view
     */
    public void onClickLoginButton(View view) {

        String userName = editTextName.getText().toString();
        String userPassword = editTextPassword.getText().toString();

        boolean isOK = false;


        if (radioButtonValue == 0) { // Admin

            if (loginAdmin(userName, userPassword)) {
                //System.out.println("successful login admin");
                isOK = true;
                // Move to AdminOrderList activity.
                Intent intent = new Intent(MainActivity. this,AdminOrdersList.class);
                startActivity(intent);
            }

        } else { // Customer

            if (loginCustomer(userName, userPassword)) {
                //System.out.println("successful login users");
                isOK = true;

                // Move to ShoppingActivity activity
                Intent intent = new Intent(MainActivity.this, ShoppingActivity.class);
                startActivity(intent);
            }
        }

        if (!isOK) {
            Toast.makeText(MainActivity.this, "Please check the usernmae and password", Toast.LENGTH_SHORT).show();
        }
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

                    // Set shared preferences
                    SharedPreferences preferences = getSharedPreferences("MyPreferences", 0);
                    SharedPreferences.Editor prefEditor = preferences.edit();
                    prefEditor.putString("CustomerName", customer.getUserName());
                    prefEditor.putString("UserType", "customer");
                    prefEditor.putInt("UserId", customer.getCustomerId());
                    prefEditor.commit();
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

                    // Set shared preferences
                    SharedPreferences preferences = getSharedPreferences("MyPreferences", 0);
                    SharedPreferences.Editor prefEditor = preferences.edit();
                    prefEditor.putString("AdminName", admin.getUserName());
                    prefEditor.putString("UserType", "admin");
                    prefEditor.putInt("UserId", admin.getEmployeeId());
                    prefEditor.commit();
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
        contentValues.put("phoneNumber", "+1-437-984-2110");
        contentValues.put("email", "heons921@gmail.com");


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
        contentValues.put("description",getString(R.string.dummy_description));

        // Add to the DB
        try {
            productManager.addRow(contentValues, ProductManager.TABLE_NAME);
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
        contentValues.put("description",getString(R.string.dummy_description));

        // Add to the DB
        try {
            productManager.addRow(contentValues, ProductManager.TABLE_NAME);
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
        contentValues.put("description",getString(R.string.dummy_description));

        // Add to the DB
        try {
            productManager.addRow(contentValues, ProductManager.TABLE_NAME);
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
        contentValues.put("description",getString(R.string.dummy_description));

        // Add to the DB
        try {
            productManager.addRow(contentValues, ProductManager.TABLE_NAME);
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
        contentValues.put("description",getString(R.string.dummy_description));

        // Add to the DB
        try {
            productManager.addRow(contentValues, ProductManager.TABLE_NAME);
        } catch (Exception exception) {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ", exception.getMessage());
        }
    }

}
