package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText rmail,rpassword,cpassword;
    TextView regi;
    Button rtn,rb;
    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        rmail = findViewById(R.id.rmail);
        rpassword = findViewById(R.id.rpassword);
        cpassword = findViewById(R.id.rcpassword);
        rtn = findViewById(R.id.rbutton);
        regi = findViewById(R.id.regi);
        rb=findViewById(R.id.rb);
        mAuth = FirebaseAuth.getInstance();
        rtn.setOnClickListener(view -> {
            createUser();
        });

        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });

        regi.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }
    private void  createUser(){
        String email= rmail.getText().toString();
        String password= rpassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            rmail.setError("Email can not be empty!");
            rmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            rpassword.setError("Password can not be empty!");
            rpassword.requestFocus();
        }else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText( RegisterActivity.this,"User Registered Successfully!", Toast.LENGTH_SHORT).show();

                                }
                                else {
                                    Toast.makeText( RegisterActivity.this,"User Registration Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }else {
                        Toast.makeText( RegisterActivity.this,"User Registration Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }
    }
