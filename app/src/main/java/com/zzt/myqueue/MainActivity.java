package com.zzt.myqueue;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zzt.myqueue.entity.MyQueueMessage;
import com.zzt.myqueue.messageDialog.MyAlertDialog;
import com.zzt.myqueue.messageDialog.MyMessageManage;
import com.zzt.myqueue.messageUtil.MyBlockingMessageUtil;
import com.zzt.test.ConfigARouter;

import java.util.Random;

@Route(path = ConfigARouter.MAIN_ACTIVITY)
public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();


    ViewPager2 viewpager2;
    ViewPager viewpager1;

    Random random = new Random();
//    PriorityBlockingQueue<MyQueueMessage> queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ARouter.getInstance().inject(this);


        String t1 = getIntent().getExtras().getString("t1");
        String t2 = getIntent().getExtras().getString("t2");


        Log.e(TAG, "t1:" + t1 + " - t2：" + t2);

        initView();

//        DialogUtil.shwoDialog(this);

        final String[] gender = new String[]{"好像是哦", "没看出来"};
        MyAlertDialog.Builder builder1 = new MyAlertDialog.Builder(this);
        builder1.setTitle("类型为   2  列表");
        builder1.setItems(gender, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder1.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.d(TAG, " 这个地方有没有监听到返回监听 alert ");
            }
        });
        builder1.show();


//        DialogUtil.shwoDialogList(this);

//        try {
//            Thread.sleep(500);
//            // 发送一个确定对话框
//            MyMessageManage.setMessageTypeDataDialog(this, new MyQueueMessage(MyMessageManage.STATUS_MESSAGE_MANAGE_TYPE_1));
//            Thread.sleep(500);
//            // 发送一个列表
//            MyMessageManage.setMessageTypeDataDialog(this, new MyQueueMessage(MyMessageManage.STATUS_MESSAGE_MANAGE_TYPE_2));
//            Thread.sleep(500);
//            // 发送一个单选
//            MyMessageManage.setMessageTypeDataDialog(this, new MyQueueMessage(MyMessageManage.STATUS_MESSAGE_MANAGE_TYPE_3));
//            // 添加完成，打印一下队列信息
//            MyBlockingMessageUtil.getSingleton().logQueueMessage();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        queue = new PriorityBlockingQueue<>(20, new MyQueueMessage());
//        Log.i(TAG, "这个会走几遍");


//        MyBlockingMessageUtil.getSingleton().setMyCallback(new MyMessageCallback() {
//            @Override
//            public boolean callMessage(@NonNull MyQueueMessage msg, MyQueueCallback queueCallback) {
//                Log.d(TAG, msg.toString() + " - 当前剩余线队列：" + MyBlockingMessageUtil.getSingleton().getSize());
//                return false;
//            }
//        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.w(TAG, "开始添加队列：" + System.currentTimeMillis());
//                for (int i = 0; i < 30; i++) {
//                    try {
//                        Thread.sleep(2000);
//
//                        MyQueueMessage myQueueMessage = new MyQueueMessage();
//                        myQueueMessage.setId("" + i);
//                        myQueueMessage.setName("" + i + "-" + System.currentTimeMillis());
//                        myQueueMessage.setLevel(random.nextInt(10));
//////                        queue.put(myQueueMessage);
////                        queue.offer(myQueueMessage);
//
//                        MyBlockingMessageUtil.getSingleton().putData(myQueueMessage);
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
////                    if (queue != null && queue.size()==1) {
////                        nextMessage();
////                    }
//                }
//
//                Log.w(TAG, "结束添加队列：" + System.currentTimeMillis());
//
//            }
//        }).start();


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //开始处理消息
//                nextMessage();
//            }
//        }, 5000);
    }


    /**
     * 添加测试数据
     */
    public void addTestData(View v) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    try {
                        Thread.sleep(1000);
                        // 发送一个确定对话框
                        MyMessageManage.setMessageTypeData(MainActivity.this, new MyQueueMessage(MyMessageManage.STATUS_MESSAGE_MANAGE_TYPE_1));
                        Thread.sleep(2000);
                        // 发送一个列表
                        MyMessageManage.setMessageTypeData(MainActivity.this, new MyQueueMessage(MyMessageManage.STATUS_MESSAGE_MANAGE_TYPE_2));
                        Thread.sleep(3000);
                        // 发送一个单选
                        MyMessageManage.setMessageTypeData(MainActivity.this, new MyQueueMessage(MyMessageManage.STATUS_MESSAGE_MANAGE_TYPE_3));
                        // 添加完成，打印一下队列信息
                        MyBlockingMessageUtil.getSingleton().logQueueMessage();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void addTestDataOne(View v) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                // 发送一个确定对话框
                MyMessageManage.setMessageTypeData(MainActivity.this, new MyQueueMessage(MyMessageManage.STATUS_MESSAGE_MANAGE_TYPE_1));
            }
        });
    }

    public void addTestDataOneList(View v) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                // 发送一个确定对话框
                MyMessageManage.setMessageTypeData(MainActivity.this, new MyQueueMessage(MyMessageManage.STATUS_MESSAGE_MANAGE_TYPE_1));
                // 发送一个列表
                MyMessageManage.setMessageTypeData(MainActivity.this, new MyQueueMessage(MyMessageManage.STATUS_MESSAGE_MANAGE_TYPE_2));
                // 发送一个单选
                MyMessageManage.setMessageTypeData(MainActivity.this, new MyQueueMessage(MyMessageManage.STATUS_MESSAGE_MANAGE_TYPE_3));
                // 添加完成，打印一下队列信息
                MyBlockingMessageUtil.getSingleton().logQueueMessage();
            }
        });

    }
//    public void nextMessage() {
//
////        Log.d(TAG, "获取队列中剩余的空间：" + queue.remainingCapacity());
//
//        MyQueueMessage queueMessage = null;
//        try {
//            queueMessage = queue.take();
//            if (queueMessage == null) {
//                return;
//            }
//            Log.d(TAG, queueMessage.toString() + " - 当前剩余线队列：" + queue.size());
//            try {
//                Thread.sleep(300);
//                nextMessage();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//    }

    private void initView() {
    }

}
