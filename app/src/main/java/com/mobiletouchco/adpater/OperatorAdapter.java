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
import com.mobiletouchco.model.Operator;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by prosantobiswas on 4/24/16.
 */

public class OperatorAdapter extends ArrayAdapter {
    ArrayList<Operator> allOperators;
    Context context;

    public OperatorAdapter(Context context, int textViewResourceId, ArrayList<Operator> allOperators) {
        super(context, textViewResourceId, allOperators);
        this.allOperators =allOperators;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        View row = LayoutInflater.from(context).inflate(
                R.layout.spinner_item, parent, false);
        TextView label = (TextView) row.findViewById(R.id.spinner_text);
        //label.setTypeface(myFont);
        label.setText(allOperators.get(position).getOperator_name());

        return row;
    }
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View row = LayoutInflater.from(context).inflate(
                R.layout.spinner_item, parent, false);
        TextView label = (TextView) row.findViewById(R.id.spinner_text);
        label.setText(allOperators.get(position).getOperator_name());

        return row;
    }

}
