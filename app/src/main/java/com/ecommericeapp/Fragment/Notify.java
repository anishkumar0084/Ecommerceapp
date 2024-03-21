package com.ecommericeapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ecommericeapp.R;
import com.ecommericeapp.databinding.FragmentNotifyBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Notify extends Fragment {

    public Notify() {
        // Required empty public constructor
    }
    FragmentNotifyBinding fragmentNotifyBinding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentNotifyBinding = FragmentNotifyBinding.inflate(inflater, container, false);
        View view = fragmentNotifyBinding.getRoot();

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

        databaseReference.child("offers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot offerSnapshot : dataSnapshot.getChildren()) {
                        String imageUrl = offerSnapshot.child("imageUrl").getValue(String.class);
                        String name = offerSnapshot.child("name").getValue(String.class);
                        String offerText = offerSnapshot.child("offer").getValue(String.class);

                        // Check if imageUrl is not null
                        if (imageUrl != null) {
                            // Use Glide to load the image into ImageView
                            Glide.with(requireContext())
                                    .load(imageUrl)
                                    .placeholder(R.drawable.coupons_image) // Placeholder image while loading
                                    .error(R.drawable.wishlist_image) // Error image if loading fails
                                    .into(fragmentNotifyBinding.imageProduct);
                        } else {
                            // Handle case where imageUrl is null
                            // For example, show a default image or hide the ImageView
                            fragmentNotifyBinding.imageProduct.setImageResource(R.drawable.customercare_img);
                        }

                        // Set name and offer text
                        fragmentNotifyBinding.textProductName.setText(name);
                        fragmentNotifyBinding.textDiscount.setText(offerText);
                    }
                } else {
                    // Handle case where no offer data exists
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });



        return view;

    }


}