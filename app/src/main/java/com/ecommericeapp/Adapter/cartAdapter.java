package com.ecommericeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ecommericeapp.Data.cartdata;
import com.ecommericeapp.OrderSummary;
import com.ecommericeapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.ProductViewHolder> {
    private List<cartdata> productList;
    Context context;
    String quantitys;

    public cartAdapter(List<cartdata> productList,Context context) {
        this.productList = productList;
        this.context=context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_xml, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        cartdata product = productList.get(position);
        holder.textViewProductName.setText(product.getTitle());
        holder.textViewProductPrice.setText("Discount ₹ "+String.valueOf(product.getDiscount()));
//        holder.quantity.setText("Qty "+product.getQuantitys());
        holder.shortd.setText("₹ "+product.getPrice());
        holder.offer.setText("Offer "+product.getOffer());
        Glide.with(holder.itemView).load(product.getImage()).fitCenter().into(holder.imageView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.Number, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.quantity.setAdapter(adapter);

        holder.quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedValue = parentView.getItemAtPosition(position).toString();


                if (selectedValue.equals("Qty 1")) {
                    quantitys="1";

                } else if (selectedValue.equals("Qty 2")) {
                    quantitys="2";

                } else if (selectedValue.equals("Qty 3")) {
                    quantitys="3";

                }else  {


                }
//                listener.onButtonClick(sizes,quantitys);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if nothing is selected
            }
        });


        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = holder.getAdapterPosition();
//

                Intent intent=new Intent(context, OrderSummary.class);
                intent.putExtra("ans", productList.get(itemPosition).getImage())
                        .putExtra("price",productList.get(itemPosition).getPrice())
                        .putExtra("title", productList.get(itemPosition).getTitle())
                        . putExtra("sizes", productList.get(itemPosition).getSizek())
                        .putExtra("quantity",quantitys)
                        .putExtra("charge",productList.get(itemPosition).getCharge())
                        .putExtra("offer",productList.get(itemPosition).getOffer())
                        .putExtra("sht_d",productList.get(itemPosition).getSht_d())
                        .putExtra("size",productList.get(itemPosition).getSizes())
                        .putExtra("Discount",productList.get(itemPosition).getDiscount());




                context.startActivity(intent);

            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid(); // Get the current user ID
                int itemPosition = holder.getAdapterPosition();
                String title = productList.get(itemPosition).getPrice();
//                Toast.makeText(view.getContext(), "ans"+title,Toast.LENGTH_SHORT).show();


                DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("cart");

                cartRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot cartSnapshot : dataSnapshot.getChildren()) {
                            String productName = cartSnapshot.child("price").getValue(String.class);
                            if (productName.equals(title)) {
                                cartSnapshot.getRef().removeValue();
                                productList.remove(itemPosition);
                                notifyItemRemoved(itemPosition);

                                break;

                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });



            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductName;
        TextView textViewProductPrice,offer,shortd;
        ImageView imageView;

        Button remove,buy;
        Spinner quantity;


        ProductViewHolder(View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.title);
            textViewProductPrice = itemView.findViewById(R.id.price);
            quantity = itemView.findViewById(R.id.quantityg);
            offer= itemView.findViewById(R.id.offerg);
            shortd= itemView.findViewById(R.id.shortdes);
            imageView=itemView.findViewById(R.id.productimagek);
            remove=itemView.findViewById(R.id.remove);
            buy=itemView.findViewById(R.id.buy1);
        }
    }
}

