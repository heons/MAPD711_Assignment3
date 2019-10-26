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

public class OrderDetails extends AppCompatActivity {

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

        userName = findViewById(R.id.name);
        userPhone = findViewById(R.id.phone);
        userEmail = findViewById(R.id.email);
        userAddress = findViewById(R.id.address);
        userCity = findViewById(R.id.city);
        userPostalCode = findViewById(R.id.postalCode);


        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        productQuantity = findViewById(R.id.productQuantity);
        productCategory = findViewById(R.id.productCategory);


        // Get product from previous activity.
        Intent intent = getIntent();
        m_product = (Product) intent.getSerializableExtra("classProduct");
        m_quantity = intent.getIntExtra("quantity", 1);

        // Update user information
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPreferences", 0); // 0 - for private mode
        userName.setText(pref.getString("CustomerName",""));

        // TODO : add them to the DB
        userPhone.setText("+1 2223234565");
        userEmail.setText("Angad@gmail.com");
        userAddress.setText("Ellesmere Road");
        userCity.setText("Scarborough");
        userPostalCode.setText("M1R3R7");

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

//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//            contentValues.put("orderDate", LocalDateTime.now().format(dtf));

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
