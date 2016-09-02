package com.mobiletouchco;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobiletouchco.utils.Config;
import com.mobiletouchco.utils.PersistentUser;

import org.json.JSONObject;

/**
 * Created by Prosanto on 8/26/16.
 */
public class ChargePhoneActivity extends AppCompatActivity {
    private Context mContext;
    private EditText edit_nation_id;
    private EditText edit_voucher;
    private LinearLayout li_go;
    private TextView operator_name;
    private TextView country_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargephone);
        mContext = this;
        initUi();
    }

    private void initUi() {

        country_name = (TextView) this.findViewById(R.id.country_name);
        operator_name = (TextView) this.findViewById(R.id.operator_name);
        country_name.setText(Config.CountryName);
        operator_name.setText(Config.OperatorName);

        edit_nation_id = (EditText) this.findViewById(R.id.edit_nation_id);
        edit_voucher = (EditText) this.findViewById(R.id.edit_voucher);
        li_go=(LinearLayout)this.findViewById(R.id.li_go);
        li_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Go();
            }
        });
    }

    public void Go() {
        if (edit_nation_id.getText().toString().trim().equalsIgnoreCase("")) {
            Toast.makeText(mContext,"Please Enter voucher ",Toast.LENGTH_LONG).show();
            return;
        } else {


            try {

                String nationId =edit_nation_id.getText().toString().trim();
                String voucherId =edit_voucher.getText().toString().trim();

                JSONObject onJsonObject = new JSONObject(PersistentUser.getOpertordetails(mContext));
                int success = onJsonObject.getInt("success");
                if (success == 1) {
                    JSONObject resulstObject = onJsonObject.getJSONObject("results");
                    String customer_service = resulstObject.getString("charge_code");
                    Log.w("customer_service", "er" + customer_service);

                    if(!nationId.equalsIgnoreCase(""))
                        customer_service=customer_service+"*"+nationId+"*"+voucherId+"%23";
                    else
                        customer_service=customer_service+"*"+voucherId+"%23";

                    Log.w("customer_service", "er" + customer_service);

                    String numberToDial = "tel:" + customer_service;
                    numberToDial =numberToDial.replaceAll("\\#","%23");

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(numberToDial));
                    startActivity(intent);
                }
            } catch (Exception ex) {
                Log.w("Exception", "er" + ex.getMessage());
            }

        }
    }
    public void Back(View v )
    {
        ChargePhoneActivity.this.finish();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
