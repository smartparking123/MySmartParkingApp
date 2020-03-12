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

public class Select_panel extends AppCompatActivity implements DataInterface, MyListAdapter.setOnItemClickListner {

    RecyclerView recyclerview_panels;

    String[] mydata = new String[]{"Panel no. 1","Panel no. 2","Panel no. 3","Panel no. 4"};


    String edt_name, edt_phonenumber,edt_date,edt_starttime,edt_endtime,panel,duration,amount,transaction_id;
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



       initPayUMoney();

    }
    void initPayUMoney() {

        transaction_id = ""+System.currentTimeMillis();
        HashMap<String, String> params = new HashMap<>();
        params.put(PayUMoney_Constants.KEY, "rjQUPktU");
        params.put(PayUMoney_Constants.TXN_ID, transaction_id);
        params.put(PayUMoney_Constants.AMOUNT, String.valueOf(amount));
        params.put(PayUMoney_Constants.PRODUCT_INFO, "Generate Pass");
        params.put(PayUMoney_Constants.FIRST_NAME, edt_name);
        params.put(PayUMoney_Constants.EMAIL, "parkingsmart1@gmail.com");
        params.put(PayUMoney_Constants.PHONE, edt_phonenumber);

/*        params.put(PayUMoney_Constants.SURL, "http://delta9.in/success.php");
        params.put(PayUMoney_Constants.FURL, "http://delta9.in/fail.php");*/

        params.put(PayUMoney_Constants.SURL, "https://www.payumoney.com/mobileapp/payumoney/success.php");
        params.put(PayUMoney_Constants.FURL, "https://www.payumoney.com/mobileapp/payumoney/failure.php");
        params.put(PayUMoney_Constants.UDF1, "");
        params.put(PayUMoney_Constants.UDF2, "");
        params.put(PayUMoney_Constants.UDF3, "");
        params.put(PayUMoney_Constants.UDF4, "");
        params.put(PayUMoney_Constants.UDF5, "");

        String hash = com.sasidhar.smaps.payumoney.Utils.generateHash(params, "e5iIg1jwi8");

        params.put(PayUMoney_Constants.HASH, hash);
        params.put(PayUMoney_Constants.SERVICE_PROVIDER, "payu_paisa");

        Intent intent = new Intent(this, MakePaymentActivity.class);
        intent.putExtra(PayUMoney_Constants.ENVIRONMENT, PayUMoney_Constants.ENV_DEV);
        intent.putExtra(PayUMoney_Constants.PARAMS, params);

        startActivityForResult(intent, PayUMoney_Constants.PAYMENT_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PayUMoney_Constants.PAYMENT_REQUEST) {
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(Select_panel.this, "Payment Successful", Toast.LENGTH_SHORT).show();

                    //Call API here for adding payment details in DB

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
                        params.put("P_date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                        params.put("P_time",new SimpleDateFormat("HH:mm").format(new Date()));
                        params.put("P_status","paid");
                        params.put("transaction_id",transaction_id);



                        volley.CallVolley(url,params,"addbooking");



                    break;
                case RESULT_CANCELED:
                    Toast.makeText(Select_panel.this, "Payment Unsuccessful", Toast.LENGTH_SHORT).show();

                    break;
            }

        }
    }
}
