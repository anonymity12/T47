package com.example.thinkpad.t47checklisthead;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Basic activity for testings.
 * tested:
 * 1. seek bar control the panel view
 * 2. using fragment manager to use fragment.
 * 3. using circle button.
 * 4. using tab layout and view pager. Tutorial from http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0731/3247.html
 * 5. google example: Creating Swipe Views with Tabs.
 * 6. 03142018 test handler
 * 7. Test AIDL by jinliang gao
 * 8. Using messenger # Android art c2.4.3 @03192018
 */
public class MainActivity extends AppCompatActivity {
    public static final int MSG_FROM_CLIENT = 299;
    public static final int MSG_FROM_SERVICE = 300;
    public static final String TAG = "MainActivity";
    Button btn = null;
    Button btn2 = null;
    Handler handler = null;

    private Messenger mService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            //return a new Message object from the global pool
            Message msg = Message.obtain(null, MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString("msg","hello, this is a client");
            msg.setData(data);
            //-----------------code that make we can see a response from this service------------

            msg.replyTo = mGetReplyMessenger;

            //-----------------code that make we can see a response from this service------------

            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    //-----------------code that make we can see a response from our service------------
    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());
    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROM_SERVICE:
                    Log.i(TAG, "handleMessage: receive msg from Service: " + msg.getData().getString("reply"));
                    break;
            }
            super.handleMessage(msg);
        }
    }
    //-----------------code that make we can see a response from our service------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_open_thread);
        btn2 = (Button) findViewById(R.id.btn_open_thread2);
        btn.setText("send message by Messenger");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MessageService.class);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}
