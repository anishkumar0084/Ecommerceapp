package com.ecommericeapp.ProductCategoryActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.ecommericeapp.Clicklistner;
import com.ecommericeapp.Data.SliderData;
import com.ecommericeapp.Data.productDetail;
import com.ecommericeapp.ProductDetail;
import com.ecommericeapp.R;
import com.ecommericeapp.Searchproduct;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Shirt extends AppCompatActivity implements Clicklistner {
    ArrayList<productDetail> productDetails=new ArrayList<>();
    RecyclerView recyclerView;
    DatabaseReference myRef1;
    com.ecommericeapp.Adapter.Home home;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shirt);
        SearchView searchView =findViewById(R.id.searchshirt);
        searchView.setQueryHint("Search...");
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Shirt.this, Searchproduct.class);
                startActivity(intent);


            }
        });
        recyclerView=findViewById(R.id.productshirt);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        home=new com.ecommericeapp.Adapter.Home(this,productDetails,this);

        myRef1 = FirebaseDatabase.getInstance().getReference().child("categories");

        String[] categoryNames = {"shirt"};

        // Fetch data from each category
        for (String categoryName : categoryNames) {
            DatabaseReference categoryRef = myRef1.child(categoryName);
            fetchDataFromNode(categoryName, categoryRef);
        }

        recyclerView.setAdapter(home);

    }
    private void fetchDataFromNode(String categoryName, DatabaseReference categoryRef) {
        categoryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Accessing properties like 'id', 'image', and 'title'
                    String id = snapshot.child("id").getValue(String.class);
                    String title = snapshot.child("title").getValue(String.class);
                    String price = snapshot.child("price").getValue(String.class);
                    String shrtimage = snapshot.child("srt_image").getValue(String.class);
                    String image1 = snapshot.child("image1").getValue(String.class);
                    String image2 = snapshot.child("image2").getValue(String.class);
                    String image3= snapshot.child("image3").getValue(String.class);
                    String image4= snapshot.child("image4").getValue(String.class);
                    String discount= snapshot.child("discount").getValue(String.class);
                    String deliverycharge= snapshot.child("Delivery_charge").getValue(String.class);
                    String shrtdesc= snapshot.child("srt_desc").getValue(String.class);
                    String offer= snapshot.child("offer").getValue(String.class);
                    String size= snapshot.child("size").getValue(String.class);
                    productDetails.add(new productDetail(id,title,price,shrtimage,image1,image2,image3,image4,discount,deliverycharge,offer,shrtdesc,size));

                }
                home.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    @Override
    public void onItemclick(productDetail productDetail) {
        Intent intent=new Intent(this, ProductDetail.class);
        intent.putExtra("ans",productDetail.getSrt_image());
        intent.putExtra("title",productDetail.getTitle());
        intent.putExtra("price",productDetail.getPrice());

        startActivity(intent);



    }
}