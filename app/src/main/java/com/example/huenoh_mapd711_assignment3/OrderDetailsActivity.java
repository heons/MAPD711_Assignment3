package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderDetailsActivity extends AppCompatActivity {

    /* Member variables */
    // OrderIDs - will be increased automatically.
    private static int m_orderId = 0;

    // Views
    TextView userName,userPhone,userEmail,userAddress,userCity,userPostalCode;
    TextView productName,productPrice,productQuantity,productCategory;

    // Product information from previous activity
    private Product m_product;
    private int m_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        // Set title.
        getSupportActionBar().setTitle(R.string.activity_title_orderDetails);

        // Get views for user
        userName = findViewById(R.id.name);
        userPhone = findViewById(R.id.phone);
        userEmail = findViewById(R.id.email);
        userAddress = findViewById(R.id.address);
        userCity = findViewById(R.id.city);
        userPostalCode = findViewById(R.id.postalCode);

        // Get views for product
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        productQuantity = findViewById(R.id.productQuantity);
        productCategory = findViewById(R.id.productCategory);

        //TODO : find better way to do this.
        // Update orderId for new orders
        OrderManager orderManager = new OrderManager(this);
        try {
            Order[] orders = orderManager.getAllOrders();
            for(int i = 0; i < orders.length; ++i) {
                if(orders[i].getOrderId() > m_orderId) {
                    m_orderId = orders[i].getOrderId();
                }
            }
        }
        catch (Exception exception)
        {
            Log.i("Error: ",exception.getMessage());
        }


        // Get product from previous activity.
        Intent intent = getIntent();
        m_product = (Product) intent.getSerializableExtra("classProduct");
        m_quantity = intent.getIntExtra("quantity", 1);

        // Get userName from shared preference.
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPreferences", 0); // 0 - for private mode
        String strUserName = pref.getString("CustomerName","");


        // Display user information
        CustomerManager customerManager = new CustomerManager(this);
        try {
            Customer customer = customerManager.getCustomerById(strUserName , "userName");
            if (null != customer) {
                userName.setText(customer.getFirstName() + " " + customer.getLastName());
                userPhone.setText(customer.getPhoneNumber());
                userEmail.setText(customer.getEmail());
                userAddress.setText(customer.getAddress());
                userCity.setText(customer.getCity());
                userPostalCode.setText(customer.getPostalCode());

            } else {}
        }
        catch (Exception exception)
        {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }

        // Display product information
        productName.setText(m_product.getProductName());
        productPrice.setText(
                Double.toString(m_product.getPrice()) + " * " + Integer.toString(m_quantity) + " = "
                        + Double.toString(m_product.getPrice() * m_quantity));
        productQuantity.setText(Integer.toString(m_quantity));
        productCategory.setText(m_product.getCategory());
    }


    /**
     * This method is for the click action of the Confirm Order button
     * It creates an order and save it to the DB
     * @param view Confirm Order button
     */
    public void onClickBtnConfirmOrder(View view){

        // Get shared preference for customerId
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPreferences", 0); // 0 - for private mode
        int customerID = pref.getInt("UserId", -1); // getting Integer
        if (customerID != -1) {

            // Initialize ContentValues object with the new order
            ContentValues contentValues = new ContentValues();
            this.m_orderId++;
            contentValues.put("orderId", this.m_orderId);
            contentValues.put("customerId", customerID);
            contentValues.put("productId", m_product.getProductId());
            contentValues.put("employeeId", 1); //TODO : do something
            contentValues.put("quantity", m_quantity);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            contentValues.put("orderDate", LocalDateTime.now().format(dtf));
            //contentValues.put("orderDate", "2019/10/25 00:00:00");

            contentValues.put("status", getString(R.string.status_inProgress));

            // Add an order to the DB
            OrderManager orderManager = new OrderManager(this);
            try {
                orderManager.addRow(contentValues, OrderManager.TABLE_NAME);

                // Initialize ContentValues object for the edit
                contentValues = new ContentValues();
                contentValues.put("quantity", m_product.getQuantity() - m_quantity);

                // Update product quantity.
                ProductManager productManager = new ProductManager(this);
                try {
                    productManager.editRow(m_product.getProductId(), "productId", contentValues, ProductManager.TABLE_NAME);
                } catch (Exception exception) {
                    //TODO : what if it fails?
                    Log.i("Error: ", exception.getMessage());
                }
            } catch (Exception exception) {
                Log.i("Error: ", exception.getMessage());
            }

            // Done. Go back to previous page.
            finish();
        }
    }


}
