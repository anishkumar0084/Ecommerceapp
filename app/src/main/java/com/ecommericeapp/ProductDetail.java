package com.ecommericeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ProductDetail extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        imageView=findViewById(R.id.product_image);
        Intent intent = getIntent();
        if (intent != null) {
            String productId = intent.getStringExtra("ans");
            Glide.with(this)
                    .load(productId)
                    .into(imageView);


            // Do something with productId
        }
    }
}