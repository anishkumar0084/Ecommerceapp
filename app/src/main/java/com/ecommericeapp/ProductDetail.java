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
import com.ecommericeapp.Data.cartdata;
import com.ecommericeapp.Data.detaiproduct;
import com.ecommericeapp.Data.productDetail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.PaymentResultWithDataListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class ProductDetail extends AppCompatActivity implements com.ecommericeapp.Adapter.detailAdapter.OnItemClickListener {
    ImageView imageView;
    Spinner quantity,size;

    Button Cart,Buy;
    String url,price,title,Discount,charge,offer,sizek,sht_d,id,image1,image2,image3,image4,rating,comments;
    String sizes;
    String quantitys;
    TextView title1,price2;
    List<detaiproduct> detaiproduct=new ArrayList<>();
    detailAdapter detailAdapter;
    String totalRatingk,average;
    RecyclerView recyclerView;
    StringBuilder usernamesAndComments;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

         recyclerView=findViewById(R.id.productdetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cart=findViewById(R.id.add_to_cart);
        Buy=findViewById(R.id.buy_now);








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













            // Do something with productId
        }

        DatabaseReference productRef = FirebaseDatabase.getInstance().getReference("products").child(id);
        productRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Product reference exists, fetch product details
                    DatabaseReference ratingsRef = productRef.child("ratings");
                    ratingsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {

                            }
                            int totalComments = (int) dataSnapshot.getChildrenCount(); // Total number of comments to fetch
                            final int[] processedComments = {0}; // Counter for processed comments

                            // Fetch ratings and comments for the product
                                float totalRating = 0;
                                int ratingCount = 0;

                                for (DataSnapshot ratingSnapshot : dataSnapshot.getChildren()) {
                                    float ratings = ratingSnapshot.child("rating").getValue(Float.class);
                                    rating = String.valueOf(ratings);

                                    totalRating += ratings;
                                    usernamesAndComments = new StringBuilder();


                                    String userId = ratingSnapshot.getKey();

                                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId).child("name");
                                    float finalTotalRating = totalRating;
                                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot userDataSnapshot) {
                                            String username = userDataSnapshot.getValue(String.class);


                                            String comment = ratingSnapshot.child("comment").getValue(String.class);



                                            usernamesAndComments.append("Name: ").append(username).append("\n");
                                            usernamesAndComments.append("Comment: ").append(comment).append("\n\n");

                                            processedComments[0]++;

                                            // Check if all comments have been processed
                                            if (processedComments[0] == totalComments) {
                                                // All comments have been fetched, proceed with calculating average and adding product details to the adapter
                                                if (processedComments[0] > 0) {
                                                    comments = usernamesAndComments.toString();
                                                    float averageRating = finalTotalRating / processedComments[0];
                                                    totalRatingk = String.valueOf(processedComments[0]);
                                                    average = String.valueOf(averageRating);
                                                } else {
                                                    // No ratings found, set default values
                                                    rating = "N/A";
                                                    comments = "No comments";
                                                    totalRatingk = "0";
                                                    average = "0.0";
                                                }

                                                // Add product details to adapter
                                                addProductDetailsToAdapter();
                                            }
                                        }








                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            // Handle error
                                        }
                                    });

                                    ratingCount++;

                                }




                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle error
                        }
                    });
                } else {
                    addProductDetailsToAdapter();
                    // Product reference does not exist, handle this scenario
                    //                    Toast.makeText(ProductDetail.this, "Product not found", Toast.LENGTH_SHORT).show();
                    //                    // You might want to finish the activity or display a message to the user
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });






        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ProductDetail.this, OrderSummary.class);
                intent1.putExtra("ans", url)
                        .putExtra("price",price)
                        .putExtra("title", title)
                        . putExtra("sizes", sizek)
                        .putExtra("quantity",quantitys)
                        .putExtra("charge",charge)
                        .putExtra("offer",offer)
                        .putExtra("sht_d",sht_d)
                        .putExtra("size",sizes)
                        .putExtra("Discount",Discount)
                        .putExtra("id",id)






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

                    DatabaseReference cartRef = databaseReference.child("users").child(userId).child("cart").child(id);

// Check if the product already exists in the cart
                    cartRef.orderByChild("name").equalTo(title).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // Product already exists in the cart, handle accordingly (e.g., show a message)
                                Toast.makeText(ProductDetail.this, "Product already exists in the cart", Toast.LENGTH_SHORT).show();
                            } else {

                                cartdata cartdata=new cartdata(price,title,url,sizek,quantitys,charge,offer,sht_d,sizes,Discount);
                                cartRef.setValue(cartdata);
                                Toast.makeText(ProductDetail.this, "Product Add Successful in Your cart", Toast.LENGTH_SHORT).show();

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






















    }
    private void addProductDetailsToAdapter() {


        detaiproduct.add(new detaiproduct(id,title,price,url,image1,image2,image3,image4,Discount,charge,offer,sht_d,sizek,rating,comments,totalRatingk,average));
        detailAdapter=new detailAdapter(this,detaiproduct,this);
        recyclerView.setAdapter(detailAdapter);

        detailAdapter.notifyDataSetChanged();



    }


    @Override
    public void onButtonClick(String size, String quantity) {

        sizes=size;
        quantitys=quantity;



    }
}