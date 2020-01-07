package com.zzt.myqueue.messageUtil;

import androidx.annotation.NonNull;

import com.zzt.myqueue.entity.MyQueueMessage;

/**
 * @author: zeting
 * @date: 2019/12/27
 * 从队列中取出消息通过接口方法发送给返回
 */
public interface MyMessageCallback {

    /**
     * 返回消息给线程处理，如果直接return true ,队列继续执行 , queueCallback 给true 也将继续执行队列
     *
     * @param showMsg       队列中取出，显示消息
     * @param queueCallback
     * @return
     */

    boolean callMessage(@NonNull MyQueueMessage showMsg, MyQueueCallback queueCallback);
}
