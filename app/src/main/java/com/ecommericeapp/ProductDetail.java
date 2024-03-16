package com.ecommericeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ecommericeapp.Adapter.detailAdapter;
import com.ecommericeapp.Data.detaiproduct;
import com.ecommericeapp.Data.productDetail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductDetail extends AppCompatActivity {
    ImageView imageView;
    Spinner quantity,size;

    Button Cart,Buy;
    String url,price,title,Discount,charge,offer,sizek,sht_d,id,image1,image2,image3,image4;
    String sizes;
    String quantitys;
    TextView title1,price2;
    List<detaiproduct> detaiproduct=new ArrayList<>();
    detailAdapter detailAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        RecyclerView recyclerView=findViewById(R.id.productdetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));






        Intent intent = getIntent();
        if (intent != null) {
             url = intent.getStringExtra("ans");
            price = intent.getStringExtra("price");
            title = intent.getStringExtra("title");
            offer = intent.getStringExtra("offer");
            charge = intent.getStringExtra("charge");
            sizek = intent.getStringExtra("size");
            sht_d= intent.getStringExtra("sht_d");
            Discount= intent.getStringExtra("Discount");
            id= intent.getStringExtra("id");
            image1= intent.getStringExtra("image1");
            image2= intent.getStringExtra("image2");
            image3= intent.getStringExtra("image3");
            image4= intent.getStringExtra("image4");

            detaiproduct.add(new detaiproduct(id,title,price,url,image1,image2,image3,image4,Discount,charge,offer,sht_d,sizek));
            detailAdapter=new detailAdapter(this,detaiproduct);


            recyclerView.setAdapter(detailAdapter);

            detailAdapter.notifyDataSetChanged();









            // Do something with productId
        }















//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.Number, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        quantity.setAdapter(adapter);
//
//        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
//                R.array.Size, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        size.setAdapter(adapter1);
//        quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                String selectedValue = parentView.getItemAtPosition(position).toString();
//
//                if (selectedValue.equals("1")) {
//                    quantitys="1";
//
//                } else if (selectedValue.equals("2")) {
//                    quantitys="2";
//
//                } else if (selectedValue.equals("3")) {
//                    quantitys="3";
//
//                }else  {
//
//
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // Do nothing if nothing is selected
//            }
//        });
//        size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                String selectedValue = parentView.getItemAtPosition(position).toString();
//                // Perform tasks based on the selected value
//                if (selectedValue.equals("M")) {
//                    sizes="M";
//
//                } else if (selectedValue.equals("X")) {
//                    sizes="X";
//
//                } else if (selectedValue.equals("L")) {
//                    sizes="L";
//                }else {
//                    sizes="XL";
//
//
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // Do nothing if nothing is selected
//            }
//        });

    }


}