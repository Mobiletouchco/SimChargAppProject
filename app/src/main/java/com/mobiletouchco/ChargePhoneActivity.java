package com.mobiletouchco;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Prosanto on 8/26/16.
 */
public class ChargePhoneActivity extends AppCompatActivity
{
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargephone);
        mContext=this;
    }

}
