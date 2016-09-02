package com.mobiletouchco;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobiletouchco.utils.Config;
import com.mobiletouchco.utils.Connectivity;
import com.mobiletouchco.utils.PersistentUser;

import org.json.JSONObject;

/**
 * Created by Prosanto on 8/26/16.
 */
public class HomeActivity extends AppCompatActivity {
    private Context mContext;
    private LinearLayout lay_chargebanlance;
    private LinearLayout lay_chackbalance;
    private LinearLayout lay_customer_service;
    private LinearLayout cardInfo;
    private TextView operator_name;
    private TextView country_name;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = this;
        initUi();
    }

    private void initUi() {

        country_name = (TextView) this.findViewById(R.id.country_name);
        operator_name = (TextView) this.findViewById(R.id.operator_name);
        country_name.setText(Config.CountryName);
        operator_name.setText(Config.OperatorName);

        lay_chargebanlance = (LinearLayout) this.findViewById(R.id.lay_chargebanlance);
        lay_chackbalance = (LinearLayout) this.findViewById(R.id.lay_chackbalance);
        lay_customer_service = (LinearLayout) this.findViewById(R.id.lay_customer_service);
        cardInfo = (LinearLayout) this.findViewById(R.id.cardInfo);
        lay_chargebanlance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, ChargePhoneActivity.class);
                startActivity(mIntent);
                ((Activity) mContext).overridePendingTransition(R.anim.pull_in_right,
                        R.anim.push_out_left);
            }
        });
        lay_chackbalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    JSONObject onJsonObject = new JSONObject(PersistentUser.getOpertordetails(mContext));
                    int success = onJsonObject.getInt("success");
                    if (success == 1) {
                        JSONObject resulstObject = onJsonObject.getJSONObject("results");
                        String customer_service = resulstObject.getString("check_balance");
                        String numberToDial = "tel:" + customer_service;
                        numberToDial =numberToDial.replaceAll("\\#","%23");
                        Log.w("numberToDial", "er" + numberToDial);

                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(numberToDial));
                        startActivity(intent);
                    }
                } catch (Exception ex) {
                    Log.w("Exception", "er" + ex.getMessage());
                }

            }
        });
        lay_customer_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, CustomerserviceActivity.class);
                startActivity(mIntent);
                ((Activity) mContext).overridePendingTransition(R.anim.pull_in_right,
                        R.anim.push_out_left);
            }
        });
        cardInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{android.Manifest.permission.READ_PHONE_STATE},
                            REQUEST_CODE_ASK_PERMISSIONS);
                }
                else {
                    Intent mIntent = new Intent(mContext, SimcardinfoActivity.class);
                    startActivity(mIntent);
                    ((Activity) mContext).overridePendingTransition(R.anim.pull_in_right,
                            R.anim.push_out_left);
                }


            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    public void Back(View v) {
        HomeActivity.this.finish();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

    }

}
