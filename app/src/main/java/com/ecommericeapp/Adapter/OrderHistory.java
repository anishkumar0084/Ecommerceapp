package com.ecommericeapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.icu.text.CaseMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ecommericeapp.Clicklistner;
import com.ecommericeapp.Data.orderDetail;
import com.ecommericeapp.R;
import com.ecommericeapp.orderdetail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class OrderHistory extends RecyclerView.Adapter<OrderHistory.ViewHolder> {
    Context context;
    List<orderDetail> orderDetailList;
    orderdetail orderdetailk;

    public OrderHistory(Context context,List<orderDetail> orderDetailList,orderdetail orderdetail) {
        this.context=context;
        this.orderDetailList=orderDetailList;
        this.orderdetailk=orderdetail;
    }

    @NonNull
    @Override
    public OrderHistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.orderhistory,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistory.ViewHolder holder, int position) {
        orderDetail orderDetail=orderDetailList.get(position);

        holder.title.setText(orderDetail.getTitle());
        holder.date.setText(orderDetail.getCurrentdate());
        Glide.with(holder.itemView)
                .load(orderDetail.getImage())
                .fitCenter()
                .into(holder.imageview);

        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    Dialog dialog=new Dialog(context);
                    dialog.setContentView(R.layout.orderrating);
                    dialog.show();
                    TextView textView=dialog.findViewById(R.id.productName12);
                    TextView textView2=dialog.findViewById(R.id.productPrice12);
                    TextView textView3=dialog.findViewById(R.id.productDescription12);

                    textView.setText(orderDetail.getTitle());
                    textView2.setText(orderDetail.getPrice());
                    textView3.setText(orderDetail.getSht_d());

                    RatingBar ratingBars = dialog.findViewById(R.id.productRatingBar);
                    EditText commentEditText = dialog.findViewById(R.id.commentEditText);
                    Button submitButton = dialog.findViewById(R.id.submitBtn);

                    submitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            float rating = ratingBars.getRating();
                            String comment = commentEditText.getText().toString();
                            FirebaseAuth mAuth = FirebaseAuth.getInstance();
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            String userId = currentUser.getUid(); // Get user ID

                            // Get rating and comment

                            // Save data to Firebase Realtime Database
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference productRef = database.getReference("products").child(orderDetail.getId()); // Replace "productId" with the actual product ID

                            // Create a new entry in the database under the product ID
                            DatabaseReference ratingsRef = productRef.child("ratings").child(userId); // Store ratings under user's ID
                            ratingsRef.child("rating").setValue(rating);
                            ratingsRef.child("comment").setValue(comment);
                            dialog.dismiss();
                            Toast.makeText(context,"\uD83D\uDC4D Your rating means a lot to us. Thank you! \uD83D\uDE0A",Toast.LENGTH_LONG).show();

                            // Now you can use the rating and comment as needed, such as sending them to a server or storing them locally.
                        }
                    });
                }
            }
        });



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderdetailk.onItemclick(orderDetail);

            }
        });

    }

    @Override
    public int getItemCount() {
        return orderDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview;
        TextView date;
        TextView title;
        RatingBar ratingBar;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageview=itemView.findViewById(R.id.productimage2);
            date=itemView.findViewById(R.id.date);
            title=itemView.findViewById(R.id.title);
            ratingBar=itemView.findViewById(R.id.product_ratingbar1);
            cardView=itemView.findViewById(R.id.cardsk);
        }
    }
}
