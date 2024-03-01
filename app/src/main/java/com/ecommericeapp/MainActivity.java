package com.ecommericeapp;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views



        // Get references to EditText fields
        EditText nameEditText = nameInputLayout.getEditText();
        EditText emailEditText = emailInputLayout.getEditText();

        // Example: Set a hint for the name field
        nameInputLayout.setHint("Name");

        // Example: Set a hint for the email field
        emailInputLayout.setHint("Email");

        // You can add more logic here, such as handling user input or form submission.
    }
}


