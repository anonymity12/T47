package com.example.thinkpad.t47checklisthead;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Basic activity for testings.
 * tested:
 * 1. seek bar control the panel view
 * 2. using fragment manager to use fragment.
 * 3. using circle button.
 * 4. using tab layout and view pager. Tutorial from http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0731/3247.html
 * 5. google example: Creating Swipe Views with Tabs.
 * 6. 03142018 test handler
 * 7. 03152018 test HandlerThread
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MyHT";
    Button btn = null;
    Button btn2 = null;
    Handler handler = null;
    MyHandlerThread mHandlerThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_open_thread);
        btn2 = (Button) findViewById(R.id.btn_open_thread2);
        Log.d(TAG, Looper.myLooper().toString());
        Log.d(TAG, "should same as above >>  " + Looper.getMainLooper().toString());


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandlerThread = new MyHandlerThread("onStartHandlerThread");
                Log.d(TAG, "创建myHandlerThread对象");
                mHandlerThread.start();
                Log.d(TAG, "start上述的HandlerThread");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mHandlerThread.mHandler != null) {
                    Message msg = new Message();
                    msg.what = 1;
                    mHandlerThread.mHandler.sendMessage(msg);
                    Log.d(TAG, ">>>>> btn2  send a msg");
                }

            }
        });
    }

    class MyHandlerThread extends HandlerThread {



        public static final String TAG = "MyHT";

        public Handler mHandler = null;

        public MyHandlerThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            Log.d(TAG, "进入Thread的run");
            if (Looper.myLooper() == null) {
                Looper.prepare();
                Log.d(TAG, "Looper.prepare() executed");
                mHandler = new Handler(Looper.myLooper()){
                    @Override
                    public void handleMessage(Message msg) {
                        Log.d(TAG, ">>>> handler got msg");
                        super.handleMessage(msg);
                    }
                };
            }
        }
    }

}
