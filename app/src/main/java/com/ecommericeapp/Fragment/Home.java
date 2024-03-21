package com.ecommericeapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.ecommericeapp.Clicklistner;
import com.ecommericeapp.Data.SliderData;
import com.ecommericeapp.Data.productDetail;
import com.ecommericeapp.ProductDetail;
import com.ecommericeapp.R;
import com.ecommericeapp.Searchproduct;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Home extends Fragment implements Clicklistner {



    public Home() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    ArrayList<productDetail> productDetails=new ArrayList<>();
    RecyclerView recyclerView;
    DatabaseReference myRef1;
    com.ecommericeapp.Adapter.Home home;
    SliderData mSliderItems;
    ImageSlider imageSlider;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        SearchView searchView = rootView.findViewById(R.id.search);
        searchView.setQueryHint("Search...");
        imageSlider=rootView.findViewById(R.id.image_slider1);










        ArrayList<String> sliderDataArrayList = new ArrayList<>();


        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("images");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sliderDataArrayList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String imageUrl = dataSnapshot.getValue(String.class);
                    sliderDataArrayList.add(imageUrl);

                }
                List<SlideModel> imageList = new ArrayList<>();

                for (String imageUrl : sliderDataArrayList) {
                    imageList.add(new SlideModel(imageUrl, null));
                }

                imageSlider.setImageList(imageList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        recyclerView=rootView.findViewById(R.id.product);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

         home=new com.ecommericeapp.Adapter.Home(getContext(),productDetails,this);

         myRef1 = FirebaseDatabase.getInstance().getReference().child("categories");

        // Implement search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                home.filter(newText); // Filter adapter data based on search query
                return true;
            }
        });




        ArrayList<String> categoryNamesList = new ArrayList<>();

        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) {
                    String categoryName = categorySnapshot.getKey();
                    categoryNamesList.add(categoryName);
                }

                // Now you have all category names, proceed with fetching data for each category
                for (String categoryName : categoryNamesList) {
                    DatabaseReference categoryRef = myRef1.child(categoryName);
                    fetchDataFromNode(categoryName, categoryRef);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle onCancelled event
            }
        });


        recyclerView.setAdapter(home);

        return rootView;


    }

    private void fetchDataFromNode(String categoryName, DatabaseReference categoryRef) {
        categoryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Accessing properties like 'id', 'image', and 'title'
                    String id = snapshot.child("id").getValue(String.class);
                    String title = snapshot.child("title").getValue(String.class);
                    String price = snapshot.child("price").getValue(String.class);
                    String shrtimage = snapshot.child("srt_image").getValue(String.class);
                    String image1 = snapshot.child("image1").getValue(String.class);
                    String image2 = snapshot.child("image2").getValue(String.class);
                    String image3= snapshot.child("image3").getValue(String.class);
                    String image4= snapshot.child("image4").getValue(String.class);
                    String discount= snapshot.child("discount").getValue(String.class);
                    String deliverycharge= snapshot.child("delivery_charge").getValue(String.class);
                    String shrtdesc= snapshot.child("srt_desc").getValue(String.class);
                    String offer= snapshot.child("offer").getValue(String.class);
                    String size= snapshot.child("size").getValue(String.class);
                    productDetails.add(new productDetail(id,title,price,shrtimage,image1,image2,image3,image4,discount,deliverycharge,offer,shrtdesc,size));

                }
                home.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    @Override
    public void onItemclick(productDetail productDetail) {
        Intent intent=new Intent(getContext(), ProductDetail.class);
        intent.putExtra("ans",productDetail.getSrt_image())
        .putExtra("title",productDetail.getTitle())
        .putExtra("price",productDetail.getPrice())
        .putExtra("Discount",productDetail.getDiscount())
        .putExtra("charge",productDetail.getDelivery_charge())
        .putExtra("offer",productDetail.getOffer())
        .putExtra("size",productDetail.getSize())
        .putExtra("sht_d",productDetail.getSrt_desc())
        .putExtra("id",productDetail.getId())
        .putExtra("image1",productDetail.getImage1())
        .putExtra("image2",productDetail.getImage2())
        .putExtra("image3",productDetail.getImage3())
        .putExtra("image4",productDetail.getImage4())


        ;


        startActivity(intent);



    }
}