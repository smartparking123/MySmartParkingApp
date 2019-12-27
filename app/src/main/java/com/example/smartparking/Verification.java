package com.example.smartparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.smartparking.utils.CommonFunctions;

import org.json.JSONObject;

public class Verification extends AppCompatActivity  {

    EditText edt_number1;
    EditText edt_number2;
    EditText edt_number3;
    EditText edt_number4;
    Button btn_submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        edt_number1=(EditText)findViewById(R.id.edt_number1);
        edt_number2=(EditText)findViewById(R.id.edt_number2);
        edt_number3=(EditText)findViewById(R.id.edt_number3);
        edt_number4=(EditText)findViewById(R.id.edt_number4);
        btn_submit=(Button)findViewById(R.id.btn_submit);


        btn_submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String code=edt_number1.getText().toString()+edt_number2.getText().toString()+edt_number3.getText().toString()+edt_number4.getText().toString();
                if (!CommonFunctions.checkverification(code))
                {
                    Toast.makeText(Verification.this, "Please enter four digit verification code", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

    }


}
