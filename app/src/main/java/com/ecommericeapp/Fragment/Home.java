package com.ecommericeapp.Fragment;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.ecommericeapp.Adapter.SliderAdapter;
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
import com.smarteist.autoimageslider.SliderView;

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




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        SearchView searchView = rootView.findViewById(R.id.search);
        searchView.setQueryHint("Search...");
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Searchproduct.class);
                startActivity(intent);


            }
        });


        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = rootView.findViewById(R.id.slider);

        SliderAdapter adapter = new SliderAdapter(getContext(), sliderDataArrayList);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("images");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sliderDataArrayList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    sliderDataArrayList.add(new SliderData(dataSnapshot.getValue().toString()) );
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);
        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);
        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);
        // to start autocycle below method is used.
        sliderView.startAutoCycle();

        recyclerView=rootView.findViewById(R.id.product);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

         home=new com.ecommericeapp.Adapter.Home(getContext(),productDetails,this);

         myRef1 = FirebaseDatabase.getInstance().getReference().child("categories");

        String[] categoryNames = {"shirt", "jeans", "mobile"};

        // Fetch data from each category
        for (String categoryName : categoryNames) {
            DatabaseReference categoryRef = myRef1.child(categoryName);
            fetchDataFromNode(categoryName, categoryRef);
        }

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
                    String image = snapshot.child("image").getValue(String.class);
                    String title = snapshot.child("title").getValue(String.class);
                    String price = snapshot.child("price").getValue(String.class);
                    productDetails.add(new productDetail(id,title,price,image));

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
        intent.putExtra("ans",productDetail.getImage());
        intent.putExtra("title",productDetail.getTitle());
        intent.putExtra("price",productDetail.getPrice());

        startActivity(intent);
        Toast.makeText(getActivity(),"tes"+productDetail.getTitle(),Toast.LENGTH_SHORT).show();



    }
}