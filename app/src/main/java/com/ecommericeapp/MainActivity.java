package com.ecommericeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ecommericeapp.Fragment.Account;
import com.ecommericeapp.Fragment.Cart;
import com.ecommericeapp.Fragment.Home;
import com.ecommericeapp.Fragment.Notify;
import com.ecommericeapp.Fragment.categories;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bottomNavigationView= findViewById(R.id.bottom_navigation);
        frameLayout=findViewById(R.id.fragment_container);
         bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 int id=item.getItemId();
                 if (id==R.id.home12){
                     frameget(new Home());


                 } else if (id==R.id.categori){
                     frameget(new categories());
                 } else if (id==R.id.navigation_notifications) {
                     frameget(new Notify());


                 } else if (id==R.id.Account) {
                     frameget(new Account());


                 }else if (id==R.id.cart){
                     frameget(new Cart());


                 }
                 return true;
             }
         });

         frameget(new Home());






    }
    private void frameget(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

}













