package com.mobiletouchco.parser;

import android.content.Context;
import android.util.Log;

import com.mobiletouchco.holder.AllCountryList;
import com.mobiletouchco.model.CountryList;
import com.mobiletouchco.model.Operator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Prosanto on 9/2/16.
 */
public class CountryListparser {
    public static boolean connect(Context con, String result)
            throws JSONException, IOException {

        AllCountryList.removeAllCountryList();
        CountryList mCountryList;
        Operator mOperator = null;
        if (result.length() < 1 || result == null) {
            return false;
        }
        Map<String,CountryList> mapValuesCountryList = new HashMap<String, CountryList>();

        final JSONObject json_ob = new JSONObject(result);
        int success = json_ob.getInt("success");

        if (success == 1) {
            final JSONArray JsonArray = json_ob.getJSONArray("results");

            for (int i = 0; i < JsonArray.length(); i++) {
                JSONObject object = JsonArray.getJSONObject(i);
                mCountryList = new CountryList();
                mCountryList.setCountry_code(object.getString("country_code"));
                mCountryList.setCountry_id(object.getString("country_id"));

                String countryName=object.getString("country_name");
                mCountryList.setCountry_name(countryName);
                mCountryList.setNetwork_code(object.getString("network_code"));
                mCountryList.setMobile_country_code(object.getString("mobile_country_code"));
                JSONArray mJsonArray = object.getJSONArray("opertors");

                ArrayList<Operator> allOperators = new ArrayList<>();
                Map<String,Operator> mapValues = new HashMap<String,Operator>();
                for (int index = 0; index < mJsonArray.length(); index++) {
                    JSONObject objectOperator = mJsonArray.getJSONObject(index);
                    mOperator = new Operator();
                    mOperator.setCharge_code(objectOperator.getString("charge_code"));
                    mOperator.setCheck_balance(objectOperator.getString("check_balance"));
                    mOperator.setCustomer_service(objectOperator.getString("customer_service"));
                    mOperator.setOperator_id(objectOperator.getString("operator_id"));
                    mOperator.setOperator_logo(objectOperator.getString("operator_logo"));
                    mOperator.setOperator_name(objectOperator.getString("operator_name"));
                    mOperator.setMobile_network_code(objectOperator.getString("mobile_network_code"));
                    String name =objectOperator.getString("operator_name");
                    mapValues.put(name, mOperator);



                }

                for (Map.Entry<String, Operator> entry : mapValues.entrySet()) {
                    String key = entry.getKey();
                    Operator value = entry.getValue();
                    allOperators.add(value);
                }


                mCountryList.setAllOperator(allOperators);
                if (mapValuesCountryList.containsKey(countryName)) {
                    //key exists
                } else {
                    //key does not exists
                    mapValuesCountryList.put(countryName,mCountryList);
                    AllCountryList.setCountryList(mCountryList);
                }


            }
        }

        return true;
    }

}
