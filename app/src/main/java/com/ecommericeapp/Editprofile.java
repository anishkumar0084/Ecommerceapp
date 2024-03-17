package com.ecommericeapp;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ecommericeapp.Data.cartdata;
import com.ecommericeapp.Fragment.Account;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Editprofile extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ImageView avatarImageView,avatarImageView1;
    String uid;
    TextView or;
    ImageButton backbnt,search,cart;

    // Initialize views and other variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        backbnt=findViewById(R.id.back_btn);
        search=findViewById(R.id.search_btn);
        cart=findViewById(R.id.cart_button);
        backbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Editprofile.this, MainActivity.class);
                intent.putExtra("fragmentToLoad", "Account");
                startActivity(intent);

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(Editprofile.this, Searchproduct.class);
                startActivity(intent);
                finish();

            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Editprofile.this, cartdata.class);
                startActivity(intent);
                finish();

            }
        });


        // Initialize Firebase Realtime Database reference
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
         uid=FirebaseAuth.getInstance().getCurrentUser().getUid();

        avatarImageView = findViewById(R.id.men_avatar);
        avatarImageView1 = findViewById(R.id.women_avatar);
        or=findViewById(R.id.or);
        check();




        // Find views and set onClickListeners for buttons
        Button updateNameBtn = findViewById(R.id.submit);
        Button updateEmailBtn = findViewById(R.id.update_emailid);
        Button updateMobileBtn = findViewById(R.id.update_number);
        Button deactivateBtn = findViewById(R.id.deactivate_btn);

        updateNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to update name
                updateName();
                check();
            }
        });

        updateEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to update email
                updateEmail();
            }
        });

        updateMobileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to update mobile number
                updateMobileNumber();
            }
        });

        deactivateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to deactivate account
                deactivateAccount();
            }
        });
    }
    private void check(){
        mDatabase.child(uid).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userName = dataSnapshot.getValue(String.class);

                // Check if the name contains "Kumar" or "Kumari" substrings
                if (userName != null) {
                    if (userName.contains("kumar")) {
                        avatarImageView1.setVisibility(View.INVISIBLE);
                        avatarImageView1.setForegroundGravity(Gravity.CENTER);
                        or.setVisibility(View.INVISIBLE);
                        // Display male image

                    } else if (userName.contains("kumari")) {
                        // Display female image
                        avatarImageView.setVisibility(View.INVISIBLE);
                        avatarImageView.setForegroundGravity(Gravity.CENTER);
                        or.setVisibility(View.INVISIBLE);


                    }

                    // Analyze further to determine if it's a male or female name (optional)
                    // For example, you can use a list of common male and female names
                    // Or you can use machine learning-based approaches for better accuracy
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle onCancelled event
            }
        });

    }

    private void updateName() {
        // Retrieve updated name from EditText

        String newName = ((TextInputEditText) findViewById(R.id.first_name)).getText().toString();
        String lastName = ((TextInputEditText) findViewById(R.id.last_name)).getText().toString();

        // Update name in Firebase Realtime Database
        mDatabase.child(uid).child("name").setValue(newName+lastName);
        Toast.makeText(Editprofile.this, "Name updated successfully", Toast.LENGTH_SHORT).show();
    }

    private void updateEmail() {
        // Retrieve updated email from EditText
        String newEmail = ((TextInputEditText) findViewById(R.id.e_mail)).getText().toString();

        // Update email in Firebase Realtime Database
        mDatabase.child("userId").child("email").setValue(newEmail);
        Toast.makeText(Editprofile.this, "Email updated successfully", Toast.LENGTH_SHORT).show();
    }

    private void updateMobileNumber() {
        // Retrieve updated mobile number from EditText
        String newMobileNumber = ((TextInputEditText) findViewById(R.id.mobile_number)).getText().toString();

        // Update mobile number in Firebase Realtime Database
        mDatabase.child("userId").child("mobileNumber").setValue(newMobileNumber);
        Toast.makeText(Editprofile.this, "Mobile number updated successfully", Toast.LENGTH_SHORT).show();
    }

    private void deactivateAccount() {
        // Flag the account as deactivated in the database
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);
            userRef.child("active").setValue(false);
        }

        // Optionally, remove user data
        // DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);
        // userRef.removeValue(); // Remove user data from the database

        // Sign out the user
        FirebaseAuth.getInstance().signOut();

        // Redirect the user to the login screen or any other appropriate activity
        startActivity(new Intent(Editprofile.this, login_page.class));

        Toast.makeText(Editprofile.this, "Account deactivated successfully", Toast.LENGTH_SHORT).show();
    }

}
