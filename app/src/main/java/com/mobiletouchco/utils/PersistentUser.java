package com.mobiletouchco.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PersistentUser {

	private static final String USERDETAILS = "operatordetails";
	private static final String PREFS_FILE_NAME = "jenkoAppPreferences";


	public static String getOpertordetails(final Context ctx) {
		return ctx.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
				.getString(USERDETAILS, "");

	}public static void setOpertordetails(final Context ctx, final String token) {
		final SharedPreferences prefs = ctx.getSharedPreferences(
				PREFS_FILE_NAME, Context.MODE_PRIVATE);
		final Editor editor = prefs.edit();
		editor.putString(USERDETAILS, token);
		editor.commit();
	}


}
