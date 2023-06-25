package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddRecordActivity extends AppCompatActivity {
    EditText systo,diasto,hRate,comment;
    Button RecordSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        Calendar calendar=Calendar.getInstance();
        String currentDate= DateFormat.getDateInstance().format(calendar.getTime());

        TextView textViewDate=findViewById(R.id.text_date);
        textViewDate.setText(currentDate);

        SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss");
        String currentTime=sdf.format(new Date());

        TextView textViewTime=findViewById(R.id.text_time);
        textViewTime.setText(currentTime);

        systo=findViewById(R.id.systolic);
        diasto=findViewById(R.id.diastolic);
        hRate=findViewById(R.id.HeartRate);
        comment=findViewById(R.id.give_comment);

        RecordSubmit=findViewById(R.id.Add_Record_btn);
        RecordSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Ssysto=systo.getText().toString();
                String Sdiasto=diasto.getText().toString();
                String ShRate=hRate.getText().toString();
                String Scomment=comment.getText().toString();

                if(TextUtils.isEmpty(Ssysto))
                {
                    systo.setError("Please,Enter Systolic Pressure");
                    systo.requestFocus();
                }
                else if(TextUtils.isEmpty(Sdiasto))
                {
                    diasto.setError("Please,Enter Diastolic Pressure");
                    diasto.requestFocus();
                }
                else if(TextUtils.isEmpty(ShRate))
                {
                    hRate.setError("Please,Enter HeartRate");
                    hRate.requestFocus();
                }
                else
                {


                }

            }
        });
    }
}