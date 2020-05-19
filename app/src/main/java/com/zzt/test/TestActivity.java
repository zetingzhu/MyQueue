package com.zzt.test;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zeting
 * @date: 2020/4/26
 */
@Route(path = "/test/testActivity")
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        Button textView = new Button(this);
        textView.setText("main");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("t1", "tt11");
                ARouter.getInstance().build(ConfigARouter.MAIN_ACTIVITY)
                        .with(bundle)
                        .withString("t2", "tt22")
                        .navigation();


            }
        });
        linearLayout.addView(textView);

        Button textView1 = new Button(this);
        textView1.setText("viewpager");
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ARouter.getInstance().build(ConfigARouter.ACTIVITY_VIEWPAGER).navigation();

//                Uri uri = Uri.parse(ConfigARouter.ACTIVITY_VIEWPAGER);
//                ARouter.getInstance().build(uri).navigation();


                ARouter.getInstance().build(ConfigARouter.ACTIVITY_VIEWPAGER).navigation(TestActivity.this, new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {

                    }

                    @Override
                    public void onLost(Postcard postcard) {

                    }

                    @Override
                    public void onArrival(Postcard postcard) {

                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {

                    }
                });
            }
        });
        linearLayout.addView(textView1);

        setContentView(linearLayout);


        ARouter.getInstance().inject(this);

        init();
    }

    private void init() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 200; i++) {
            map.put(i, "添加：" + i);
        }
    }
}
