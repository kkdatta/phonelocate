package com.lamudi.phonefield.sample;

import android.location.Location;
import android.telephony.SmsManager;

import com.lamudi.phonefield.PhoneEditText;

/**
 * Created by kalyan on 2/5/18.
 */

public class SendLocation {

    public static void sendLocationSMS(String phoneNumber, GPSTracker gpsTracker) {


        SmsManager smsManager = SmsManager.getDefault();
        StringBuffer smsBody = new StringBuffer();
        //smsBody.append("http://maps.google.com/maps?saddr=");
        smsBody.append("https://www.google.com/maps/search/?api=1&query=");
        smsBody.append(gpsTracker.getLatitude());
        smsBody.append(",");
        smsBody.append(gpsTracker.getLongitude());
        smsManager.sendTextMessage(phoneNumber, null, smsBody.toString(), null, null);
    }
}
