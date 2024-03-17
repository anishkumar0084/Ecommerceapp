package com.ecommericeapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecommericeapp.Adapter.cartAdapter;
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
                    String productprice = productSnapshot.child("price").getValue(String.class);
                    String producttitle = productSnapshot.child("title").getValue(String.class);
                    String producttitleimage = productSnapshot.child("image").getValue(String.class);
                    String imageUrlsizek = productSnapshot.child("sizek").getValue(String.class);
                    String imageUrlquanti = productSnapshot.child("quantitys").getValue(String.class);
                    String imageUrlcharge = productSnapshot.child("charge").getValue(String.class);
                    String imageUrloffer = productSnapshot.child("offer").getValue(String.class);
                    String imageUrlsht_d= productSnapshot.child("sht_d").getValue(String.class);
                    String imageUrlsizes = productSnapshot.child("sizes").getValue(String.class);
                    String imageUrldiscount = productSnapshot.child("discount").getValue(String.class);
                    // Get other product details if needed

                        productList.add(new cartdata(productprice,producttitle,producttitleimage,imageUrlsizek,imageUrlquanti,imageUrlcharge,imageUrloffer,imageUrlsht_d,imageUrlsizes,imageUrldiscount));
                }

                RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                cartAdapter productAdapter = new cartAdapter(productList,getContext());


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
