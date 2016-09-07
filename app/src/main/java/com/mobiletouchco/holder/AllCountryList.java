package com.mobiletouchco.holder;

import com.mobiletouchco.model.CountryList;
import com.mobiletouchco.model.Sortbycountry;

import java.util.Collections;
import java.util.Vector;

/**
 * Created by Prosanto on 9/2/16.
 */
public class AllCountryList {
    public static Vector<CountryList>allCountryList = new Vector<>();

    public static Vector<CountryList> getAllCountryList() {
        return allCountryList;
    }

    public static void setAllCountryList(Vector<CountryList> allCountryList) {
        AllCountryList.allCountryList = allCountryList;
    }
    public static CountryList getCountryList(int pos) {
        return allCountryList.elementAt(pos);
    }

    public static void setCountryList(CountryList _allCountryList) {
        AllCountryList.allCountryList.addElement(_allCountryList);
    }

    public static void removeAllCountryList() {
        AllCountryList.allCountryList.removeAllElements();
    }
    public static Vector<CountryList> getAllCountryListort() {
        Collections.sort( AllCountryList.allCountryList, new Sortbycountry());
        return  AllCountryList.allCountryList;
    }

}
