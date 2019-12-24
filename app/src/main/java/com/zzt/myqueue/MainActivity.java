package com.zzt.myqueue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.PriorityBlockingQueue;

public class MainActivity extends AppCompatActivity {


    PriorityBlockingQueue queue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
