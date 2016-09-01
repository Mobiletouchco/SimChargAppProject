package com.mobiletouchco;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
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
        LinearLayout li_go=(LinearLayout)this.findViewById(R.id.li_go);
        li_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext,HomeActivity.class);
                startActivity(mIntent);
                ((Activity) mContext).overridePendingTransition(R.anim.pull_in_right,
                        R.anim.push_out_left);
            }
        });

    }
}
