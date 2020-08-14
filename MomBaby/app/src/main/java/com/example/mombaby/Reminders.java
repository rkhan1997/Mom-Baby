package com.example.mombaby;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Reminders extends AppCompatActivity {

    EditText date_time_in;
    Button storeButton1;

    FirebaseDatabase rootNode3;
    DatabaseReference reference3;

    private ArrayList<RemindersHelper> arrayList1 = new ArrayList<>();
    private ArrayAdapter<RemindersHelper> adapter1;
    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        date_time_in=findViewById(R.id.date_time_input);
        date_time_in.setInputType(InputType.TYPE_NULL);
        date_time_in.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                showDateTimeDialog(date_time_in);
                                            }


        });

        storeButton1 = (Button) findViewById(R.id.storeButton);

        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList1);
        listView1 = (ListView) findViewById(R.id.data1);

        listView1.setAdapter(adapter1);

        storeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode3 = FirebaseDatabase.getInstance();
                reference3 = rootNode3.getReference("Reminders");

//Get all the values
                String datetimein = date_time_in.getText().toString();

                RemindersHelper rem = new RemindersHelper(datetimein);

                reference3.push().setValue(rem);

                rootNode3.getReference().child("Reminders").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            RemindersHelper user2 = snapshot.getValue(RemindersHelper.class);
                            arrayList1.add(user2);
                            adapter1.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

            }


        });

        //goBackButton
        Button goBackButton = (Button)
                findViewById(R.id.back1);
        //onclick
        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                startActivity(new Intent(getApplicationContext(),
                        HomeActivity.class));
            }
        });
    }

    private void showDateTimeDialog(final EditText date_time_in) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(Reminders.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(Reminders.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();


            }


}