package com.ecommericeapp.Adapter;

import android.content.Context;
import android.icu.text.CaseMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ecommericeapp.Clicklistner;
import com.ecommericeapp.Data.orderDetail;
import com.ecommericeapp.R;
import com.ecommericeapp.orderdetail;

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
