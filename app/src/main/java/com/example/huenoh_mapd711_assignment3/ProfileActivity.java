package com.example.huenoh_mapd711_assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    EditText userNameFirst, userNameLast, userEmail, userPhone, userAddress, userCity, userPostalCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
            Customer customer = customerManager.getCustomerById(strUserName , "userName");
            if (null != customer) {
                userNameFirst.setText(customer.getFirstName());
                userNameLast.setText(customer.getLastName());
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

        //disable some fileds
        userNameFirst.setEnabled(false);
        userNameLast.setEnabled(false);
    }

    /**
     * This method is for the click action of the Update button
     * It update customer's profile to the DB
     * @param view Confirm Order button
     */
    public void onClickBtnUpdate(View view) {
        //TODO : validation


    }
}
