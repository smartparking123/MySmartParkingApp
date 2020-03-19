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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.smartparking.utils.CommonFunctions;
import com.example.smartparking.utils.Constants;
import com.example.smartparking.utils.DataInterface;
import com.example.smartparking.utils.Webservice_Volley;
import com.sasidhar.smaps.payumoney.MakePaymentActivity;
import com.sasidhar.smaps.payumoney.PayUMoney_Constants;

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
    TextView txt_date, txt_selected_date, txt_time,txt_selected_time,txt_panel,txt_selected_panel_no,txt_amount,txt_your_amount;

    Webservice_Volley volley;

    String panel;

    String name, phonenumber,date,starttime,endtime,duration,amount,transaction_id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_booking);
        edt_name=(EditText)findViewById(R.id.edt_name);
        edt_phonenumber=(EditText)findViewById(R.id.edt_phonenumber);
        edt_date=(EditText)findViewById(R.id.edt_date);
        edt_starttime=(EditText)findViewById(R.id.edt_starttime);
        edt_endtime=(EditText)findViewById(R.id.edt_endtime);
        btn_submit=(Button)findViewById(R.id.btn_submit);
        txt_date=(TextView)findViewById(R.id.txt_date);
        txt_selected_date=(TextView)findViewById(R.id.txt_selected_date);
        txt_time=(TextView)findViewById(R.id.txt_time);
        txt_selected_time=(TextView)findViewById(R.id.txt_selected_time);
        txt_panel=(TextView)findViewById(R.id.txt_panel);
        txt_selected_panel_no=(TextView)findViewById(R.id.txt_selected_panel_no);
        txt_amount=(TextView)findViewById(R.id.txt_amount);
        txt_your_amount=(TextView)findViewById(R.id.txt_your_amount);

        volley = new Webservice_Volley(this,this);

        starttime=getIntent().getStringExtra("starttime");
        endtime=getIntent().getStringExtra("endtime");
        date=getIntent().getStringExtra("date");
        duration=getIntent().getStringExtra("duration");
        amount=getIntent().getStringExtra("amount");

        panel = getIntent().getStringExtra("panel");

        txt_selected_date.setText(date);
        txt_selected_time.setText(starttime + " to " + endtime);
        txt_selected_panel_no.setText(panel);
        txt_your_amount.setText(amount);

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
                /*if (!CommonFunctions.checkstring(edt_date.getText().toString())){
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
*/
                /*Intent i = new Intent(Booking.this,Select_panel.class);
                i.putExtra("name",edt_name.getText().toString());
                i.putExtra("phonenumber",edt_phonenumber.getText().toString());
                i.putExtra("starttime",edt_starttime.getText().toString());
                i.putExtra("endtime",edt_endtime.getText().toString());
                i.putExtra("duration",""+hours);
                i.putExtra("amount",""+(hours*20));
                i.putExtra("date",edt_date.getText().toString());
                startActivity(i);
*/

                initPayUMoney();


            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String pad(int c) {

        if (c < 10)
            return "0"+c;

        else
            return String.valueOf(c);

    }

    void initPayUMoney() {

        transaction_id = ""+System.currentTimeMillis();
        HashMap<String, String> params = new HashMap<>();
        params.put(PayUMoney_Constants.KEY, "rjQUPktU");
        params.put(PayUMoney_Constants.TXN_ID, transaction_id);
        params.put(PayUMoney_Constants.AMOUNT, String.valueOf(amount));
        params.put(PayUMoney_Constants.PRODUCT_INFO, "Generate Pass");
        params.put(PayUMoney_Constants.FIRST_NAME, edt_name.getText().toString());
        params.put(PayUMoney_Constants.EMAIL, "parkingsmart1@gmail.com");
        params.put(PayUMoney_Constants.PHONE, edt_phonenumber.getText().toString());

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
                    Toast.makeText(Booking.this, "Payment Successful", Toast.LENGTH_SHORT).show();

                    //Call API here for adding payment details in DB

                    String url = Constants.Webserive_Url + "addbooking.php";

                    HashMap<String,String> params = new HashMap<>();

                    params.put("U_id","1");
                    params.put("U_name",edt_name.getText().toString());
                    params.put("U_contactno",edt_phonenumber.getText().toString());
                    params.put("B_date",date);
                    params.put("B_start_time",starttime);
                    params.put("B_end_time",endtime);
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
                    Toast.makeText(Booking.this, "Payment Unsuccessful", Toast.LENGTH_SHORT).show();

                    break;
            }

        }
    }
}

