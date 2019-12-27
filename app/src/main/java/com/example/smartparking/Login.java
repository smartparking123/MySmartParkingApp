package com.example.smartparking;

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

public class Login extends AppCompatActivity implements DataInterface {
    EditText edt_emailphonenumber;
    EditText edt_password;
    Button btn_login;

    Webservice_Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_emailphonenumber=(EditText)findViewById(R.id.edt_emailphonenumber);
        edt_password=(EditText)findViewById(R.id.edt_password);
        btn_login=(Button)findViewById(R.id.btn_login);

        volley = new Webservice_Volley(this,this);

        btn_login.setOnClickListener(new View.OnClickListener() {
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
                if (!CommonFunctions.checkstring(edt_password.getText().toString())) {
                    edt_password.setError("Please enter your password");
                    edt_password.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkpassword(edt_password.getText().toString())) {
                    edt_password.setError("Please enter valid password");
                    edt_password.requestFocus();

                    return;
                }

                String url = Constants.Webserive_Url + "login.php";
                HashMap<String,String> params = new HashMap<>();
                params.put("U_email",edt_emailphonenumber.getText().toString());
                params.put("U_password",edt_password.getText().toString());

                volley.CallVolley(url,params,"login");



            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();

    }
}
