package com.ecommericeapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ecommericeapp.Adapter.OrderHistory;
import com.ecommericeapp.Adapter.cartAdapter;
import com.ecommericeapp.Data.cartdata;
import com.ecommericeapp.Data.orderDetail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Orderhistory extends AppCompatActivity {
    List<orderDetail> orderDetailList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory);


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        assert currentUser != null;
        String userId = currentUser.getUid();

        DatabaseReference cartRef = firebaseDatabase.getReference().child("users").child(userId).child("order");

        cartRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                    String productName = productSnapshot.child("date").getValue(String.class);
                    String productPrice = productSnapshot.child("title").getValue(String.class);
                    String size = productSnapshot.child("size").getValue(String.class);
                    String productPriceorder = productSnapshot.child("order_id").getValue(String.class);
                    String productPriceprice = productSnapshot.child("price").getValue(String.class);
                    String productPricediscount= productSnapshot.child("discount").getValue(String.class);
                    String productPricetotal= productSnapshot.child("total_amount").getValue(String.class);
                    String productPricepayment= productSnapshot.child("payment_method").getValue(String.class);
//                    String imageUrl = productSnapshot.child("imageUrl").getValue(String.class);
                    // Get other product details if needed
                    if (productPrice != null) {
                        orderDetailList.add(new orderDetail(productName,productPrice,size,productPriceorder,productPriceprice,productPricediscount,productPricetotal,productPricepayment) );


                    }
                }

                RecyclerView recyclerView = findViewById(R.id.orderhistro);
                recyclerView.setLayoutManager(new LinearLayoutManager(Orderhistory.this));
                OrderHistory orderHistory=new OrderHistory(Orderhistory.this,orderDetailList);



                recyclerView.setAdapter(orderHistory);
                orderHistory.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });


    }
}