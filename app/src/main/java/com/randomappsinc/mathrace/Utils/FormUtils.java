package com.randomappsinc.mathrace.Utils;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.randomappsinc.mathrace.R;

/**
 * Created by alexanderchiou on 12/5/15.
 */
public class FormUtils {
    public static void showSnackbar(View parent, String message) {
        Context context = MyApplication.get().getApplicationContext();
        Snackbar snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_LONG);
        View rootView = snackbar.getView();
        snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.app_blue));
        TextView tv = (TextView) rootView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbar.show();
    }
}
