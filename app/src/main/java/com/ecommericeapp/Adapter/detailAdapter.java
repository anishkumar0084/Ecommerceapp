package com.ecommericeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
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
    String sizes;
    String quantitys;
    private OnItemClickListener listener;


    public detailAdapter(Context context,List<detaiproduct> productDetails,OnItemClickListener listener) {
        this.context=context;
        this.productDetails=productDetails;
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onButtonClick(String size, String quantity);
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

//        if (productDetail.getSize().equals("No")){
//            holder.size.setVisibility(View.INVISIBLE);
//            holder.quantity.setGravity(Gravity.CENTER);
//
//        }



//        imageList.add(new SlideModel(productDetail.getImage1()));






        holder.imageSlider.setImageList(imageList);
        holder.imageSlider.stopSliding();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.Number, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.quantity.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(context,
                R.array.Size, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       holder. size.setAdapter(adapter1);
        holder.quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                listener.onButtonClick(sizes,quantitys);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if nothing is selected
            }
        });
       holder. size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                listener.onButtonClick(sizes,quantitys);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if nothing is selected
            }
        });




//        Glide.with(context)
//                .load(productDetail.getSrt_image())
//                .into(holder.imageView);






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

            imageSlider=itemView.findViewById(R.id.image_slider);
            quantity=itemView.findViewById(R.id.product_quantity_spinner);
            size=itemView.findViewById(R.id.product_size_spinner);




//


        }
    }
}
