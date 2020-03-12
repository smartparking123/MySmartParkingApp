package com.example.smartparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.smartparking.adapters.MyBookingListAdapter;
import com.example.smartparking.adapters.MyPaymentListAdapter;
import com.example.smartparking.models.BookingInfoVo;
import com.example.smartparking.models.PaymentInfo;
import com.example.smartparking.utils.Constants;
import com.example.smartparking.utils.DataInterface;
import com.example.smartparking.utils.Webservice_Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class My_payment extends AppCompatActivity implements DataInterface {

    RecyclerView recycler_my_payment;

    Webservice_Volley volley;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_payment);
        recycler_my_payment=(RecyclerView)findViewById(R.id.recycler_my_payment);

        recycler_my_payment.setLayoutManager(new LinearLayoutManager(this));

        volley = new Webservice_Volley(this,this);

        String url = Constants.Webserive_Url + "payment.php";
        HashMap<String,String> params = new HashMap<>();
        params.put("user_id","1");

        volley.CallVolley(url,params,"payment");
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try{

            PaymentInfo paymentinfo=new Gson().fromJson(jsonObject.toString(),PaymentInfo.class);
            if (paymentinfo!=null)
            {
                if (paymentinfo.getData()!=null)
                {
                    if (paymentinfo.getData().size() >0)
                    {
                        MyPaymentListAdapter adapter=new MyPaymentListAdapter(paymentinfo.getData());
                        recycler_my_payment.setAdapter(adapter);
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

