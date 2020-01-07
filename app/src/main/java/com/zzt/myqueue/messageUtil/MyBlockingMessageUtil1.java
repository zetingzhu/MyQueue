package com.zzt.myqueue.messageUtil;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.zzt.myqueue.entity.MyQueueMessage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: zeting
 * @date: 2019/12/27
 * 创建一个 队列，来用来处理各种消息
 * 这个方法废弃，使用阻塞队列问题比较多，数据抓取不及时，还会导致主线程阻塞
 */
@Deprecated
public class MyBlockingMessageUtil1 {
    private final static String TAG = MyBlockingMessageUtil1.class.getSimpleName();
    // 单例对象
    private volatile static MyBlockingMessageUtil1 instance;
    // 阻塞队列
    private PriorityBlockingQueue<MyQueueMessage> queue;
    // 默认队列容量
    private final static int DEFAULT_CAPACITY = 20;
    // 消息回调监听
    private MyMessageCallback myCallback;
    // 操作主线程
    private final static int MESSAGE_WHAT = 0x0020;
    // 主线程操作handler
    private Handler mainHandler;
    // 创建一个阻塞线程池
    private ExecutorService executorService;

    private MyBlockingMessageUtil1() {
        queue = new PriorityBlockingQueue<>(DEFAULT_CAPACITY, new MyQueueMessage());
        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == MESSAGE_WHAT) {
                    if (myCallback != null) {
                        Log.i(TAG, "数据回传给接口了：" + msg.toString());
                        boolean boo = myCallback.callMessage((MyQueueMessage) msg.obj, new MyQueueCallback() {
                            @Override
                            public void callQueue(boolean goOn) {
                                // 如果返回的是true 就继续循环队列，如果不是就结束了
                                Log.i(TAG, "对话框 消失 回传状态：" + goOn);
                                if (goOn) {
                                    // 打印一下当前线程的线程信息
                                    logExecutor(executorService);
                                    // 这个地方要切换线程，不能够阻塞主线程
                                    executorService.execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            startLoop();
                                        }
                                    });
                                }
                            }
                        });
                        /**
                         *  如果返回的是true 就继续循环队列，如果不是就结束了
                         *  先去掉这个逻辑，别在给两边，我就没法控制了，目前没有找到判断队列是否有一个阻塞状态的判断
                         */
//                    if (boo) {
//                        loopMessage();
//                    }
                    } else {
                        //todo 当没有监听接口的时候，这个就需要自己来处理了

                    }
                }
            }
        };

        executorService = newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                startLoop();
            }
        });
    }

    public static MyBlockingMessageUtil1 getSingleton() {
        if (instance == null) {
            synchronized (MyBlockingMessageUtil1.class) {
                if (instance == null) {
                    instance = new MyBlockingMessageUtil1();
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
        Message message = mainHandler.obtainMessage(MESSAGE_WHAT, loopMessage());
        mainHandler.sendMessage(message);
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
        queue.offer(myQueueMessage);
        // 如果当前队列容量超容，直接丢弃最不重要的
        if (queue.size() > DEFAULT_CAPACITY) {
//            List<MyQueueMessage> mList = new ArrayList<>();
//            queue.drainTo(mList, DEFAULT_CAPACITY);
//            queue.clear();
//            queue.addAll(mList) ;
            return;
        }
        Log.d(TAG, "当前队列容量：" + queue.size());
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
        // 将添加数据放在其他线程中，防止放入数据太多的时候容易阻塞放入的线程
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
        // 如果当前队列容量超容，直接丢弃最不重要的
        if (queue.size() > DEFAULT_CAPACITY) {
            return;
        }
        // 数据 添加到队列中
        queue.offer(myQueueMessage);
        Log.d(TAG, "当前队列容量：" + queue.size());
//            }
//        });
        // 加载完成后，启动一下队列
//        startLoop();
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
        try {
            queueMessage = queue.take();
            Log.i(TAG, queueMessage.toString() + " - 当前剩余线队列：" + queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return queueMessage;
    }

    /**
     * 用于辅助线程切换的阻塞队列
     *
     * @return
     */
    private synchronized ExecutorService newCachedThreadPool() {
        // 创建一个小点线程池
        return new ThreadPoolExecutor(5, 10,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }


    /**
     * 日志打印
     *
     * @param es
     */
    public void logExecutor(ExecutorService es) {
        ThreadPoolExecutor tpe = ((ThreadPoolExecutor) es);
        int queueSize = tpe.getQueue().size();
        int activeCount = tpe.getActiveCount();
        long completedTaskCount = tpe.getCompletedTaskCount();
        long taskCount = tpe.getTaskCount();
        Log.i(TAG, "线程池信息 - 总线程数：" + taskCount + " - 当前活动线程数：" + activeCount + " - 当前排队线程数：" + queueSize + " - 执行完成线程数：" + completedTaskCount);
    }


    /**
     * 打印队列信息
     */
    public void logQueueMessage() {
        Log.i(TAG, "打印一下当前队列中的信息：" + queue.size() + " - " + queue.toString());
    }

}
