package com.ecommericeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductDetail extends AppCompatActivity {
    ImageView imageView;
    Spinner quantity,size;

    Button Cart,Buy;
    String url,price,title;
    String sizes;
    String quantitys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        TextView title1=findViewById(R.id.product_title);
        TextView price2=findViewById(R.id.product_price);

        imageView=findViewById(R.id.product_image);
        Intent intent = getIntent();
        if (intent != null) {
             url = intent.getStringExtra("ans");
            price = intent.getStringExtra("price");
            title = intent.getStringExtra("title");
            title1.setText(title);
            price2.setText(price);
            Glide.with(this)
                    .load(url)
                    .into(imageView);


            // Do something with productId
        }

        Cart=findViewById(R.id.add_to_cart_button);
        Buy=findViewById(R.id.buy_now_button);
        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ProductDetail.this, OrderSummary.class);
                intent1.putExtra("ans", url)
                        .putExtra("price", price)
                        .putExtra("title", title)
                         . putExtra("sizes", sizes)
                        .putExtra("quantity",quantitys)





                ;


                startActivity(intent1);



            }
        });
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();

// Authenticate the user (Firebase Authentication)
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();

                if (currentUser != null) {
                    String userId = currentUser.getUid();

                    DatabaseReference cartRef = databaseReference.child("users").child(userId).child("cart");

// Check if the product already exists in the cart
                    cartRef.orderByChild("name").equalTo(title).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // Product already exists in the cart, handle accordingly (e.g., show a message)
                                Toast.makeText(getApplicationContext(), "Product already exists in the cart", Toast.LENGTH_SHORT).show();
                            } else {
                                // Define product details as strings
                                String productName =title;
                                String productPrice =price;
                                String productImageUrl =url;

                                // Product doesn't exist in the cart, add it
                                DatabaseReference newProductRef = cartRef.push();
                                newProductRef.child("name").setValue(productName);
                                newProductRef.child("price").setValue(productPrice);
                                newProductRef.child("imageUrl").setValue(productImageUrl);
                                // Add other product details as needed
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle errors
                        }
                    });



                }






            }
        });




        quantity=findViewById(R.id.product_quantity_spinner);
        size=findViewById(R.id.product_size_spinner);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Number, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantity.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Size, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        size.setAdapter(adapter1);
        quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedValue = parentView.getItemAtPosition(position).toString();

                if (selectedValue.equals("1")) {
                    quantitys="1";

                } else if (selectedValue.equals("2")) {
                    quantitys="2";

                } else if (selectedValue.equals("3")) {
                    quantitys="3";

                }else  {


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if nothing is selected
            }
        });
        size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedValue = parentView.getItemAtPosition(position).toString();
                // Perform tasks based on the selected value
                if (selectedValue.equals("M")) {
                    sizes="M";

                } else if (selectedValue.equals("X")) {
                    sizes="X";

                } else if (selectedValue.equals("L")) {
                    sizes="L";
                }else {
                    sizes="XL";


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if nothing is selected
            }
        });

    }


}