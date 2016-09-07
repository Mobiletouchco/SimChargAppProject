package com.mobiletouchco.parser;

import android.content.Context;

import com.mobiletouchco.holder.AllCountryList;
import com.mobiletouchco.model.CountryList;
import com.mobiletouchco.model.Operator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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
        final JSONObject json_ob = new JSONObject(result);
        int success = json_ob.getInt("success");

        if (success == 1) {
            final JSONArray JsonArray = json_ob.getJSONArray("results");

            for (int i = 0; i < JsonArray.length(); i++) {
                JSONObject object = JsonArray.getJSONObject(i);
                mCountryList = new CountryList();
                mCountryList.setCountry_code(object.getString("country_code"));
                mCountryList.setCountry_id(object.getString("country_id"));
                mCountryList.setCountry_name(object.getString("country_name"));
                mCountryList.setNetwork_code(object.getString("network_code"));

                JSONArray mJsonArray = object.getJSONArray("opertors");

                ArrayList<Operator> allOperators = new ArrayList<>();

                for (int index = 0; index < mJsonArray.length(); index++) {
                    JSONObject objectOperator = mJsonArray.getJSONObject(index);
                    mOperator = new Operator();
                    mOperator.setCharge_code(objectOperator.getString("charge_code"));
                    mOperator.setCheck_balance(objectOperator.getString("check_balance"));
                    mOperator.setCustomer_service(objectOperator.getString("customer_service"));
                    mOperator.setOperator_id(objectOperator.getString("operator_id"));
                    mOperator.setOperator_logo(objectOperator.getString("operator_logo"));
                    mOperator.setOperator_name(objectOperator.getString("operator_name"));
                    allOperators.add(mOperator);

                }

                mCountryList.setAllOperator(allOperators);
                AllCountryList.setCountryList(mCountryList);

            }
        }

        return true;
    }

}
