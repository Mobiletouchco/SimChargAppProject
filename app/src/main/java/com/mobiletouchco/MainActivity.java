package com.mobiletouchco;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;

    }
    public void Go(View v)
    {
        Intent mIntent = new Intent(mContext,HomeActivity.class);
        startActivity(mIntent);
    }
}
