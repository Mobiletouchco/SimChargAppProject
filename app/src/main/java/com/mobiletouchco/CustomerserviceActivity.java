package com.mobiletouchco;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
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
    private ImageView operator_image;
    private AQuery mAQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerservice);
        mContext = this;
        mAQ =new AQuery(mContext);
        initUi();
    }

    private void initUi() {
        details_operator = (TextView) this.findViewById(R.id.details_operator);
        country_name = (TextView) this.findViewById(R.id.country_name);
        operator_name = (TextView) this.findViewById(R.id.operator_name);
        operator_image=(ImageView)this.findViewById(R.id.operator_image);


        try {

            JSONObject onJsonObject = new JSONObject(PersistentUser.getOpertordetails(mContext));
            int success = onJsonObject.getInt("success");
            if (success == 1) {
                JSONObject resulstObject = onJsonObject.getJSONObject("results");
                String customer_service = resulstObject.getString("customer_service");
                details_operator.setText(customer_service);
                String operator_logo = resulstObject.getString("operator_logo");
                String country_nameT = resulstObject.getString("country_name");
                String operator_nameT = resulstObject.getString("operator_name");
                country_name.setText(country_nameT);
                operator_name.setText(operator_nameT);
                if(!operator_logo.equalsIgnoreCase(""))
                    mAQ.id(operator_image).image(operator_logo, true, true, 200, 0);
            }
        } catch (Exception ex) {

        }
    }

    public void Back(View v) {
        CustomerserviceActivity.this.finish();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

    }
}
