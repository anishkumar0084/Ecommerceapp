package com.ecommericeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.ecommericeapp.Data.detaiproduct;
import com.ecommericeapp.OrderSummary;
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

public class detailAdapter extends RecyclerView.Adapter<detailAdapter.ViewHolderd> {
    Context context;
    List<detaiproduct> productDetails;

    public detailAdapter(Context context,List<detaiproduct> productDetails) {
        this.context=context;
        this.productDetails=productDetails;
    }

    @NonNull
    @Override
    public detailAdapter.ViewHolderd onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.productdetail1,parent,false);
        ViewHolderd viewHolderd=new ViewHolderd(view);
        return viewHolderd;
    }

    @Override
    public void onBindViewHolder(@NonNull detailAdapter.ViewHolderd holder, int position) {
        detaiproduct productDetail=productDetails.get(position);

        holder.title1.setText(productDetail.getTitle());
        holder.price2.setText(productDetail.getPrice());
        List<SlideModel> imageList = new ArrayList<>();


        imageList.add(new SlideModel(productDetail.getImage1(), null));
        imageList.add(new SlideModel(productDetail.getImage2(), null));
        imageList.add(new SlideModel(productDetail.getImage3(), null));
        imageList.add(new SlideModel(productDetail.getImage4(), null));



//        imageList.add(new SlideModel(productDetail.getImage1()));






        holder.imageSlider.setImageList(imageList);
        holder.imageSlider.stopSliding();



//        Glide.with(context)
//                .load(productDetail.getSrt_image())
//                .into(holder.imageView);

        holder.Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(context, OrderSummary.class);
                intent1.putExtra("ans", productDetail.getSrt_image())
                        .putExtra("price", productDetail.getPrice())
                        .putExtra("title", productDetail.getTitle())
                        . putExtra("sizes", productDetail.getSize())
//                        .putExtra("quantity",)
                        .putExtra("charge",productDetail.getDelivery_charge())
                        .putExtra("offer",productDetail.getOffer())
                        .putExtra("sht_d",productDetail.getSrt_desc())
//                        .putExtra("size",)
                        .putExtra("Discount",productDetail.getDiscount())





                ;


                context.startActivity(intent1);



            }
        });
       holder.Cart.setOnClickListener(new View.OnClickListener() {
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
                    cartRef.orderByChild("name").equalTo(productDetail.getTitle()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // Product already exists in the cart, handle accordingly (e.g., show a message)
                                Toast.makeText(context, "Product already exists in the cart", Toast.LENGTH_SHORT).show();
                            } else {
                                // Define product details as strings
                                String productName = productDetail.getTitle();
                                String productPrice =productDetail.getPrice();
                                String productImageUrl =productDetail.getSrt_image();

                                // Product doesn't exist in the cart, add it
                                DatabaseReference newProductRef = cartRef.push();
                                newProductRef.child("name").setValue(productName);
                                newProductRef.child("price").setValue(productPrice);
                                newProductRef.child("imageUrl").setValue(productImageUrl);
                                Toast.makeText(context, "Product Add Successful in Your cart", Toast.LENGTH_SHORT).show();

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






    }



    @Override
    public int getItemCount() {
        return 1;
    }
    public class ViewHolderd extends RecyclerView.ViewHolder {
        ImageView imageView;
        Spinner quantity,size;

        Button Cart,Buy;
        String url,price,title,Discount,charge,offer,sizek,sht_d;
        String sizes;
        String quantitys;
        TextView title1,price2;
        ImageSlider imageSlider;

        public ViewHolderd(@NonNull View itemView) {
            super(itemView);
            title1=itemView.findViewById(R.id.productName);
             price2=itemView.findViewById(R.id.price4);
            imageView=itemView.findViewById(R.id.product_image);
            Cart=itemView.findViewById(R.id.add_to_cart);
            Buy=itemView.findViewById(R.id.buy_now);
            imageSlider=itemView.findViewById(R.id.image_slider);

//            quantity=itemView.findViewById(R.id.product_quantity_spinner);
//            size=itemView.findViewById(R.id.product_size_spinner);



        }
    }
}
