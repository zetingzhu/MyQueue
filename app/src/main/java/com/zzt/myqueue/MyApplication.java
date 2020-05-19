package com.zzt.myqueue;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author: zeting
 * @date: 2019/12/28
 */
public class MyApplication extends Application {
    private static MyApplication mApplication;

    public static MyApplication getInstance() {
        return mApplication;
    }

    private static boolean IS_DEBUG = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        if (IS_DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(mApplication); // 尽可能早，推荐在Application中初始化
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
