package com.example.smartparking;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.smartparking.utils.CommonFunctions;
import com.example.smartparking.utils.Constants;
import com.example.smartparking.utils.DataInterface;
import com.example.smartparking.utils.Webservice_Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Booking extends AppCompatActivity implements DataInterface {

    EditText edt_name;
    EditText edt_phonenumber;
    EditText edt_date;
    EditText edt_starttime;
    EditText edt_endtime;
    Button btn_submit;

    Webservice_Volley volley;

    String panel;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_booking);
        edt_name=(EditText)findViewById(R.id.edt_name);
        edt_phonenumber=(EditText)findViewById(R.id.edt_phonenumber);
        edt_date=(EditText)findViewById(R.id.edt_date);
        edt_starttime=(EditText)findViewById(R.id.edt_starttime);
        edt_endtime=(EditText)findViewById(R.id.edt_endtime);
        btn_submit=(Button)findViewById(R.id.btn_submit);

        volley = new Webservice_Volley(this,this);

        panel = getIntent().getStringExtra("panel");

        edt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(Booking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        edt_date.setText(pad(year)+"-"+pad(month+1)+"-"+pad(dayOfMonth));



                    }
                }, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));


                datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
                datePickerDialog.show();

            }
        });

        edt_starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(Booking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        edt_starttime.setText(pad(hourOfDay)+":"+pad(minute));

                    }
                },Calendar.getInstance().get(Calendar.HOUR),Calendar.getInstance().get(Calendar.MINUTE),true);

                timePickerDialog.show();

            }
        });

        edt_endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(Booking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        edt_endtime.setText(pad(hourOfDay)+":"+pad(minute));

                    }
                },Calendar.getInstance().get(Calendar.HOUR),Calendar.getInstance().get(Calendar.MINUTE),true);

                timePickerDialog.show();

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CommonFunctions.checkstring(edt_name.getText().toString())){
                    edt_name.setError("Please enter your name");
                    edt_name.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkstring(edt_phonenumber.getText().toString())){
                    edt_phonenumber.setError("Please enter your phone number");
                    edt_phonenumber.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkmobile(edt_phonenumber.getText().toString())){
                    edt_phonenumber.setError("Please enter valid phone number");
                    edt_phonenumber.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkstring(edt_date.getText().toString())){
                    edt_date.setError("Please enter date");
                    edt_date.requestFocus();
                    return;
                }

                if (!CommonFunctions.checkstring(edt_starttime.getText().toString())){
                    edt_starttime.setError("Please enter your start time");
                    edt_starttime.requestFocus();
                    return;
                }

                if (!CommonFunctions.checkstring(edt_endtime.getText().toString())){
                    edt_endtime.setError("Please enter your end time");
                    edt_endtime.requestFocus();
                    return;
                }




                int hours=0;

                try {


                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

                    Date date1 = simpleDateFormat.parse(edt_starttime.getText().toString());
                    Date date2 = simpleDateFormat.parse(edt_endtime.getText().toString());

                    if (date2.before(date1)){
                        Toast.makeText(Booking.this, "End time should not be before start time", Toast.LENGTH_SHORT).show();
                    return;
                    }


                    long difference = date2.getTime() - date1.getTime();
                    int days = (int) (difference / (1000 * 60 * 60 * 24));
                    hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
                    int min = (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);
                    hours = (hours < 0 ? 1 : hours);

                    hours = (min<=0)?hours:hours+1;

                    Log.i("======= Hours", " :: " + hours);


                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                Intent i = new Intent(Booking.this,Select_panel.class);
                i.putExtra("name",edt_name.getText().toString());
                i.putExtra("phonenumber",edt_phonenumber.getText().toString());
                i.putExtra("starttime",edt_starttime.getText().toString());
                i.putExtra("endtime",edt_endtime.getText().toString());
                i.putExtra("duration",""+hours);
                i.putExtra("amount",""+(hours*20));
                i.putExtra("date",edt_date.getText().toString());
                startActivity(i);



            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
    }

    public String pad(int c) {

        if (c < 10)
            return "0"+c;

        else
            return String.valueOf(c);

    }
}

