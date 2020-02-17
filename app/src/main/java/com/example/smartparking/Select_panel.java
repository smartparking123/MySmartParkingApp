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

import org.json.JSONObject;

import java.util.HashMap;

public class Select_panel extends AppCompatActivity implements DataInterface, MyListAdapter.setOnItemClickListner {

    RecyclerView recyclerview_panels;

    String[] mydata = new String[]{"Panel no. 1","Panel no. 2","Panel no. 3","Panel no. 4"};

    String edt_name, edt_phonenumber,edt_date,edt_starttime,edt_endtime,panel,duration,amount;
    Webservice_Volley volley;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_panel);
        edt_name=getIntent().getStringExtra("name");
        edt_phonenumber=getIntent().getStringExtra("phonenumber");
        edt_starttime=getIntent().getStringExtra("starttime");
        edt_endtime=getIntent().getStringExtra("endtime");
        edt_date=getIntent().getStringExtra("date");
        duration=getIntent().getStringExtra("duration");
        amount=getIntent().getStringExtra("amount");


        recyclerview_panels=(RecyclerView)findViewById(R.id.recyclerview_panels);

        recyclerview_panels.setLayoutManager(new GridLayoutManager(this,2));


        recyclerview_panels.setAdapter(new MyListAdapter(this,mydata,this));

        volley = new Webservice_Volley(this,this);







    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

            Intent i = new Intent(Select_panel.this, Homepage.class);
            startActivity(i);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int pos, String s) {

        panel = String.valueOf(pos+1);


        String url = Constants.Webserive_Url + "addbooking.php";

        HashMap<String,String> params = new HashMap<>();

        params.put("U_id","1");
        params.put("U_name",edt_name);
        params.put("U_contactno",edt_phonenumber);
        params.put("B_date",edt_date);
        params.put("B_start_time",edt_starttime);
        params.put("B_end_time",edt_endtime);
        params.put("B_amount",amount);
        params.put("B_duration",duration);
        params.put("B_status","0");
        params.put("B_placeid",panel);

        volley.CallVolley(url,params,"addbooking");


    }
}
