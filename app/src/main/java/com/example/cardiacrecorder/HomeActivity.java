package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    private Button btnlogOut;
    private FirebaseAuth mAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnlogOut=findViewById(R.id.blo);
        mAuth=FirebaseAuth.getInstance();
        btnlogOut.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(HomeActivity.this,MainActivity.class));
        });
    }
    @Override
    protected void onStart() {

        super.onStart();
        FirebaseUser user= mAuth.getCurrentUser();
        if(user==null){
            startActivity(new Intent(HomeActivity.this,LoginActivity.class));
        }
    }
}