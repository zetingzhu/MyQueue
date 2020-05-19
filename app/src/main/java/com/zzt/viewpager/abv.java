package com.zzt.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import okhttp3.OkHttpClient;

/**
 * @author: zeting
 * @date: 2020/4/20
 */
public class abv extends View {

    OkHttpClient okHttpClient;

    public abv(Context context) {
        super(context);
    }

    public abv(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public abv(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abv(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
