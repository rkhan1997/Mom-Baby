package com.example.mombaby;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityLog extends AppCompatActivity {
    FirebaseDatabase rootNode2;
    DatabaseReference reference2;

    EditText regActivity;
    EditText regTime;
    EditText regDate;

    Button regButton2;

    private ArrayList<ActivityHelper> arrayList = new ArrayList<>();
    private ArrayAdapter<ActivityHelper> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        regActivity = (EditText) findViewById(R.id.editTextActivity);
        regTime = (EditText) findViewById(R.id.editTextTime);
        regDate = (EditText) findViewById(R.id.editTextDate);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView = (ListView) findViewById(R.id.firebaseData);

        listView.setAdapter(adapter);
        //regbutton
        rootNode2 = FirebaseDatabase.getInstance();
        reference2 = rootNode2.getReference("Activities");
        rootNode2.getReference().child("Activities").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ActivityHelper user = snapshot.getValue(ActivityHelper.class);
                    arrayList.add(user);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        regButton2 = (Button) findViewById(R.id.updateButton);

        regButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode2 = FirebaseDatabase.getInstance();
                reference2 = rootNode2.getReference("Activities");

                        //Get all the values
                        final String activity = regActivity.getText().toString();
                        final String time = regTime.getText().toString();
                        final String date = regDate.getText().toString();


                        ActivityHelper act = new ActivityHelper(activity, time, date);

                        reference2.push().setValue(act);

                    }

        });

        //goBackButton
        Button goBackButton = (Button)
                findViewById(R.id.back2);
        //onclick
        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                startActivity(new Intent(getApplicationContext(),
                        HomeActivity.class));
            }
        });

    }
}