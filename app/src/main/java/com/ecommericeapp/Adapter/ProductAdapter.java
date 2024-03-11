package com.ecommericeapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ecommericeapp.Data.cartdata;
import com.ecommericeapp.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<cartdata> productList;

    public ProductAdapter(List<cartdata> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_xml, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        cartdata product = productList.get(position);
        holder.textViewProductName.setText(product.getTitle());
        holder.textViewProductPrice.setText(String.valueOf(product.getPrice()));
        Glide.with(holder.itemView).load(product.getImage()).fitCenter().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductName;
        TextView textViewProductPrice;
        ImageView imageView;

        ProductViewHolder(View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.title);
            textViewProductPrice = itemView.findViewById(R.id.price);
            imageView=itemView.findViewById(R.id.productimagek);
        }
    }
}

