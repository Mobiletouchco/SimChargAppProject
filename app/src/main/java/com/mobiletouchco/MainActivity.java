package com.mobiletouchco;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mobiletouchco.utils.Connectivity;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private TextView text_networkd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        initUi();

    }
    private void initUi() {
        text_networkd=(TextView)this.findViewById(R.id.text_networkd);
        NetworkInfo mNetworkInfo = Connectivity.getNetworkInfo(mContext);
        text_networkd.setText(mNetworkInfo.getTypeName());

    }

    public void Go(View v)
    {
        Intent mIntent = new Intent(mContext,HomeActivity.class);
        startActivity(mIntent);
    }
}
