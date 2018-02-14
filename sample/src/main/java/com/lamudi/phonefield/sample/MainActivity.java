package com.lamudi.phonefield.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.lamudi.phonefield.PhoneEditText;
import com.lamudi.phonefield.PhoneInputLayout;

public class MainActivity extends AppCompatActivity {

    public static final String locatephone = "Locatephone";
    private DBUtil dbUtil;
    private DBUtil phnutil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbUtil = new DBUtil(this);

//        final PhoneInputLayout phoneInputLayout =
//                (PhoneInputLayout) findViewById(R.id.phone_input_layout);

    final EditText editText = (EditText) findViewById(R.id.edit_text_name);

    final PhoneEditText phoneEditText = (PhoneEditText) findViewById(R.id.edit_text);

        CustomPhoneInputLayout customPhoneInputLayout = new CustomPhoneInputLayout(this, "IN");

//    final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
//        .findViewById(android.R.id.content)).getChildAt(0);
//
//    viewGroup.addView(customPhoneInputLayout, 0);


        final Button button = (Button) findViewById(R.id.submit_button);
        final Button history_button = (Button) findViewById(R.id.history);

//    history_button.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        Intent i=new Intent(this,History.class);
//        i.putExtra("calcName","STANDARD");
//        startActivity(i);
//      }
//    });

        //assert phoneInputLayout != null;
    assert phoneEditText != null;
        assert button != null;

//        phoneInputLayout.setHint(R.string.phone_hint);
//        phoneInputLayout.setDefaultCountry("IN");


//
    editText.setHint(R.string.name_hint);
    phoneEditText.setHint(R.string.phone_hint);
    phoneEditText.setDefaultCountry("IN");

        button.setOnClickListener(new View.OnClickListener() {
            //      @Override
            public void onClick(View v) {
                boolean valid = true;
//                if (phoneInputLayout.isValid()) {
//                    phoneInputLayout.setError(null);
//                } else {
//                    phoneInputLayout.setError(getString(R.string.invalid_phone_number));
//                    valid = false;
//                }

                        if (phoneEditText.isValid()) {
                          phoneEditText.setError(null);
                        } else {
                          phoneEditText.setError(getString(R.string.invalid_phone_number));
                          valid = false;
                        }

                if (valid) {
                    dbUtil.insert("STANDARD", phoneEditText.getPhoneNumber());
                    Toast.makeText(MainActivity.this, R.string.valid_phone_number, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, R.string.invalid_phone_number, Toast.LENGTH_LONG).show();
                }

            }
        });


        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,History.class);
                i.putExtra("calcName","STANDARD");
                Toast.makeText(MainActivity.this,"existing contact",Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });

        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
               if(messageText.contains(locatephone)){

                    Toast.makeText(MainActivity.this,"mssg_recved",Toast.LENGTH_LONG).show();
//                    SmsReceiver smsReceiver = new SmsReceiver();
                    String phnno=dbUtil.retrieveContact("STANDARD");
                    GPSTracker gpsTracker = new GPSTracker(MainActivity.this);
                    //SendLocation.sendLocationSMS(phoneEditText.getPhoneNumber(),gpsTracker);
                    SendLocation.sendLocationSMS(phnno,gpsTracker);
                    Toast.makeText(MainActivity.this,phnno,Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}


