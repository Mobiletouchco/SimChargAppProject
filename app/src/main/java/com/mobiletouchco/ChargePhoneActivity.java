package com.mobiletouchco;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
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
    private ImageView operator_image;
    private AQuery mAQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargephone);
        mContext = this;
        mAQ = new AQuery(mContext);
        initUi();
    }

    private void initUi() {

        country_name = (TextView) this.findViewById(R.id.country_name);
        operator_name = (TextView) this.findViewById(R.id.operator_name);
        operator_image=(ImageView)this.findViewById(R.id.operator_image);
        try {

            JSONObject onJsonObject = new JSONObject(PersistentUser.getOpertordetails(mContext));
            int success = onJsonObject.getInt("success");
            if (success == 1) {
                JSONObject resulstObject = onJsonObject.getJSONObject("results");
                String operator_logo = resulstObject.getString("operator_logo");
                String country_nameT = resulstObject.getString("country_name");
                String operator_nameT = resulstObject.getString("operator_name");
                country_name.setText(country_nameT);
                operator_name.setText(operator_nameT);
                if(!operator_logo.equalsIgnoreCase(""))
                    mAQ.id(operator_image).image(operator_logo, true, true, 200, 0);

            }
        } catch (Exception ex) {
            Log.w("Exception", "er" + ex.getMessage());
        }



        edit_nation_id = (EditText) this.findViewById(R.id.edit_nation_id);
        edit_voucher = (EditText) this.findViewById(R.id.edit_voucher);
        li_go = (LinearLayout) this.findViewById(R.id.li_go);
        li_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Go();
            }
        });
    }

    public void Go() {

        try {

            String nationId = edit_nation_id.getText().toString().trim();
            String voucherId = edit_voucher.getText().toString().trim();

            JSONObject onJsonObject = new JSONObject(PersistentUser.getOpertordetails(mContext));
            int success = onJsonObject.getInt("success");
            if (success == 1) {
                JSONObject resulstObject = onJsonObject.getJSONObject("results");
                String customer_service = resulstObject.getString("charge_code");
                Log.w("customer_service", "er" + customer_service);

                if (customer_service.contains("ID")) {
                    if (edit_nation_id.getText().toString().trim().equalsIgnoreCase("")) {
                        Toast.makeText(mContext, "Please Enter voucher ", Toast.LENGTH_LONG).show();
                        return;
                    } else if (edit_nation_id.getText().toString().trim().equalsIgnoreCase("")) {
                        Toast.makeText(mContext, "Please Enter ID ", Toast.LENGTH_LONG).show();
                        return;
                    } else {

                        customer_service = customer_service.replace("ID", nationId);
                        customer_service = customer_service.replace("VT", voucherId);

                        String numberToDial = "tel:" + customer_service;
                        numberToDial = numberToDial.replaceAll("\\#", "%23");

                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(numberToDial));
                        startActivity(intent);
                    }
                } else {
                    if (edit_nation_id.getText().toString().trim().equalsIgnoreCase("")) {
                        Toast.makeText(mContext, "Please Enter voucher ", Toast.LENGTH_LONG).show();
                        return;
                    } else {

//                        customer_service = customer_service.replace("ID", nationId);
                        customer_service = customer_service.replace("VT", voucherId);
                        String numberToDial = "tel:" + customer_service;
                        numberToDial = numberToDial.replaceAll("\\#", "%23");
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(numberToDial));
                        startActivity(intent);
                    }


                }


            }
        } catch (Exception ex) {
            Log.w("Exception", "er" + ex.getMessage());
        }


    }

    public void Back(View v) {
        ChargePhoneActivity.this.finish();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
