package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cardiacrecorder.databinding.ActivityDeleteDataBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteData extends AppCompatActivity {
    ActivityDeleteDataBinding binding;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDeleteDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.Delsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=binding.Delname.getText().toString();
                String time=binding.Deltime.getText().toString();
                deleteData(name,time);

            }
        });
    }
    private void deleteData(String d_name,String d_time){
        databaseReference= FirebaseDatabase.getInstance().getReference("Records");
        databaseReference.child(d_name).child(d_time).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(DeleteData.this,HomeActivity.class);
                    Toast.makeText(DeleteData.this,"Successfully Deleted",Toast.LENGTH_SHORT).show();
                    startActivity(intent);


                }
                else
                {
                    Toast.makeText(DeleteData.this,"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}