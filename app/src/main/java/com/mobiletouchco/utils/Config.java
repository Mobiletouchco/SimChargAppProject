package com.mobiletouchco.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by JASYC DELL 3 on 3/22/2016.
 */
public class Config {
    public static final String BASEULR ="http://www.mobiletouchco.com/webservice/";
    public static final String securitycode = "api#100#mobiletouch*786!#101";
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
            return true;
        else
            return false;
    }
}
