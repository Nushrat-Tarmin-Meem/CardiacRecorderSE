package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardiacrecorder.databinding.ActivityAddRecordBinding;
import com.example.cardiacrecorder.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class AddRecordActivity extends AppCompatActivity {
    ActivityAddRecordBinding binding;
    String mail,sis,dias,rate,comment,d,t;
    FirebaseDatabase db;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Calendar calendar=Calendar.getInstance();
        String currentDate= DateFormat.getDateInstance().format(calendar.getTime());

        binding.textDate.setText(currentDate);
        SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss");
        String currentTime=sdf.format(new Date());
        binding.textTime.setText(currentTime);
        Intent i=getIntent();
        String email=i.getStringExtra("Email");
        binding.addemail.setText(String.valueOf(email));
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail=binding.name.getText().toString();
                sis=binding.systolic.getText().toString();
                dias=binding.diastolic.getText().toString();
                rate=binding.HeartRate.getText().toString();
                comment=binding.giveComment.getText().toString();
                d=binding.textDate.getText().toString();
                t=binding.textTime.getText().toString();
                if(!sis.isEmpty() && !dias.isEmpty() && !rate.isEmpty()){
                    Records records= new Records(mail,sis,dias,rate,comment,d,t);
                    db=FirebaseDatabase.getInstance();
                    reference=db.getReference("Records");
                    reference.child(mail).child(t).setValue(records).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.addemail.setText("");
                            binding.systolic.setText("");
                            binding.diastolic.setText("");
                            binding.HeartRate.setText("");
                            binding.giveComment.setText("");
                            binding.textDate.setText("");
                            binding.textTime.setText("");
                            Toast.makeText(AddRecordActivity.this, "Successfully Added Record", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}