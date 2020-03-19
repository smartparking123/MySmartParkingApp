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

public class check_booking extends AppCompatActivity implements DataInterface {

    EditText edt_check_date;
    EditText edt_start_time;
    EditText edt_end_time;
    Button btn_check_availability;

    Webservice_Volley volley;

    String panel;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_booking);

        edt_check_date=(EditText)findViewById(R.id.edt_check_date);
        edt_start_time=(EditText)findViewById(R.id.edt_start_time);
        edt_end_time=(EditText)findViewById(R.id.edt_end_time);
        btn_check_availability=(Button)findViewById(R.id.btn_check_availabilty);

        volley = new Webservice_Volley(this,this);

        panel = getIntent().getStringExtra("panel");

        edt_check_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(check_booking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        edt_check_date.setText(pad(year)+"-"+pad(month+1)+"-"+pad(dayOfMonth));



                    }
                }, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));


                datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
                datePickerDialog.show();

            }
        });

        edt_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(check_booking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        edt_start_time.setText(pad(hourOfDay)+":"+pad(minute));

                    }
                },Calendar.getInstance().get(Calendar.HOUR),Calendar.getInstance().get(Calendar.MINUTE),true);

                timePickerDialog.show();

            }
        });

        edt_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(check_booking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        edt_end_time.setText(pad(hourOfDay)+":"+pad(minute));

                    }
                },Calendar.getInstance().get(Calendar.HOUR),Calendar.getInstance().get(Calendar.MINUTE),true);

                timePickerDialog.show();

            }
        });

        btn_check_availability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!CommonFunctions.checkstring(edt_check_date.getText().toString())){
                    edt_check_date.setError("Please enter date");
                    edt_check_date.requestFocus();
                    return;
                }

                if (!CommonFunctions.checkstring(edt_start_time.getText().toString())){
                    edt_start_time.setError("Please enter your start time");
                    edt_start_time.requestFocus();
                    return;
                }

                if (!CommonFunctions.checkstring(edt_end_time.getText().toString())){
                    edt_end_time.setError("Please enter your end time");
                    edt_end_time.requestFocus();
                    return;
                }




                int hours=0;

                try {


                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

                    Date date1 = simpleDateFormat.parse(edt_start_time.getText().toString());
                    Date date2 = simpleDateFormat.parse(edt_end_time.getText().toString());

                    if (date2.before(date1)){
                        Toast.makeText(check_booking.this, "End time should not be before start time", Toast.LENGTH_SHORT).show();
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

                Intent i = new Intent(check_booking.this,Select_panel.class);

                i.putExtra("starttime",edt_start_time.getText().toString());
                i.putExtra("endtime",edt_end_time.getText().toString());
                i.putExtra("duration",""+hours);
                i.putExtra("amount",""+(hours*20));
                i.putExtra("date",edt_check_date.getText().toString());
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

