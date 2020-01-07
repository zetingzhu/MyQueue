package com.zzt.myqueue.messageDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.text.method.MovementMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.ArrayRes;
import androidx.annotation.AttrRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.zzt.myqueue.MainActivity;
import com.zzt.myqueue.R;


/**
 * @author: zeting
 * @date: 2019/12/27
 */
public class MyAlertDialog extends AlertDialog {

    private final static String TAG = MyAlertDialog.class.getSimpleName();

    protected MyAlertDialog(Context context) {
        super(context);
    }

    protected MyAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected MyAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected void onStop() {
        Log.d(TAG, " 这个地方有没有监听到返回监听 MyAlertDialog onStop");
        super.onStop();
        Log.d(TAG, " 这个地方有没有监听到返回监听 MyAlertDialog onStop");
    }


    @Override
    public void onBackPressed() {
        Log.d(TAG, " 这个地方有没有监听到返回监听 onBackPressed");
        super.onBackPressed();
    }

    @Override
    public void create() {
        super.create();
        Log.d(TAG, " 这个地方有没有监听到返回监听 create");
    }

    @Override
    public void show() {
        super.show();
        Log.d(TAG, " 这个地方有没有监听到返回监听 show");
    }

    @Override
    public void hide() {
        Log.d(TAG, " 这个地方有没有监听到返回监听 hide");
        super.hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, " 这个地方有没有监听到返回监听 onStart");
    }

    @Override
    public void cancel() {
        super.cancel();
        Log.d(TAG, " 这个地方有没有监听到返回监听 cancel");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, " 这个地方有没有监听到返回监听 onCreate  1");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, " 这个地方有没有监听到返回监听 onKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d(TAG, " 这个地方有没有监听到返回监听 onKeyUp");
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void dismiss() {
        Log.d(TAG, " 这个地方有没有监听到返回监听 MyAlertDialog ");
        super.dismiss();
        Log.d(TAG, " 这个地方有没有监听到返回监听 MyAlertDialog ");
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {

        super.setOnDismissListener(listener);
    }
}
