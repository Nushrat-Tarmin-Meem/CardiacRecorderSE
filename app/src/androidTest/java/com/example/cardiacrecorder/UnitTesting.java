package com.example.cardiacrecorder;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
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

@RunWith(AndroidJUnit4.class)

public class UnitTesting {

    @Rule
    public ActivityTestRule<AddRecordActivity> activityRule = new ActivityTestRule<AddRecordActivity>(AddRecordActivity.class);

    private AddRecordActivity activity;
    private Button submitButton;
    private EditText nameEditText;
    private EditText systolicEditText;
    private EditText diastolicEditText;
    private EditText heartRateEditText;
    private EditText commentEditText;



    @Before
    public void setUp() {

        activity = activityRule.getActivity();
        submitButton = activity.findViewById(R.id.submit);
        nameEditText = activity.findViewById(R.id.name);
        systolicEditText = activity.findViewById(R.id.systolic);
        diastolicEditText = activity.findViewById(R.id.diastolic);
        heartRateEditText = activity.findViewById(R.id.HeartRate);
        commentEditText = activity.findViewById(R.id.give_comment);
    }

    @Test
    public void testAddData() {
        activity.runOnUiThread(() -> {
            nameEditText.setText("Meem");
            systolicEditText.setText("76");
            diastolicEditText.setText("75");
            heartRateEditText.setText("75");
            commentEditText.setText("UnitTest");
            submitButton.performClick();
        });

        // Wait for the UI thread to complete
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the record was added successfully
    }


}

