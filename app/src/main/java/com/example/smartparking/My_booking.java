package com.example.smartparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.smartparking.utils.Constants;
import com.example.smartparking.utils.DataInterface;
import com.example.smartparking.utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class My_booking extends AppCompatActivity implements DataInterface {

    RecyclerView recycler_my_booking;

    Webservice_Volley volley;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);
        recycler_my_booking=(RecyclerView)findViewById(R.id.recycler_my_booking);

        volley = new Webservice_Volley(this,this);

        String url = Constants.Webserive_Url + "booking.php";
        HashMap<String,String> params = new HashMap<>();
        params.put("user_id","1");

        volley.CallVolley(url,params,"booking");
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

    }
}
