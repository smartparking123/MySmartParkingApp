package com.example.smartparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartparking.utils.CommonFunctions;

public class Signup extends AppCompatActivity {

    EditText edt_name;
    EditText edt_phonenumber;
    EditText edt_email;
    EditText edt_address;
    EditText edt_city;
    EditText edt_password;
    Button btn_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edt_name=(EditText)findViewById(R.id.edt_name);
        edt_phonenumber=(EditText)findViewById(R.id.edt_phonenumber);
        edt_email=(EditText)findViewById(R.id.edt_email);
        edt_address=(EditText)findViewById(R.id.edt_address);
        edt_city=(EditText)findViewById(R.id.edt_city);
        edt_password=(EditText)findViewById(R.id.edt_password);
        btn_signup=(Button)findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
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
                if (!CommonFunctions.checkstring(edt_email.getText().toString())){
                    edt_email.setError("Please enter your email");
                    edt_email.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkemail(edt_email.getText().toString())){
                    edt_email.setError("Please enter valid email");
                    edt_email.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkstring(edt_address.getText().toString())){
                    edt_address.setError("Please enter your address");
                    edt_address.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkstring(edt_city.getText().toString())){
                    edt_city.setError("Please enter your city");
                    edt_city.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkstring(edt_password.getText().toString())){
                    edt_password.setError("Please enter your password");
                    edt_password.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkpassword(edt_password.getText().toString())){
                    edt_password.setError("Please enter valid password");
                    edt_password.requestFocus();

                    return;
                }



            }
        });
    }
}
