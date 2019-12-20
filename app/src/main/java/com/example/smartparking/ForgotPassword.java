package com.example.smartparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartparking.utils.CommonFunctions;

public class ForgotPassword extends AppCompatActivity {

    EditText edt_emailphonenumber;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        edt_emailphonenumber = (EditText)findViewById(R.id.edt_emailphonenumber);
        btn_submit = (Button)findViewById(R.id.btn_submit);
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
            }
        });
    }
}
