package com.ecommericeapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecommericeapp.Adapter.CategoryAdapter;
import com.ecommericeapp.Data.Categorydata;
import com.ecommericeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class categories extends Fragment {



    public categories() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view =inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerView=view.findViewById(R.id.categoryrecyl);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference().child("categoryimage");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Categorydata> categorydata1=new ArrayList<>();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String images1=dataSnapshot.child("images").getValue(String.class);
                    String name=dataSnapshot.child("name").getValue(String.class);
                    categorydata1.add(new Categorydata(images1,name));

                }
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                CategoryAdapter categoryAdapter=new CategoryAdapter(getContext(),categorydata1);
                recyclerView.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}