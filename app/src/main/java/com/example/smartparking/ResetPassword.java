package com.example.smartparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smartparking.utils.CommonFunctions;

public class ResetPassword extends AppCompatActivity {

    EditText edt_newpassword;
    EditText edt_confirmnewpassword;
    Button btn_submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        edt_newpassword=(EditText)findViewById(R.id.edt_newpassword);
        edt_confirmnewpassword=(EditText)findViewById(R.id.edt_confirmnewpassword);
        btn_submit=(Button)findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!CommonFunctions.checkstring(edt_newpassword.getText().toString())) {
                    edt_newpassword.setError("Please enter your password");
                    edt_newpassword.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkpassword(edt_newpassword.getText().toString())) {
                    edt_newpassword.setError("Please enter valid password");
                    edt_newpassword.requestFocus();

                    return;
                }
                if (CommonFunctions.checkstring(edt_confirmnewpassword.getText().toString())) {
                    edt_confirmnewpassword.setError("Please enter your password");
                    edt_confirmnewpassword.requestFocus();
                    return;
                }
                if (CommonFunctions.checkstring(edt_confirmnewpassword.getText().toString())) {
                    edt_confirmnewpassword.setError("Please enter valid password");
                    edt_confirmnewpassword.requestFocus();
                    return;
                }
            }
        });

    }
}
