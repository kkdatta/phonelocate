package com.lamudi.phonefield.sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.lamudi.phonefield.PhoneEditText;

/**
 * Created by kalyan on 2/5/18.
 */

public class SmsReceiver extends BroadcastReceiver {

    // Interface
    private static SmsListener mListener;
    private DBUtil dbutil;
    String phonenumber="";


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data  = intent.getExtras();
        dbutil = new DBUtil(context);
        phonenumber = dbutil.retrieveContact("STANDARD");
        // PDU: “protocol data unit”, the industry format for an SMS message
        Object[] pdus = (Object[]) data.get("pdus");



        for(int i=0; i<pdus.length; i++){

            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

            String sender = smsMessage.getDisplayOriginatingAddress();
            // We can use this sender to filter messages

//           if (sender.equals("+919673115028"))
            if (sender.equals(phonenumber))
            {
                String messageBody = smsMessage.getMessageBody();
                // Pass the message text to interface
                mListener.messageReceived(messageBody);
            }
        }
    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }

    public String phnNumber(){
        return phonenumber;
    }
}