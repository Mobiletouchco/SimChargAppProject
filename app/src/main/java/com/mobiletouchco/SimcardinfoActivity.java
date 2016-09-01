package com.mobiletouchco;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Prosanto on 8/27/16.
 */
public class SimcardinfoActivity extends AppCompatActivity {
    protected Context mContext;
    private TextView device_imei;
    private TextView device_model;
    private TextView device_Manufacturer;
    private TextView device_brand;

    private TextView sim_state;
    private TextView sim_serial;
    private TextView sim_imsi;
    private TextView sim_country;
    private TextView sim_operator;
    private TextView sim_operatorcode;
    private TextView sim_networdktype;

    private final int REQUEST_READ_PHONE_STATE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simcardinfo);
        mContext = this;
        initUi();
    }

    private void initUi() {

        sim_state = (TextView) this.findViewById(R.id.sim_state);
        sim_serial = (TextView) this.findViewById(R.id.sim_serial);
        sim_imsi = (TextView) this.findViewById(R.id.sim_imsi);
        sim_country = (TextView) this.findViewById(R.id.sim_country);
        sim_operator = (TextView) this.findViewById(R.id.sim_operator);
        sim_operatorcode = (TextView) this.findViewById(R.id.sim_operatorcode);
        sim_networdktype = (TextView) this.findViewById(R.id.sim_networdktype);

        //http://stackoverflow.com/questions/7074584/android-device-information
        device_imei = (TextView) this.findViewById(R.id.device_imei);
        device_model = (TextView) this.findViewById(R.id.device_model);
        device_Manufacturer = (TextView) this.findViewById(R.id.device_Manufacturer);
        device_brand = (TextView) this.findViewById(R.id.device_brand);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            //TODO
        }
        TelephonyManager telephonyManager = (TelephonyManager) mContext
                .getSystemService(Context.TELEPHONY_SERVICE);
        int simState = telephonyManager.getSimState();
        String SerialNumber = telephonyManager.getSimSerialNumber();
        String simIMSI = telephonyManager.getSubscriberId().toString();
        String CountryIso = telephonyManager.getSimCountryIso();
        String OperatorName = telephonyManager.getSimOperatorName();
        String SimOperator = telephonyManager.getSimOperator();
        Locale l = new Locale("", "" + CountryIso.toUpperCase());
        String country = l.getDisplayCountry();

        sim_serial.setText("" + SerialNumber);
        sim_imsi.setText("" + simIMSI);
        sim_country.setText(country);
        sim_operator.setText(OperatorName);
        sim_operatorcode.setText(SimOperator);
        sim_networdktype.setText("" + networkType());


        String _MANUFACTURER = android.os.Build.MANUFACTURER;
        String _BRAND = android.os.Build.BRAND;
        String _MODEL = android.os.Build.MODEL;
        String device_id = Build.SERIAL;
        device_id = telephonyManager.getDeviceId();

        device_imei.setText(device_id);
        device_model.setText(_MODEL);
        device_Manufacturer.setText(_MANUFACTURER);
        device_brand.setText(_BRAND);

    }

    @Override

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                }
                break;

            default:
                break;
        }
    }

    private String networkType() {
        TelephonyManager teleMan = (TelephonyManager)
                getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = teleMan.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return "1xRTT";
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return "CDMA";
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return "EDGE";
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return "eHRPD";
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return "EVDO rev. 0";
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return "EVDO rev. A";
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return "EVDO rev. B";
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return "GPRS";
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return "HSDPA";
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return "HSPA";
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "HSPA+";
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return "HSUPA";
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "iDen";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "LTE";
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return "UMTS";
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return "Unknown";
        }
        throw new RuntimeException("New type of network");
    }

    public void Back(View v) {
        SimcardinfoActivity.this.finish();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

}
