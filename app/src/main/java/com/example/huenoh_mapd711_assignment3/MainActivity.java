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


    int checkIfAdminOrCustomer = 0;
    EditText editTextName,editTextPassword;
    Button loginButton;
    RadioButton radioButtonAdmin,radioButtonUsers;
    int radioButtonValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);
        radioButtonAdmin = findViewById(R.id.radioAdmin);
        radioButtonUsers = findViewById(R.id.radioUsers);




        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGrp);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb1 = (RadioButton) findViewById(R.id.radioAdmin);
                if(rb1.isChecked()){
                    //printToast("Yes, checked!");
                    radioButtonValue = 0;
                    Toast.makeText(getApplicationContext(),"Yes", Toast.LENGTH_LONG).show();
                } else {
                    radioButtonValue = 1;
//                    printToast("No checked!");
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



    public void onClickLoginButton(View view){

        String userName = editTextName.getText().toString();
        String userPassword = editTextPassword.getText().toString();

        if (radioButtonValue == 0){
             loginAdmin(userName,userPassword);
                System.out.println("successfull login admin");

                SharedPreferences preferences = getSharedPreferences("MyPreferences", 0);
                SharedPreferences.Editor prefEditor = preferences.edit();
                prefEditor.putString("name", userName);
                prefEditor.commit();

//            Intent intent = new Intent(MainActivity.this,LoginTypeActivity.class);
//            intent.putExtra("intValue",0);
//            startActivity(intent);


        }else{

            loginCustomer(userName,userPassword);
            SharedPreferences preferences = getSharedPreferences("MyPreferences", 0);
            SharedPreferences.Editor prefEditor = preferences.edit();
            prefEditor.putString("name", userName);
            prefEditor.commit();

            Intent intent = new Intent(MainActivity.this,ShoppingActivity.class);
            startActivity(intent);

            System.out.println("successfull login users");
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
