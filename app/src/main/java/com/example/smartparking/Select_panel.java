package com.example.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartparking.adapters.MyListAdapter;
import com.example.smartparking.utils.Constants;
import com.example.smartparking.utils.DataInterface;
import com.example.smartparking.utils.Webservice_Volley;
import com.sasidhar.smaps.payumoney.MakePaymentActivity;
import com.sasidhar.smaps.payumoney.PayUMoney_Constants;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Select_panel extends AppCompatActivity implements MyListAdapter.setOnItemClickListner {

    RecyclerView recyclerview_panels;

    String[] mydata = new String[]{"Panel no. 1","Panel no. 2","Panel no. 3","Panel no. 4"};


    String edt_name, edt_phonenumber,edt_date,edt_starttime,edt_endtime,panel,duration,amount,transaction_id;
    Webservice_Volley volley;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_panel);

        /*edt_name=getIntent().getStringExtra("name");
        edt_phonenumber=getIntent().getStringExtra("phonenumber");*/

        edt_starttime=getIntent().getStringExtra("starttime");
        edt_endtime=getIntent().getStringExtra("endtime");
        edt_date=getIntent().getStringExtra("date");
        duration=getIntent().getStringExtra("duration");
        amount=getIntent().getStringExtra("amount");


        recyclerview_panels=(RecyclerView)findViewById(R.id.recyclerview_panels);
        recyclerview_panels.setLayoutManager(new GridLayoutManager(this,2));

        recyclerview_panels.setAdapter(new MyListAdapter(this,mydata,this));


    }



    @Override
    public void onItemClick(int pos, String s) {

        panel = String.valueOf(pos+1);

        Intent i = new Intent(Select_panel.this, Booking.class);

        i.putExtra("starttime",edt_starttime);
        i.putExtra("endtime",edt_endtime);
        i.putExtra("duration",""+duration);
        i.putExtra("amount",""+amount);
        i.putExtra("date",edt_date);
        i.putExtra("panel",panel);

        startActivity(i);

    }


}
