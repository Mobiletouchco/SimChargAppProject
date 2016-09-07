package com.mobiletouchco;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
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
import com.mobiletouchco.utils.AlertMessage;
import com.mobiletouchco.utils.BusyDialog;
import com.mobiletouchco.utils.Config;
import com.mobiletouchco.utils.Connectivity;
import com.mobiletouchco.utils.NetInfo;
import com.mobiletouchco.utils.PersistentUser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
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
    private LinearLayout li_auto;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private LinearLayout li_contactus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initUi();

    }

    private void initUi() {
        text_networkd = (TextView) this.findViewById(R.id.text_networkd);
        LinearLayout li_go = (LinearLayout) this.findViewById(R.id.li_go);
        li_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (allOperators.size() > 1) {
                    if (operator_spinner.getSelectedItemPosition() > 0) {
                        Operator mOperator = allOperators.get(operator_spinner.getSelectedItemPosition());
                        if (!NetInfo.isOnline(mContext)) {


                        } else {
                            doWebRequestForOpertordetails(mOperator.getOperator_id());

                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Please select operator", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "No operator found!!!", Toast.LENGTH_SHORT).show();

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
                if (allOperators.size() > 0) {
                    Collections.sort(allOperators, Operator.StuNameComparator);
                    Operator mOperator = new Operator();
                    mOperator.setOperator_name("Select operator");
                    allOperators.add(0, mOperator);

                }
                mOperatorAdapter = new OperatorAdapter(mContext, R.layout.spinner_item, allOperators);
                operator_spinner.setAdapter(mOperatorAdapter);
                mOperatorAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        li_auto = (LinearLayout) this.findViewById(R.id.li_auto);
        li_auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{android.Manifest.permission.READ_PHONE_STATE},
                            REQUEST_CODE_ASK_PERMISSIONS);
                } else {
                    TelephonyManager manager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                    String networkOperator = manager.getNetworkOperator();
                    int mcc = 0, mnc = 0;
                    if (networkOperator != null) {
                        mcc = Integer.parseInt(networkOperator.substring(0, 3));
                        mnc = Integer.parseInt(networkOperator.substring(3));
                    }
                    doWebRequestForOpertordetailsbyName("" + mnc, "" + mcc);

                }
            }
        });
        li_contactus = (LinearLayout) this.findViewById(R.id.li_contactus);
        li_contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"Mobiletouchco@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Support");
                i.putExtra(Intent.EXTRA_TEXT, "");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.READ_PHONE_STATE},
                    REQUEST_CODE_ASK_PERMISSIONS);
        }

        // check the internet connection available or not
        if (!NetInfo.isOnline(mContext)) {
            String responseData = PersistentUser.getAllOpertordetails(mContext);
            if (responseData.equalsIgnoreCase("")) {
                Toast.makeText(MainActivity.this, "Need to first time Internet connection.", Toast.LENGTH_SHORT).show();
                return;
            }
            try {

                CountryListparser.connect(mContext, responseData);
                AllCountryList.getAllCountryListort();
                if (AllCountryList.getAllCountryListort().size() > 0) {
                    CountryList mCountryList = new CountryList();
                    mCountryList.setCountry_name("Select country");
                    AllCountryList.getAllCountryList().add(0, mCountryList);
                }
                mCountryAdapter.notifyDataSetChanged();
                if (AllCountryList.getAllCountryList().size() > 0)
                    spinner_country.setSelection(0);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            doWebRequestForCountryInfo();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void doWebRequestForCountryInfo() {

        String url = Config.BASEULR + "countryoperatorinfo";

        mBusyDialog = new BusyDialog(mContext, false, "Loading...");
        mBusyDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mBusyDialog.dismis();
                Log.w("response", "are" + response);
                try {

                    CountryListparser.connect(mContext, response);
                    PersistentUser.setAllOpertordetails(mContext, response);
                    AllCountryList.getAllCountryListort();
                    if (AllCountryList.getAllCountryListort().size() > 0) {
                        CountryList mCountryList = new CountryList();
                        mCountryList.setCountry_name("Select country");
                        AllCountryList.getAllCountryList().add(0, mCountryList);

                    }
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
        Log.w("operator_id", "are" + operator_id);

        mBusyDialog = new BusyDialog(mContext, false, "Loading...");
        mBusyDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mBusyDialog.dismis();
                Log.w("response", "are" + response);
                try {

                    PersistentUser.setOpertordetails(mContext, response);
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

    public void doWebRequestForOpertordetailsbyName(final String mobile_network_code, final String mobile_country_code) {

        String url = Config.BASEULR + "operatordetailsbyname";
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

                    JSONObject mJsonObject = new JSONObject(response);
                    int success = mJsonObject.getInt("success");
                    if (success == 0) {
                        Toast.makeText(mContext, "Your current network is not detected.Please manually select  network", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        PersistentUser.setOpertordetails(mContext, response);
                        Intent mIntent = new Intent(mContext, HomeActivity.class);
                        startActivity(mIntent);
                        ((Activity) mContext).overridePendingTransition(R.anim.pull_in_right,
                                R.anim.push_out_left);
                    }

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
                params.put("mobile_country_code", mobile_country_code);
                params.put("mobile_network_code", mobile_network_code);


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
