package com.example.sms_readupload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnSendSMS;
    private EditText etPhoneNum, etMessage;
    private final static int REQUEST_CODE_PERMISSION_SEND_SMS = 123;

    private ListView lvsms;
    private final static int REQUEST_CODE_PERMISSION_READ_SMS = 456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPhoneNum= (EditText) findViewById(R.id.etPhoneNum);
        etMessage= (EditText) findViewById(R.id.etMsg);
        btnSendSMS= (Button) findViewById(R.id.btnSendSMS);

        btnSendSMS.setEnabled(false);

        if(checkPermission(Manifest.permission.SEND_SMS)){
            btnSendSMS.setEnabled(true);
        }
        else{
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    (Manifest.permission.SEND_SMS)},REQUEST_CODE_PERMISSION_SEND_SMS);
        }

        btnSendSMS.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String msg=etMessage.getText().toString();
                String phoneNum = etPhoneNum.getText().toString();
                SmsManager smsMan = SmsManager.getDefault();
                smsMan.sendTextMessage(phoneNum,null,msg,null,null);
                Toast.makeText(MainActivity.this,"SMS send",Toast.LENGTH_LONG).show();

            }
        });
    }

    private boolean checkPermission (String permission){
        int checkPermission= ContextCompat.checkSelfPermission(this,permission);
        return checkPermission== PackageManager.PERMISSION_GRANTED;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_PERMISSION_SEND_SMS:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    btnSendSMS.setEnabled(true);
                }
                break;

        }
    }
}
