package com.mobiletouchco;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Prosanto on 8/27/16.
 */
public class CustomerserviceActivity extends AppCompatActivity {
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerservice);
        mContext = this;
        initUi();
    }

    private void initUi() {
    }

    public void Back(View v) {
        CustomerserviceActivity.this.finish();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
