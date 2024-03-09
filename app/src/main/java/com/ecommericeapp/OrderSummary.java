package com.ecommericeapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ecommericeapp.Adapter.ProductAdapter;
import com.ecommericeapp.Data.cartdata;
import com.ecommericeapp.databinding.ActivityOrderSummaryBinding;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderSummary extends AppCompatActivity implements PaymentResultListener {

    ImageView imageView;
    private ActivityOrderSummaryBinding binding;

    String url,price,title;

    String name,address_type,state,city,pin_code,house_no,road_name,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityOrderSummaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("ans");
            price = intent.getStringExtra("price");
            title = intent.getStringExtra("title");
            binding.title.setText(title);
            binding.price.setText(price);
            Glide.with(this)
                    .load(url)
                    .into(binding.productimage);


            // Do something with productId
        }




        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference cartRef=databaseReference.child("users").child(uid).child("Address");

        cartRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot productSnapshot) {
//                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                     address_type= productSnapshot.child("address_type").getValue(String.class);
                    name = productSnapshot.child("name").getValue(String.class);
                    house_no = productSnapshot.child("house_no").getValue(String.class);
                    city = productSnapshot.child("city").getValue(String.class);
                    phone = productSnapshot.child("phone").getValue(String.class);
                    pin_code = productSnapshot.child("pin_code").getValue(String.class);
                    road_name= productSnapshot.child("road_name").getValue(String.class);
                    state= productSnapshot.child("state").getValue(String.class);
                    // Get other product details if needed
                    if (name != null) {
                        binding.username.setText(name+","+address_type);
                        binding.Address.setText(state+","+city+","+pin_code+","+house_no+","+road_name);
                        binding.phone.setText(phone);

//
                    }else{
                        binding.username.setVisibility(View.INVISIBLE);
                        binding.Address.setVisibility(View.INVISIBLE);
                        binding.phone.setVisibility(View.INVISIBLE);
                        binding.save.setVisibility(View.VISIBLE);
                        binding.save.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(OrderSummary.this, saved_address.class);

                                startActivity(intent);
                            }
                        });
                    }
                }




//            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
        binding.adresschange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OrderSummary.this, saved_address.class);
                intent.putExtra("AddressType", address_type);
                intent.putExtra("Name", name);
                intent.putExtra("HouseNo", house_no);
                intent.putExtra("City", city);
                intent.putExtra("Phone", phone);
                intent.putExtra("PinCode", pin_code);
                intent.putExtra("RoadName", road_name);
                intent.putExtra("State", state);
                startActivity(intent);

            }
        });
        // adding on click listener to our button.
        binding.continuef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkout.preload(getApplicationContext());
                Checkout checkout=new Checkout();

                checkout.setKeyID("<YOUR_KEY_ID>");

                try {
                    JSONObject options = new JSONObject();

                    options.put("name", "Merchant Name");
                    options.put("description", "Reference No. #123456");
                    options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
                    options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
                    options.put("theme.color", "#3399cc");
                    options.put("currency", "INR");
                    options.put("amount", "50000");//pass amount in currency subunits
                    options.put("prefill.email", "anggsheskfmeskihsinghjamsar@gmail.com");
                    options.put("prefill.contact","9988776655737");
                    JSONObject retryObj = new JSONObject();
                    retryObj.put("enabled", true);
                    retryObj.put("max_count", 4);
                    options.put("retry", retryObj);

                    checkout.open(OrderSummary.this,options);

                } catch(Exception e) {
                    Log.e(TAG, "Error in starting Razorpay Checkout", e);
                }







            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();


    }
}