package com.ecommericeapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ecommericeapp.Data.Categorydata;
import com.ecommericeapp.R;
import com.ecommericeapp.categorylisner;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {
    Context context;
    List<Categorydata> categorydata;
    private categorylisner categorylisner;
    public CategoryAdapter(Context context,List<Categorydata> categorydata,categorylisner categorylisner) {
        this.context=context;
        this.categorydata=categorydata;
        this.categorylisner= categorylisner;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.category,parent,false);
        viewholder viewholder=new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewholder holder, int position) {

        Categorydata categorydata1=categorydata.get(position);
        holder.textView.setText(categorydata1.getName());

        Glide.with(holder.itemView)
                .load(categorydata1.getImage())
                .fitCenter()
                .into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categorylisner.onItemclick(categorydata.get(position));

            }
        });


    }

    @Override
    public int getItemCount() {
        return categorydata.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        CardView cardView;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
            imageView=itemView.findViewById(R.id.imageView);
            cardView=itemView.findViewById(R.id.cardView);

        }
    }
}
