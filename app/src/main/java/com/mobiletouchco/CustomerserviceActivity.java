package com.mobiletouchco;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Prosanto on 8/27/16.
 */
public class CustomerserviceActivity extends AppCompatActivity
{
    protected Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simcardinfo);
        mContext=this;
        initUi();
    }
    private void initUi() {
    }
}
