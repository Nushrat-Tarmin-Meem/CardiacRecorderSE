package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cardiacrecorder.databinding.ActivityUpdateBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity {
    private ActivityUpdateBinding binding;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        binding=ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Calendar calendar=Calendar.getInstance();
        String currentDate= DateFormat.getDateInstance().format(calendar.getTime());


        binding.Uptextdate.setText(currentDate);
        SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss");
        String currentTime=sdf.format(new Date());
        binding.Uptexttime.setText(currentTime);

        binding.Upsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PreTime=binding.PreUpTime.getText().toString();
                String mail=binding.Upname.getText().toString();
                String sys=binding.Upsystolic.getText().toString();
                String dias=binding.Updiastolic.getText().toString();
                String time=binding.Uptexttime.getText().toString();
                String date=binding.Uptextdate.getText().toString();
                String rate=binding.UpHeartRate.getText().toString();
                String comment=binding.Upgivecomment.getText().toString();

                updateData(PreTime,mail,sys,dias,time,date,rate,comment);

            }
        });
    }

    public void updateData(String preTime,String mail, String sys, String dias,String time,String date,String rate,String comment) {
        HashMap User = new HashMap();

        User.put("PreTime", preTime);
        User.put("comment", comment);
        User.put("d", date);
        User.put("dias", dias);
        User.put("mail", mail);
        User.put("rate", rate);
        User.put("sis", sys);
        User.put("t", time);

        databaseReference = FirebaseDatabase.getInstance().getReference("Records");
        databaseReference.child(mail).child(preTime).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    binding.Upname.setText("");
                    binding.Upsystolic.setText("");
                    binding.Updiastolic.setText("");
                    binding.UpHeartRate.setText("");
                    binding.Upgivecomment.setText("");
                    binding.Uptextdate.setText("");
                    binding.Uptexttime.setText("");
                    Intent intent = new Intent(UpdateActivity.this,HomeActivity.class);
                    Toast.makeText(UpdateActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(UpdateActivity.this, "Failed to Update", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}

