package com.omka.mackhaton.common;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

public class ViewUtils {
    public static Snackbar getSnackbar(Activity activity, String message) {
        int marginBottom = SizeUtils.getNavigationBarSize(activity).y;
        Snackbar snackbar = Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
        final View snackBarView = snackbar.getView();
        snackBarView.setPadding(
                snackBarView.getPaddingLeft(),
                snackBarView.getPaddingTop(), snackBarView.getPaddingRight(),
                snackBarView.getPaddingBottom() + marginBottom);
        return snackbar;
    }
    public static Snackbar getSnackbarWithAction(Activity activity, String message,String actionTitle, View.OnClickListener listener) {
        return getSnackbar(activity,message).setAction(actionTitle, listener);
    }

    public static DividerItemDecoration getDividerItemDecoration(Context context, int orientation){
        return new DividerItemDecoration(context,orientation);
    }

}