package com.ecommericeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.ecommericeapp.Data.Address;
import com.ecommericeapp.databinding.ActivitySaveAddressBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class saved_address extends AppCompatActivity {
    private ActivitySaveAddressBinding binding;
    DatabaseReference databaseReference;
    private String selectedAddressType = "";
    String name,address_type,state,city,pin_code,house_no,road_name,phone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySaveAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference= FirebaseDatabase.getInstance().getReference();
        String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference1=databaseReference.child("users").child(uid).child("Address");

        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAddressType = "Home";

                binding.home.setBackgroundColor(getResources().getColor(R.color.white));
                binding.work.setBackgroundColor(getResources().getColor(android.R.color.transparent));


            }
        });
        binding.work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAddressType = "Work";

                binding.work.setBackgroundColor(getResources().getColor(R.color.white));
                binding.home.setBackgroundColor(getResources().getColor(android.R.color.transparent));

            }
        });



        binding.saveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.fullName.getEditText().getText().toString().trim();
                String phone = binding.phoneNumber.getEditText().getText().toString().trim();
                String pin_code = binding.pincode.getEditText().getText().toString().trim();
                String state = binding.state.getEditText().getText().toString().trim();
                String city = binding.city.getEditText().getText().toString().trim();
                String house_no = binding.houseNo.getEditText().getText().toString().trim();
                String road_name = binding.roadName.getEditText().getText().toString().trim();


                Address address=new Address(name,phone,pin_code,state,city,house_no,road_name,selectedAddressType);
//                binding.progress.setVisibility(View.VISIBLE);
//                binding.progressbar.setVisibility(View.VISIBLE);

//                Handler handler=new Handler();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        binding.progress.setVisibility(View.VISIBLE);
//                        binding.progressbar.setVisibility(View.VISIBLE);
//
//
//
//                    }
//                });
//


                databaseReference1.setValue(address);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            address_type = intent.getStringExtra("AddressType");
            city= intent.getStringExtra("City");
            phone= intent.getStringExtra("Phone");
            house_no= intent.getStringExtra("HouseNo");
            name= intent.getStringExtra("Name");
            pin_code= intent.getStringExtra("PinCode");
            road_name= intent.getStringExtra("RoadName");
            state= intent.getStringExtra("State");

            TextInputEditText fullNameEditText = (TextInputEditText) binding.fullName.getEditText();
            TextInputEditText phoneNumberEditText = (TextInputEditText) binding.phoneNumber.getEditText();
            TextInputEditText pincodeEditText = (TextInputEditText) binding.pincode.getEditText();
            TextInputEditText stateEditText = (TextInputEditText) binding.state.getEditText();
            TextInputEditText cityEditText = (TextInputEditText) binding.city.getEditText();
            TextInputEditText houseNoEditText = (TextInputEditText) binding.houseNo.getEditText();
            TextInputEditText roadNameEditText = (TextInputEditText) binding.roadName.getEditText();

            assert fullNameEditText != null;
            fullNameEditText.setText(name);
            phoneNumberEditText.setText(phone);
            pincodeEditText.setText(pin_code);
            stateEditText.setText(state);
            cityEditText.setText(city);
            houseNoEditText.setText(house_no);
            roadNameEditText.setText(road_name);

            if (Objects.equals(address_type, "Home")){
                selectedAddressType = "Home";

                binding.home.setBackgroundColor(getResources().getColor(R.color.white));
                binding.work.setBackgroundColor(getResources().getColor(android.R.color.transparent));


            }else {
                selectedAddressType = "Work";

                binding.work.setBackgroundColor(getResources().getColor(R.color.white));
                binding.home.setBackgroundColor(getResources().getColor(android.R.color.transparent));


            }





        }




    }
}