package com.ecommericeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Reg_page extends AppCompatActivity {

    AppCompatButton button;

    TextInputLayout name,email,password,mobile;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    TextView progressText;
    ProgressBar progressBar;











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_page);
        name=findViewById(R.id.outlinedTextField);
        email=findViewById(R.id.outlinedTextField2);
        password=findViewById(R.id.outlinedTextField5);
        mobile=findViewById(R.id.outlinedTextField3);
        button=findViewById(R.id.regis);
        progressBar=findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);
        progressBar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user=mAuth.getCurrentUser();


        if (user!= null) {
            // User is signed in, go to main activity
            startActivity(new Intent(Reg_page.this, login_page.class));
            finish();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                progressBar.setVisibility(View.VISIBLE);
                progressText.setText("Creating account...");

            }
        });



    }

    private void registerUser() {
        final String name1 = name.getEditText().getText().toString().trim();
        final String email1 = email.getEditText().getText().toString().trim();
        String password1 = password.getEditText().getText().toString().trim();
        String mobile1 = mobile.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(name1) || TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1) || TextUtils.isEmpty(mobile1)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;

        } else  if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        } else if (!TextUtils.isDigitsOnly(mobile1)) {
            Toast.makeText(this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show();
            return;




        } else {

        mAuth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Registration success
                    String userId = mAuth.getCurrentUser().getUid();
                    User user = new User(name1, email1,password1,mobile1 );
                    mDatabase.child("users").child(userId).setValue(user);

                    Intent intent=new Intent(Reg_page.this,login_page.class);
                    startActivity(intent);

                    Toast.makeText(Reg_page.this, "Registration successful",
                            Toast.LENGTH_SHORT).show();
                    // Redirect to next activity or perform desired action
                } else {
                    // Registration failed
                    Toast.makeText(Reg_page.this, "Registration failed",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }}
}