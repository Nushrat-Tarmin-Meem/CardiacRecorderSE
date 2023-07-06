package com.example.cardiacrecorder;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.room.Update;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(AndroidJUnit4.class)

public class UnitTesting2 {

    @Rule
    public ActivityTestRule<UpdateActivity> activityRule = new ActivityTestRule<UpdateActivity>(UpdateActivity.class);


    private String preTime,mail,sys, dias, time, date,rate,comment;
    private UpdateActivity upactivity;

    @Before
    public void setUp() {

        upactivity = activityRule.getActivity();

    }

    @Test
    public void testUpdateData() {
        upactivity.runOnUiThread(() -> {
            // Set up the test data
            preTime = "21:22:37";
            mail = "Meem";
            sys = "50";
            dias = "50";
            SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss");
            time=sdf.format(new Date());
            Calendar calendar=Calendar.getInstance();
            date= DateFormat.getDateInstance().format(calendar.getTime());
            rate = "55";
            comment = "Updated comment";

            // Call the updateData() method
            upactivity.updateData(preTime, mail, sys, dias, time, date, rate, comment);
        });

        // Wait for the update operation to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the data was updated successfully in the Firebase database
        // You can add your verification logic here based on your specific requirements
        // For example, you can check if the data in the database matches the updated values
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Records");
        reference.child(mail).child(preTime).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Check if the snapshot exists and contains the expected updated values
                assertNotNull(snapshot);
                assertEquals(sys, snapshot.child("sis").getValue());
                assertEquals(dias, snapshot.child("dias").getValue());
                assertEquals(rate, snapshot.child("rate").getValue());
                assertEquals(comment, snapshot.child("comment").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                fail("Database error: " + error.getMessage());
            }
        });
    }

}

