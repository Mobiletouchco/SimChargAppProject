package com.mobiletouchco.model;

import java.util.Comparator;


public class Sortbycountry implements Comparator<CountryList> {

	public int compare(CountryList arg0, CountryList arg1) {
		// TODO Auto-generated method stub
		final String emp1Age = arg0.getCountry_name().toString().trim();
		final String emp2Age = arg1.getCountry_name().toString().trim();

		if (emp1Age.compareTo(emp2Age) >= 0) {
			return 1;
		} else if (emp1Age.compareTo(emp2Age) < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}
