package com.example.mombaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //emergency button
       ImageButton em_img = (ImageButton) findViewById(R.id.Em_img);
        //set the onclick
        em_img.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) { startActivity(new Intent(getApplicationContext(), Emergency.class));
                                                 }
                                             }
        );

        //log button
        ImageButton activityButt = (ImageButton) findViewById(R.id.activityButton);
        //set the onclick
        activityButt.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     startActivity(new Intent(getApplicationContext(), ActivityLog.class));
                                                 }
                                             }
        );


        //reminder button
        ImageButton RemindButt = (ImageButton) findViewById(R.id.RemindButton);
        //set the onclick
        RemindButt.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  startActivity(new Intent(getApplicationContext(), Reminders.class));
                                              }
                                          }
        );

        //reminder button
        ImageButton StoreButt = (ImageButton) findViewById(R.id.storeLocator);
        //set the onclick
        StoreButt.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              startActivity(new Intent(getApplicationContext(), NearbyStoresMap.class));
                                          }
                                      }
        );

    }
}