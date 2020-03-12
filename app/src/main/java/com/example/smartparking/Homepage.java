package com.example.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    public void Clickonnewbooking(View view) {
        Intent i=new Intent(Homepage.this,Booking.class);
        startActivity(i);
    }
    public void Clickonmybooking(View view) {
        Intent i=new Intent(Homepage.this,My_booking.class);
        startActivity(i);
    }
    public void Clickonmypayment(View view) {
        Intent i=new Intent(Homepage.this,My_payment.class);
        startActivity(i);
    }
}
