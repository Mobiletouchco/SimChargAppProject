package com.mobiletouchco;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Prosanto on 8/26/16.
 */
public class ChargePhoneActivity extends AppCompatActivity {
    private Context mContext;
    private EditText edit_nation_id;
    private EditText edit_voucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargephone);
        mContext = this;
        initUi();
    }

    private void initUi() {
        edit_nation_id = (EditText) this.findViewById(R.id.edit_nation_id);
        edit_voucher = (EditText) this.findViewById(R.id.edit_voucher);

    }

    public void Go(View v) {
        if (edit_nation_id.getText().toString().trim().equalsIgnoreCase("")) {
        } else if (edit_nation_id.getText().toString().trim().equalsIgnoreCase("")) {
        } else {

            String data ="*155*1045634*123456789"+"%23";
            String numberToDial = "tel:"+data;
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(numberToDial));
            startActivity(intent);
        }
    }
}
