package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText mail,passwordl;
    TextView log,log2;
    Button login;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mail=findViewById(R.id.lmail);
        log=findViewById(R.id.log);

        passwordl=findViewById(R.id.lpassword);
        login= findViewById(R.id.lbutton);
        mAuth =FirebaseAuth.getInstance();

        login.setOnClickListener(view -> {
            loginUser();
        });

        log.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        });
    }
    private void loginUser(){
        String email= mail.getText().toString();
        String password= passwordl.getText().toString();

        if(TextUtils.isEmpty(email)){
            mail.setError("Email can not be empty!");
            mail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            passwordl.setError("Password can not be empty!");
            passwordl.requestFocus();
        }else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        if(mAuth.getCurrentUser().isEmailVerified()){
                            Toast.makeText( LoginActivity.this,"Logged Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,HomeActivity.class));

                        }
                        else {
                            Toast.makeText( LoginActivity.this,"Please Verify Your Email!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText( LoginActivity.this,"User Signin Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}