package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    private Button btnlogOut,AddBtn, ViewBtn;
    private FirebaseAuth mAuth;
    EditText mail;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnlogOut=findViewById(R.id.blo);
        AddBtn=findViewById(R.id.addRecord);
        ViewBtn=findViewById(R.id.viewRecord);
        Intent i=getIntent();
        String email=i.getStringExtra("Email");
        mail=findViewById(R.id.addemail2);
        mail.setText(email);
        mAuth=FirebaseAuth.getInstance();
        btnlogOut.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(HomeActivity.this,MainActivity.class));
        });
        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddRecordActivity.class);
                startActivity(intent);
                Intent i=new Intent(getApplicationContext(),AddRecordActivity.class);
                i.putExtra("Email",email);
                startActivity(i);
            }
        });
        ViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ViewRecordActivity.class);
                startActivity(intent);

            }
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