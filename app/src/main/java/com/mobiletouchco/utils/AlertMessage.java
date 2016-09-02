package com.mobiletouchco.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


public class AlertMessage {
    /*
	 * show alert dialog
	 * P: context, title and message
	 * 
	 */

    public static void showMessage(final Context c, final String title,
                                   final String message) {
        final AlertDialog.Builder aBuilder = new AlertDialog.Builder(c);
        aBuilder.setTitle(title);
        //aBuilder.setIcon(R.drawable.ic_launcher);
        aBuilder.setMessage(message);

        aBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.dismiss();
            }

        });

        aBuilder.show();
    }
	/*
	 * show alert dialog
	 * P: context, title and message
	 * 
	 */

    public static void showMessage(final Context c, int icon, final String title,
                                   final String message) {
        final AlertDialog.Builder aBuilder = new AlertDialog.Builder(c);
        aBuilder.setTitle(title);
        //aBuilder.setIcon(R.drawable.ic_launcher);
        aBuilder.setMessage(message);

        aBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                dialog.dismiss();
            }

        });

        aBuilder.show();
    }

	/*
	 * overloaded method
	 * with button text
	 * 
	 */

    public static void showMessage(final Context c, final String title,
                                   final String message, String buttonText) {
        final AlertDialog.Builder aBuilder = new AlertDialog.Builder(c);
        aBuilder.setTitle(title);
        //aBuilder.setIcon(R.drawable.ic_launcher);
        aBuilder.setMessage(message);

        aBuilder.setPositiveButton(buttonText,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog,
                                        final int which) {
                        dialog.dismiss();
                    }

                });

        aBuilder.show();
    }

    /*
     * overloaded method
     * with icon
     *
     */
    public static void showMessage(final Context c, final String title,
                                   final String message, String buttonText, int icon) {
        final AlertDialog.Builder aBuilder = new AlertDialog.Builder(c);
        aBuilder.setTitle(title);
        aBuilder.setIcon(icon);
        aBuilder.setMessage(message);

        aBuilder.setPositiveButton(buttonText,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog,
                                        final int which) {
                        dialog.dismiss();
                    }

                });

        aBuilder.show();
    }

}
