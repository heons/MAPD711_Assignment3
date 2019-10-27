package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    EditText userNameFirst, userNameLast, userEmail, userPhone, userAddress, userCity, userPostalCode;

    Customer m_customer = new Customer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Set title.
        getSupportActionBar().setTitle(R.string.activity_title_profile);

        // Get views
        userNameFirst = findViewById(R.id.profileFirstName);
        userNameLast = findViewById(R.id.profileLastName);
        userEmail = findViewById(R.id.profileEmail);
        userPhone = findViewById(R.id.profilePhone);
        userAddress = findViewById(R.id.profileAddress);
        userCity = findViewById(R.id.profileCity);
        userPostalCode = findViewById(R.id.profilePostalCode);


        // Get userName from shared preference.
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPreferences", 0); // 0 - for private mode
        String strUserName = pref.getString("CustomerName","");

        // Display user information
        CustomerManager customerManager = new CustomerManager(this);
        try {
            m_customer = customerManager.getCustomerById(strUserName , "userName");
            if (null != m_customer) {
                userNameFirst.setText(m_customer.getFirstName());
                userNameLast.setText(m_customer.getLastName());
                userPhone.setText(m_customer.getPhoneNumber());
                userEmail.setText(m_customer.getEmail());
                userAddress.setText(m_customer.getAddress());
                userCity.setText(m_customer.getCity());
                userPostalCode.setText(m_customer.getPostalCode());

            } else {}
        }
        catch (Exception exception)
        {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }

        //disable some fileds
        userNameFirst.setEnabled(false);
        userNameLast.setEnabled(false);
    }

    /**
     * This method is for the click action of the Update button
     * It update customer's profile to the DB
     * @param view btnUpdateProfile
     */
    public void onClickBtnUpdate(View view) {
        //TODO : validation

        // Initialize ContentValues for the update
        ContentValues contentValues;
        contentValues = new ContentValues();
        //contentValues.put("customerId", m_customer.getCustomerId());
        //contentValues.put("userName", m_customer.getUserName());
        contentValues.put("address", userAddress.getText().toString());
        contentValues.put("city", userCity.getText().toString());
        contentValues.put("postalCode", userPostalCode.getText().toString());
        contentValues.put("phoneNumber", userPhone.getText().toString());
        contentValues.put("email", userEmail.getText().toString());
        //contentValues.put("password", userEmail.getText().toString()); //TODO

        // Update the customer
        CustomerManager customerManager = new CustomerManager(this);
        try {
            customerManager.editRow(m_customer.getCustomerId(), "customerId", contentValues, CustomerManager.TABLE_NAME);
        } catch (Exception exception) {
            Log.i("Error: ", exception.getMessage());
        }

    }
}
