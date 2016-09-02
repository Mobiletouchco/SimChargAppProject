package com.mobiletouchco;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mobiletouchco.utils.Config;
import com.mobiletouchco.utils.PersistentUser;

import org.json.JSONObject;

/**
 * Created by Prosanto on 8/27/16.
 */
public class CustomerserviceActivity extends AppCompatActivity {
    protected Context mContext;
    private TextView details_operator;
    private TextView operator_name;
    private TextView country_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerservice);
        mContext = this;
        initUi();
    }

    private void initUi() {
        details_operator = (TextView) this.findViewById(R.id.details_operator);
        country_name = (TextView) this.findViewById(R.id.country_name);
        operator_name = (TextView) this.findViewById(R.id.operator_name);
        country_name.setText(Config.CountryName);
        operator_name.setText(Config.OperatorName);

        try {

            JSONObject onJsonObject = new JSONObject(PersistentUser.getOpertordetails(mContext));
            int success = onJsonObject.getInt("success");
            if (success == 1) {
                JSONObject resulstObject = onJsonObject.getJSONObject("results");
                String customer_service = resulstObject.getString("customer_service");
                details_operator.setText(customer_service);

            }
        } catch (Exception ex) {

        }
    }

    public void Back(View v) {
        CustomerserviceActivity.this.finish();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

    }
}
