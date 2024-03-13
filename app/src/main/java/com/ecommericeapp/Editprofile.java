package com.ecommericeapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class Editprofile extends AppCompatActivity {

    ImageButton back_btn;
    ImageButton search_btn;
    ImageButton cart_btn;
    ImageButton men_avatar;
    ImageButton women_avatar;
    TextInputEditText first_name;
    TextInputEditText last_name;
    Button submit;
    TextInputEditText mobile_number;
    Button update_number;
    TextInputEditText e_mail;
    Button update_emailid;
    Button deactivate_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        back_btn=findViewById(R.id.back_btn);

    }
}