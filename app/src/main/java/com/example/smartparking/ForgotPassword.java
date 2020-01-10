package com.example.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartparking.utils.CommonFunctions;
import com.example.smartparking.utils.Constants;
import com.example.smartparking.utils.DataInterface;
import com.example.smartparking.utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class ForgotPassword extends AppCompatActivity implements DataInterface {

    EditText edt_emailphonenumber;
    Button btn_submit;
    Webservice_Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        edt_emailphonenumber = (EditText)findViewById(R.id.edt_emailphonenumber);
        btn_submit = (Button)findViewById(R.id.btn_submit);
        volley = new Webservice_Volley(this,this);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!CommonFunctions.checkstring(edt_emailphonenumber.getText().toString())) {
                    edt_emailphonenumber.setError("Please enter your email/phonenumber");
                    edt_emailphonenumber.requestFocus();
                    return;
                }
                if(TextUtils.isDigitsOnly(edt_emailphonenumber.getText().toString()))
                {
                    if (!CommonFunctions.checkmobile(edt_emailphonenumber.getText().toString())) {
                        edt_emailphonenumber.setError("Please enter valid phonenumber");
                        edt_emailphonenumber.requestFocus();
                        return;
                    }
                }
                else
                {
                    if (!CommonFunctions.checkemail(edt_emailphonenumber.getText().toString())) {
                        edt_emailphonenumber.setError("Please enter valid email");
                        edt_emailphonenumber.requestFocus();
                        return;
                    }

                }
                String url = Constants.Webserive_Url + "forgotpsw.php";
                HashMap<String,String> params = new HashMap<>();
                params.put("U_email",edt_emailphonenumber.getText().toString());

                volley.CallVolley(url,params,"forgotpsw");
            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

            if (jsonObject.getString("response").equalsIgnoreCase("1")) {


                String code = jsonObject.getString("verificationcode");
                String id = jsonObject.getString("id");

                Toast.makeText(this, "Verification code : " + code, Toast.LENGTH_LONG).show();

                Intent i = new Intent(ForgotPassword.this,Verification.class);
                i.putExtra("code",code);
                i.putExtra("id",id);
                startActivity(i);




            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}
