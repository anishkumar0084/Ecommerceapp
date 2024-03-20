package com.ecommericeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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

public class Orderhistory extends AppCompatActivity implements orderdetail{
    List<orderDetail> orderDetailList=new ArrayList<>();
    OrderHistory orderHistory;

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
                    String productPricepaymentimage= productSnapshot.child("image").getValue(String.class);
                    String productPricepaymentcurrent= productSnapshot.child("currentdate").getValue(String.class);
                    String productPricepaymentcurrentq= productSnapshot.child("quantity").getValue(String.class);
                    String productPricepaymentcurrentch= productSnapshot.child("charge").getValue(String.class);
                    String productPricepaymentcurrentoff= productSnapshot.child("offer").getValue(String.class);
                    String productPricepaymentcurrentsht= productSnapshot.child("sht_d").getValue(String.class);
                    String productPricepaymentcurrentsizes= productSnapshot.child("sizes").getValue(String.class);
                    String productPricepaymentcurrentid= productSnapshot.child("id").getValue(String.class);
//                    String imageUrl = productSnapshot.child("imageUrl").getValue(String.class);
                    // Get other product details if needed
                    if (productPrice != null) {
                        orderDetailList.add(new orderDetail(productName,productPrice,size,productPriceorder,productPriceprice,productPricediscount,productPricetotal,
                                productPricepayment,productPricepaymentimage,
                                productPricepaymentcurrent,productPricepaymentcurrentq,productPricepaymentcurrentch,productPricepaymentcurrentoff,productPricepaymentcurrentsht,productPricepaymentcurrentsizes,productPricepaymentcurrentid) );


                    }
                }

                RecyclerView recyclerView = findViewById(R.id.orderhistro);
                recyclerView.setLayoutManager(new LinearLayoutManager(Orderhistory.this));



                recyclerView.setAdapter(orderHistory);
                orderHistory.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
        orderHistory=new OrderHistory(Orderhistory.this,orderDetailList,this);



    }


    @Override
    public void onItemclick(orderDetail orderdetail) {
        Intent intent=new Intent(Orderhistory.this,OrderSummary.class);
        String ans="5";
        intent.putExtra("ans", orderdetail.getImage())
                .putExtra("price",orderdetail.getPrice())
                .putExtra("title", orderdetail.getTitle())
                . putExtra("sizes", orderdetail.getSize())
                .putExtra("quantity",orderdetail.getQuantity())
                .putExtra("charge",orderdetail.getCharge())
                .putExtra("sht_d",orderdetail.getSht_d())
                .putExtra("size",orderdetail.getSizes())
                .putExtra("Discount",orderdetail.getDiscount())
                        .putExtra("order",ans)
                        .putExtra("date",orderdetail.getDate())
                        .putExtra("payment",orderdetail.getPayment_method())
                        .putExtra("orderid",orderdetail.getOrder_id())
        ;



        startActivity(intent);

    }
}