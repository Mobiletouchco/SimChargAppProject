package com.mobiletouchco;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobiletouchco.adpater.CountryAdapter;
import com.mobiletouchco.adpater.OperatorAdapter;
import com.mobiletouchco.holder.AllCountryList;
import com.mobiletouchco.model.CountryList;
import com.mobiletouchco.model.Operator;
import com.mobiletouchco.parser.CountryListparser;
import com.mobiletouchco.utils.BusyDialog;
import com.mobiletouchco.utils.Config;
import com.mobiletouchco.utils.Connectivity;
import com.mobiletouchco.utils.NetInfo;
import com.mobiletouchco.utils.PersistentUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private TextView text_networkd;
    private BusyDialog mBusyDialog;
    private Spinner spinner_country;
    private CountryAdapter mCountryAdapter;
    private Spinner operator_spinner;
    private ArrayList<Operator> allOperators = new ArrayList<>();
    private OperatorAdapter mOperatorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initUi();

    }

    private void initUi() {
        text_networkd = (TextView) this.findViewById(R.id.text_networkd);
        NetworkInfo mNetworkInfo = Connectivity.getNetworkInfo(mContext);
        text_networkd.setText(mNetworkInfo.getTypeName());
        LinearLayout li_go = (LinearLayout) this.findViewById(R.id.li_go);
        li_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (allOperators.size() > 0) {
                    Operator mOperator = allOperators.get(operator_spinner.getSelectedItemPosition());
                    doWebRequestForOpertordetails(mOperator.getOperator_id());
                    Config.OperatorName= mOperator.getOperator_name();
                    CountryList mCountryList = AllCountryList.getAllCountryList().elementAt(spinner_country.getSelectedItemPosition());
                    Config.CountryName= mCountryList.getCountry_name();

                }

            }
        });
        spinner_country = (Spinner) this.findViewById(R.id.spinner_country);
        mCountryAdapter = new CountryAdapter(mContext, R.layout.spinner_item);
        spinner_country.setAdapter(mCountryAdapter);
        mCountryAdapter.notifyDataSetChanged();

        operator_spinner = (Spinner) this.findViewById(R.id.operator_spinner);
        spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CountryList mCountryList = AllCountryList.getAllCountryList().elementAt(i);
                allOperators = mCountryList.getAllOperator();
                mOperatorAdapter = new OperatorAdapter(mContext, R.layout.spinner_item, allOperators);
                operator_spinner.setAdapter(mOperatorAdapter);
                mOperatorAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        doWebRequestForCountryInfo();
    }

    public void doWebRequestForCountryInfo() {

        String url = Config.BASEULR + "countryoperatorinfo";
        if (!NetInfo.isOnline(mContext)) {
            Toast.makeText(getApplicationContext(), "" + getResources().getString(R.string.internetconnection_erro), Toast.LENGTH_SHORT).show();
            return;
        }
        mBusyDialog = new BusyDialog(mContext, false, "Loading...");
        mBusyDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mBusyDialog.dismis();
                Log.w("response", "are" + response);
                try {

                    CountryListparser.connect(mContext, response);
                    mCountryAdapter.notifyDataSetChanged();
                    if (AllCountryList.getAllCountryList().size() > 0)
                        spinner_country.setSelection(0);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mBusyDialog.dismis();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("security_code", Config.securitycode);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        int socketTimeout = 50000;// 30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }

    public void doWebRequestForOpertordetails(final String operator_id) {

        String url = Config.BASEULR + "operatordetails";
        if (!NetInfo.isOnline(mContext)) {
            Toast.makeText(getApplicationContext(), "" + getResources().getString(R.string.internetconnection_erro), Toast.LENGTH_SHORT).show();
            return;
        }
        mBusyDialog = new BusyDialog(mContext, false, "Loading...");
        mBusyDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mBusyDialog.dismis();
                Log.w("response", "are" + response);
                try {

                    PersistentUser.setOpertordetails(mContext,response);
                    Intent mIntent = new Intent(mContext, HomeActivity.class);
                    startActivity(mIntent);
                    ((Activity) mContext).overridePendingTransition(R.anim.pull_in_right,
                            R.anim.push_out_left);


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mBusyDialog.dismis();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("security_code", Config.securitycode);
                params.put("operator_id", operator_id);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        int socketTimeout = 50000;// 30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }

}
