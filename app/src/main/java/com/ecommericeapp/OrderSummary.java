package com.ecommericeapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ecommericeapp.Data.orderDetail;
import com.ecommericeapp.databinding.ActivityOrderSummaryBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class OrderSummary extends AppCompatActivity implements PaymentResultListener  {

    ImageView imageView;
    private ActivityOrderSummaryBinding binding;
    private RelativeLayout parentLayout;

    String url,price,title,Discount,charge,offer,sizek,sht_d;

    String name,address_type,state,city,pin_code,house_no,road_name,phone;
    String sizes,quantity,totals,total_amounts,currentDate,orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityOrderSummaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UniqueOrderIdGenerator orderIdGenerator = new UniqueOrderIdGenerator();
        orderId = orderIdGenerator.generateOrderId();
        String ans="5";





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



            if (sizes.equals("No")){
                binding.quantity.setText("Qty:"+quantity);

            }else {
                binding.quantity.setText("sizes:"+sizek);


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
                inflateLayout();







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
        binding.underOrderSummary.setVisibility(View.INVISIBLE);
//        // Get year
//        int year = calendar.get(Calendar.YEAR);

        // Create a string to display in the TextView
        currentDate = "  Delivery by "+dayOfMonth + ", " + month + " " + dayOfWeek  ;
        if (ans.equals(intent.getStringExtra("order"))){

            binding.continuef.setVisibility(View.INVISIBLE);
            binding.order.setText("Order Summary ");
            binding.deliverdate.setText(intent.getStringExtra("date"));
            binding.save3.setVisibility(View.INVISIBLE);
            binding.adresschange.setVisibility(View.INVISIBLE);
            binding.underOrderSummary.setVisibility(View.VISIBLE);


            binding.gh.setText("Payment Method-"+intent.getStringExtra("payment"));
            binding.gh.setTextSize(30);
            binding.underOrderSummary.setText("order id="+intent.getStringExtra("orderid"));





        }else {

            binding.deliverdate.setText(currentDate);

        }





    }
    private void Order_detatil(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        String paymentmethod="Cash On Delivery";
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH, 7);
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
        String currentDate1 = "Order date -"+dayOfMonth + ", " + month + " " + dayOfWeek  ;

        if (currentUser != null) {
            String userId = currentUser.getUid();

            DatabaseReference cartRef = databaseReference.child("users").child(userId).child("order").child(orderId);

// Check if the product already exists in the cart
            cartRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    orderDetail orderDetail=new orderDetail(currentDate1,title,sizes,orderId,price,Discount,total_amounts,paymentmethod,url,currentDate,quantity,charge,offer,sht_d,sizek);

                    cartRef.setValue(orderDetail);



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle errors
                }
            });



        }


    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();




    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();


    }

    public class UniqueOrderIdGenerator {
        private Random RANDOM = new Random();

        public  String generateOrderId() {
            long timestamp = System.currentTimeMillis();
            int random = RANDOM.nextInt(10000); // Adjust as needed for desired length

            return String.format("%d-%04d", timestamp, random);
        }

        public void main(String[] args) {
             orderId = generateOrderId();
        }
    }
    private void inflateLayout() {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.paymentlayout);
        dialog.show();

        Button button=dialog.findViewById(R.id.btnCashOnDelivery);
        Button online=dialog.findViewById(R.id.btnOnlinePay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order_detatil();
                button.setBackgroundColor(getResources().getColor(R.color.white));
                online.setBackgroundColor(getResources().getColor(R.color.black));

            }
        });
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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




//        Button tvInflated = inflatedLayout.findViewById(R.btn);
        // You can modify TextView or other views as needed here

    }

}