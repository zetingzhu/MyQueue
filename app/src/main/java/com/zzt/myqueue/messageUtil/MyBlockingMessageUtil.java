package com.zzt.myqueue.messageUtil;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.zzt.myqueue.MyApplication;
import com.zzt.myqueue.entity.MyQueueMessage;
import com.zzt.myqueue.messageDialog.MyAlertDialog;
import com.zzt.myqueue.messageDialog.MyDialog;
import com.zzt.myqueue.messageDialog.MyMessageManage;

import java.util.LinkedList;

/**
 * @author: zeting
 * @date: 2019/12/27
 * 创建一个 队列，来用来处理各种消息
 */
public class MyBlockingMessageUtil {
    private final static String TAG = MyBlockingMessageUtil.class.getSimpleName();
    // 单例对象
    private volatile static MyBlockingMessageUtil instance;
    // 链表队列
    private LinkedList<MyQueueMessage> queue;
    // 默认队列容量
    private final static int DEFAULT_CAPACITY = 20;
    // 消息回调监听
    private MyMessageCallback myCallback;
    // 操作主线程
    private final static int MESSAGE_WHAT = 0x0020;
    // 主线程操作handler
    private Handler mainHandler;
    // 当前取出的message对象
    private MyQueueMessage showMsg;
    // 锁
    private Object lock = new Object();
    // 传入当前有的context 对象
    private Context mContext;


    private MyBlockingMessageUtil() {
        queue = new LinkedList<>();
        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == MESSAGE_WHAT) {
                    showMsg = (MyQueueMessage) msg.obj;
                    if (myCallback != null) {
                        Log.i(TAG, "数据回传给接口了：" + msg.toString());

                        myCallback.callMessage(showMsg, new MyQueueCallback() {
                            @Override
                            public void callQueue(boolean goOn) {
                                // 如果返回的是true 就继续循环队列，如果不是就结束了
                                Log.i(TAG, "对话框 消失 回传状态：" + goOn);
                                if (goOn) {
                                    showMsg = null;
                                    startLoop();
                                }
                            }
                        });
                    } else {
                        //todo 当没有监听接口的时候，这个就需要自己来处理了
//                        if (showMsg != null) {
//                            mContext = mContext == null ? MyApplication.getInstance().getBaseContext() : mContext;
//                            MyDialog myDialog = MyMessageManage.showDialogType(MyApplication.getInstance().getBaseContext(), showMsg, null);
//                            myDialog.setQueueCallback(new MyQueueCallback() {
//                                @Override
//                                public void callQueue(boolean goOn) {
//                                    Log.i(TAG, "对话框 消失 回传状态：" + goOn);
//                                    if (goOn) {
//                                        showMsg = null;
//                                        startLoop();
//                                    }
//                                }
//                            });
//                        }
                    }
                }
            }
        };
    }

    public static MyBlockingMessageUtil getSingleton() {
        if (instance == null) {
            synchronized (MyBlockingMessageUtil.class) {
                if (instance == null) {
                    instance = new MyBlockingMessageUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 开始循环
     */
    public void startLoop() {
        Log.i(TAG, "获取队列剩余第一条信息 队列开始" + queue.size());
        MyQueueMessage msg = loopMessage();
        if (msg != null) {
            Message message = mainHandler.obtainMessage(MESSAGE_WHAT, msg);
            mainHandler.sendMessage(message);
        }
    }

    /**
     * 获取当前队列个数
     *
     * @return
     */
    public int getSize() {
        if (queue != null) {
            return queue.size();
        }
        return 0;
    }

    /**
     * 将数据放入到队列中
     *
     * @param myQueueMessage
     */
    public void putData(MyQueueMessage myQueueMessage) {
        synchronized (lock) {
            // 如果当前队列容量超容，直接丢弃最不重要的
            if (queue.size() > DEFAULT_CAPACITY) {
                return;
            }
            queue.offer(myQueueMessage);
            Log.d(TAG, "当前队列容量：" + queue.size());
            if (showMsg == null) {
                // 加载完成后，当前数据为空就启动一下队列
                startLoop();
            }
        }
    }


    /**
     * 传入当前的context 对象
     *
     * @param myQueueMessage
     * @param context
     */
    public void putData(MyQueueMessage myQueueMessage, Context context) {
        this.mContext = context;
        putData(myQueueMessage);
    }

    /**
     * 将数据放入到队列比，并且加入监听对象
     *
     * @param myQueueMessage
     * @param myMessageCallback
     */
    public void putData(MyQueueMessage myQueueMessage, MyMessageCallback myMessageCallback) {
        if (myCallback != null) {
            // 移除到原来的监听对象
            myCallback = null;
        }
        myCallback = myMessageCallback;
        putData(myQueueMessage);
    }

    /**
     * 在当前线程添加监听
     *
     * @param myCallback
     */
    public void setMyCallback(MyMessageCallback myCallback) {
        this.myCallback = myCallback;
    }

    /**
     * 移除当前线程监听
     */
    public void removeMyCallback() {
        this.myCallback = null;
    }


    /**
     * 获取当前队列消息第一条消息
     *
     * @return
     */
    private MyQueueMessage loopMessage() {
        MyQueueMessage queueMessage = null;
        queueMessage = queue.pollFirst();
        if (queueMessage != null) {
            Log.i(TAG, queueMessage.toString() + " - 当前剩余线队列：" + queue.size());
        }
        return queueMessage;
    }


    /**
     * 打印队列信息
     */
    public void logQueueMessage() {
        Log.i(TAG, "打印一下当前队列中的信息：" + queue.size() + " - " + queue.toString());
    }

}
