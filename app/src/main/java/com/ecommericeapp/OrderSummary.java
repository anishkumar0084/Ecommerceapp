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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class OrderSummary extends AppCompatActivity implements PaymentResultListener {

    ImageView imageView;
    private ActivityOrderSummaryBinding binding;

    String url,price,title,Discount,charge,offer,sizek,sht_d;

    String name,address_type,state,city,pin_code,house_no,road_name,phone;
    String sizes,quantity,totals,total_amounts;

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
            sizes= intent.getStringExtra("sizes");
            quantity= intent.getStringExtra("quantity");
            Discount = intent.getStringExtra("Discount");
            offer = intent.getStringExtra("offer");
            charge = intent.getStringExtra("charge");
            sizek = intent.getStringExtra("size");
            sht_d= intent.getStringExtra("sht_d");
            binding.offer.setText(offer);
            binding.shrtDes.setText(sht_d);

            if (sizek.equals("No")){
                binding.quantity.setText("Qty:"+quantity);

            }else {
                binding.quantity.setText("sizes:"+sizes);


            }



            binding.title.setText(title);
            binding.price.setText("₹ "+price);
            binding.price2.setText("Price("+quantity+" item)");

            int int1=Integer.valueOf(quantity);
            int into = Integer.parseInt(Discount);
            int total_dis=int1*into;
            Discount=String.valueOf(total_dis);
            binding.Discount.setText("₹ "+Discount);
            binding.save3.setText("You will save ₹"+Discount+" on this order");

            if (price != null) {
                int int2 = Integer.parseInt(price);
                int total=int1*int2;
                 totals=String.valueOf(total);
                binding.pricesk.setText("₹ "+totals);
                if (charge.equals("0")||500<int2){
                    binding.charge.setText("FREE Delivery");
                    charge="0";

                }else {
                    binding.charge.setText("₹ "+charge);
                }

            }
            if (Discount!=null&&charge!=null){
                int int3 = Integer.parseInt(Discount);
                int int4 = Integer.parseInt(totals);
                int int5 = Integer.parseInt(charge);
                int total_amount=int4-int3+int5;
                total_amounts=String.valueOf(total_amount);
                binding.totalprice.setText("₹ "+total_amounts);


            }



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

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        // Get day of the week as a string (e.g., "Monday")
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        String dayOfWeek = dayFormat.format(calendar.getTime());
        // Get month as a string (e.g., "March")
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
        String month = monthFormat.format(calendar.getTime());
        // Get day of the month
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//        // Get year
//        int year = calendar.get(Calendar.YEAR);

        // Create a string to display in the TextView
        String currentDate = "  Delivery by "+dayOfMonth + ", " + month + " " + dayOfWeek  ;
        binding.deliverdate.setText(currentDate);


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