package com.example.sms_readupload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnSendSMS;
    private EditText etPhoneNum, etMessage;
    private final static int REQUEST_CODE_PERMISSION_SEND_SMS = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPhoneNum= (EditText) findViewById(R.id.etPhoneNum);
        etMessage= (EditText) findViewById(R.id.etMsg);
        btnSendSMS= (Button) findViewById(R.id.btnSendSMS);

        btnSendSMS.setEnabled(false);

        btnSendSMS.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String msg=etMessage.getText().toString();
                String phoneNum = etPhoneNum.getText().toString();

                if(checkPermission(Manifest.permission.SEND_SMS)){
                    btnSendSMS.setEnabled(true);
                }
                else{
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            (Manifest.permission.SEND_SMS)},REQUEST_CODE_PERMISSION_SEND_SMS);
                }
            }
        });
    }

    private boolean checkPermission (String permission){
        int checkPermission= ContextCompat.checkSelfPermission(this,permission);
        return checkPermission== PackageManager.PERMISSION_GRANTED;

    }
}
