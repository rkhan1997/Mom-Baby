package com.example.mombaby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Emergency extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;
  //  DatabaseReference reff; https://www.youtube.com/watch?v=LpWhAz3e1sI

    TextView regName;
    EditText regPhone;

    Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

          regName = (TextView) findViewById(R.id.doc_name);
          regPhone = (EditText) findViewById(R.id.doc_phone);

        //regbutton
        regButton = (Button) findViewById(R.id.doc_button);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("doctor");

                //Get all the values
              // String name = regName.getEditableText().toString();
               String name = regName.getText().toString();
               String phone = regPhone.getText().toString();

                DocHelper doc = new DocHelper(name, phone);

                reference.setValue(doc);
            }


        });

        //goBackButton
        Button goBackButton = (Button)
                findViewById(R.id.Em_back);
            //onclick
            goBackButton.setOnClickListener(new View.OnClickListener() {
                public void onClick (View v) {
                    startActivity(new Intent(getApplicationContext(),
                            HomeActivity.class));
                }
            }
        );

    }//end goBack onCreate
}
