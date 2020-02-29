package com.example.smartparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.smartparking.adapters.MyBookingListAdapter;
import com.example.smartparking.models.BookingInfoVo;
import com.example.smartparking.utils.Constants;
import com.example.smartparking.utils.DataInterface;
import com.example.smartparking.utils.Webservice_Volley;
import com.google.gson.Gson;

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

        recycler_my_booking.setLayoutManager(new LinearLayoutManager(this));

        volley = new Webservice_Volley(this,this);

        String url = Constants.Webserive_Url + "booking.php";
        HashMap<String,String> params = new HashMap<>();
        params.put("user_id","1");

        volley.CallVolley(url,params,"booking");
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try{

            BookingInfoVo bookingInfoVo=new Gson().fromJson(jsonObject.toString(),BookingInfoVo.class);
            if (bookingInfoVo!=null)
            {
                if (bookingInfoVo.getData()!=null)
                {
                    if (bookingInfoVo.getData().size() >0)
                    {
                        MyBookingListAdapter adapter=new MyBookingListAdapter(bookingInfoVo.getData());
                        recycler_my_booking.setAdapter(adapter);
                    }
                }
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }



    }
}
