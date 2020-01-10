package com.example.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ResetPassword extends AppCompatActivity implements DataInterface {

    EditText edt_newpassword;
    EditText edt_confirmnewpassword;
    Button btn_submit;

    Webservice_Volley volley;
    String userid ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        userid = getIntent().hasExtra("id") ? getIntent().getStringExtra("id") : "0";

        edt_newpassword=(EditText)findViewById(R.id.edt_newpassword);
        edt_confirmnewpassword=(EditText)findViewById(R.id.edt_confirmnewpassword);
        btn_submit=(Button)findViewById(R.id.btn_submit);

        volley = new Webservice_Volley(this,this);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!CommonFunctions.checkstring(edt_newpassword.getText().toString())) {
                    edt_newpassword.setError("Please enter your password");
                    edt_newpassword.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkpassword(edt_newpassword.getText().toString())) {
                    edt_newpassword.setError("Please enter valid password ");
                    edt_newpassword.requestFocus();

                    return;
                }
                if (!CommonFunctions.checkstring(edt_confirmnewpassword.getText().toString())) {
                    edt_confirmnewpassword.setError("Please enter your password");
                    edt_confirmnewpassword.requestFocus();
                    return;
                }
                if (!CommonFunctions.checkstring(edt_confirmnewpassword.getText().toString())) {
                    edt_confirmnewpassword.setError("Please enter valid password");
                    edt_confirmnewpassword.requestFocus();
                    return;
                }

                String url = Constants.Webserive_Url + "resetpsw.php";
                HashMap<String,String> params = new HashMap<>();
                params.put("U_id",userid);
                params.put("U_password",edt_newpassword.getText().toString());

                volley.CallVolley(url,params,"resetpsw");
            }
        });

    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

            if (jsonObject.getString("response").equalsIgnoreCase("1")) {

                Intent i = new Intent(ResetPassword.this,Login.class);
                startActivity(i);

                finishAffinity();




            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
