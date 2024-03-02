package com.ecommericeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_page extends AppCompatActivity {

    TextInputEditText emailEditText;
    TextInputEditText passwordEditText;
    FirebaseAuth mauth;
    DatabaseReference databaseReference;
    Button signInButton;
    Button registrationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton=findViewById(R.id.signInButton);
        registrationButton=findViewById(R.id.registrationButton);
        mauth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        FirebaseUser user=mauth.getCurrentUser();

        if (user!= null) {
            // User is signed in, go to main activity
            startActivity(new Intent(login_page.this, MainActivity.class));
            finish();
        }
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_page.this,Reg_page.class));
                finish();

            }
        });






        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

    }

    private void loginUser() {
        final String email1 = emailEditText.getText().toString().trim();
        String password1 = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;

        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        } else {
            mauth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user=mauth.getCurrentUser();
                        if (user!=null){
                            String uid=user.getUid();
                            databaseReference.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    User userdata=snapshot.getValue(User.class);

                                    if (userdata != null) {
                                        // User data retrieved successfully
                                        Toast.makeText(login_page.this, "Welcome back, " + userdata.getName(),
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    Intent intent=new Intent(login_page.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();



                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {


                                }
                            });

                        }

                    }else {
                        Toast.makeText(login_page.this, "Login Failed" ,
                                Toast.LENGTH_SHORT).show();

                    }

                }
            });


        }
    }
}