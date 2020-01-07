package com.zzt.myqueue.messageDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import androidx.annotation.NonNull;

import com.zzt.myqueue.entity.MyQueueMessage;
import com.zzt.myqueue.messageUtil.MyBlockingMessageUtil;
import com.zzt.myqueue.messageUtil.MyMessageCallback;
import com.zzt.myqueue.messageUtil.MyQueueCallback;

/**
 * @author: zeting
 * @date: 2019/12/27
 */
public class MyMessageManage {
    private final static String TAG = MyMessageManage.class.getSimpleName();
    // 两个按钮
    public static final int STATUS_MESSAGE_MANAGE_TYPE_1 = 1;
    // 列表
    public static final int STATUS_MESSAGE_MANAGE_TYPE_2 = 2;
    // 单选
    public static final int STATUS_MESSAGE_MANAGE_TYPE_3 = 3;


    public static Dialog showDialogType(Context context, MyQueueMessage showMsg, MyQueueCallback queueCallback) {
        Dialog mDialog = null;
        switch (showMsg.getType()) {
            case STATUS_MESSAGE_MANAGE_TYPE_1:
                mDialog = DialogUtil.shwoDialog(context, queueCallback);
                break;
            case STATUS_MESSAGE_MANAGE_TYPE_2:
                mDialog = DialogUtil.shwoDialogList(context, queueCallback);
                break;
            case STATUS_MESSAGE_MANAGE_TYPE_3:
                mDialog = DialogUtil.shwoDialogSingle(context, queueCallback);
                break;
        }
        return mDialog;
    }


    public static void setMessageTypeDataDialog(final Context context, MyQueueMessage queueMessage) {
        MyMessageCallback myMessageCallback = new MyMessageCallback() {
            @Override
            public boolean callMessage(@NonNull MyQueueMessage showMsg, final MyQueueCallback queueCallback) {
                Dialog dialog = showDialogType(context, showMsg, queueCallback);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        queueCallback.callQueue(true);
                    }
                });
                return false;
            }
        };
        MyBlockingMessageUtil.getSingleton().putData(queueMessage, myMessageCallback);
    }

    public static void setMessageTypeData(final Context context, MyQueueMessage myQueueMessage) {
        setMessageTypeDataDialog(context, myQueueMessage);
    }

    @Deprecated
    public static void setMessageTypeData1(final Context context, MyQueueMessage myQueueMessage) {
        MyBlockingMessageUtil.getSingleton().putData(myQueueMessage, new MyMessageCallback() {
            @Override
            public boolean callMessage(@NonNull MyQueueMessage msg, MyQueueCallback queueCallback) {
                Log.w(TAG, "当前显示对话框类型是什么：" + msg.getType());
                if (context != null) {
                    switch (msg.getType()) {
                        case STATUS_MESSAGE_MANAGE_TYPE_1:
                            DialogUtil.shwoDialog(context, queueCallback);
                            break;
                        case STATUS_MESSAGE_MANAGE_TYPE_2:
                            DialogUtil.shwoDialogList(context, queueCallback);
                            break;
                        case STATUS_MESSAGE_MANAGE_TYPE_3:
                            DialogUtil.shwoDialogSingle(context, queueCallback);
                            break;
                        default:
                            DialogUtil.shwoDialog(context, queueCallback);
                            break;
                    }
                }
                return false;
            }
        });
    }


}
