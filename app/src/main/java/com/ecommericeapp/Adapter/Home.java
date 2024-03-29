package com.ecommericeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.ecommericeapp.Clicklistner;
import com.ecommericeapp.Data.SliderData;
import com.ecommericeapp.Data.productDetail;
import com.ecommericeapp.R;
import com.ecommericeapp.Searchproduct;

import java.util.ArrayList;
import java.util.List;

public class Home  extends RecyclerView.Adapter<Home.viewholder> {


    Context context;
    productDetail data;
    ArrayList<productDetail> productDetail;
    private Clicklistner clicklistner;
     ArrayList<productDetail> filteredProductList;

    public Home(Context context , ArrayList<productDetail> productdetail,Clicklistner clicklistner) {
        this.context=context;
        this.productDetail=productdetail;
        this.filteredProductList = new ArrayList<productDetail>(productdetail);
        this.clicklistner=clicklistner;

    }
    public void filterList(ArrayList<productDetail> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        productDetail = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(context).inflate(R.layout.prducts,parent,false);
        viewholder viewholder=new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
         data = productDetail.get(position);


        holder.prductname.setText(data.getTitle());
        holder.price.setText("₹ "+data.getPrice());




        Glide.with(holder.itemView)
                .load(data.getSrt_image())
                .fitCenter()
                .into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicklistner.onItemclick(productDetail.get(position));
            }
        });




    }

    @Override
    public int getItemCount() {
        return productDetail.size();
    }





    public class viewholder extends RecyclerView.ViewHolder{
        ImageView image;
        View itemView;
        TextView prductname,price;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.product_image);
            prductname=itemView.findViewById(R.id.product_title);
            price=itemView.findViewById(R.id.product_price);
            this.itemView = itemView;

        }

    }
}
