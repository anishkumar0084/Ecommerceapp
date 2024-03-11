package com.ecommericeapp.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommericeapp.Adapter.ProductAdapter;
import com.ecommericeapp.Data.cartdata;
import com.ecommericeapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Cart extends Fragment {


    public Cart() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        assert currentUser != null;
        String userId = currentUser.getUid();
        DatabaseReference cartRef = firebaseDatabase.getReference().child("users").child(userId).child("cart");

        cartRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<cartdata> productList = new ArrayList<>();
                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                    String productName = productSnapshot.child("name").getValue(String.class);
                    String productPrice = productSnapshot.child("price").getValue(String.class);
                    String imageUrl = productSnapshot.child("imageUrl").getValue(String.class);
                    // Get other product details if needed
                    if (productPrice != null) {

                        productList.add(new cartdata(productName, productPrice,imageUrl));
                    }
                }

                RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                ProductAdapter productAdapter = new ProductAdapter(productList);


                recyclerView.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });


        // Initialize the BroadcastReceiver


        return view;
    }


    }
