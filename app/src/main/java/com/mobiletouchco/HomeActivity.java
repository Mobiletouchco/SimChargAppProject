package com.mobiletouchco;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.mobiletouchco.utils.Connectivity;

/**
 * Created by Prosanto on 8/26/16.
 */
public class HomeActivity extends AppCompatActivity {
    private Context mContext;
    private LinearLayout lay_chargebanlance;
    private LinearLayout lay_chackbalance;
    private LinearLayout lay_customer_service;
    private LinearLayout cardInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = this;
        initUi();
    }

    private void initUi() {
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

                String numberToDial = "tel:" + "*158" + "%23";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(numberToDial));
                startActivity(intent);
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
                Intent mIntent = new Intent(mContext, SimcardinfoActivity.class);
                startActivity(mIntent);
                ((Activity) mContext).overridePendingTransition(R.anim.pull_in_right,
                        R.anim.push_out_left);
            }
        });

    }
    public void Back(View v) {
        HomeActivity.this.finish();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

    }

}
