package com.mobiletouchco.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobiletouchco.R;
import com.mobiletouchco.holder.AllCountryList;
import com.mobiletouchco.model.CountryList;

import java.util.Vector;

/**
 * Created by prosantobiswas on 4/24/16.
 */

public class CountryAdapter extends ArrayAdapter {
    Vector<CountryList> list;
    Context context;

    public CountryAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId, AllCountryList.getAllCountryList());
        this.list =  AllCountryList.getAllCountryList();
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        View row = LayoutInflater.from(context).inflate(
                R.layout.spinner_item, parent, false);
        TextView label = (TextView) row.findViewById(R.id.spinner_text);
        //label.setTypeface(myFont);
        label.setText(list.get(position).getCountry_name());

        return row;
    }
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View row = LayoutInflater.from(context).inflate(
                R.layout.spinner_item, parent, false);
        TextView label = (TextView) row.findViewById(R.id.spinner_text);
        label.setText(list.get(position).getCountry_name());

        return row;
    }

}
