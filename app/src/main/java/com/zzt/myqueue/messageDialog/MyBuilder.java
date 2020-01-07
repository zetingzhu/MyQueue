package com.zzt.myqueue.messageDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * @author: zeting
 * @date: 2019/12/30
 */
public class MyBuilder extends MyAlertDialog.Builder {

    private final static String TAG = MyBuilder.class.getSimpleName();
    public MyBuilder(Context context) {
        super(context);
    }

    public MyBuilder(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public AlertDialog create() {
        return super.create();
    }

    @Override
    public AlertDialog show() {
        return super.show();
    }
}
