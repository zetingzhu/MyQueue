package com.zzt.myqueue.messageDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import com.zzt.myqueue.entity.MyQueueMessage;
import com.zzt.myqueue.messageUtil.MyMessageCallback;
import com.zzt.myqueue.messageUtil.MyQueueCallback;

/**
 * @author: zeting
 * @date: 2019/12/27
 */
public class MyDialog extends Dialog implements MyMessageCallback {
    private final static String TAG = MyDialog.class.getSimpleName();

    // 对话框通知队列接口
    private MyQueueCallback queueCallback;

    // 对话框展示内容
    private MyQueueMessage showMessage;


    public MyDialog(@NonNull Context context) {
        super(context);
    }

    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {

        Log.d(TAG, " 这个地方有没有监听到返回监听 ");

        if (queueCallback != null) {
            queueCallback.callQueue(true);
        }
        super.setOnDismissListener(listener);
    }


    @Override
    public void dismiss() {

        super.dismiss();
    }

    @Override
    public boolean callMessage(@NonNull MyQueueMessage msg, MyQueueCallback queueCallback) {
        this.showMessage = msg;
        Log.w(TAG, "当前显示对话框类型是什么：" + msg.getType());
        return false;
    }

    public void setQueueCallback(MyQueueCallback queueCallback) {
        this.queueCallback = queueCallback;
    }

    /**
     * 获取接口数据
     *
     * @return
     */
    public MyQueueMessage getShowMessage() {
        return showMessage;
    }

}
