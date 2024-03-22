package com.ecommericeapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecommericeapp.Editprofile;
import com.ecommericeapp.OrderSummary;
import com.ecommericeapp.Orderhistory;
import com.ecommericeapp.R;
import com.ecommericeapp.databinding.FragmentAccountBinding;
import com.ecommericeapp.login_page;
import com.ecommericeapp.saved_address;
import com.google.firebase.auth.FirebaseAuth;

public class Account extends Fragment {

    private FragmentAccountBinding binding;


    public Account() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Intent intent=new Intent(getContext(), login_page.class);
                startActivity(intent);

            }
        });
        binding.savedAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), saved_address.class);
                startActivity(intent);

            }
        });
        binding.editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Editprofile.class);
                startActivity(intent);

            }
        });
        binding.orderid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Orderhistory.class);
                startActivity(intent);

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Release the binding instance
    }
}