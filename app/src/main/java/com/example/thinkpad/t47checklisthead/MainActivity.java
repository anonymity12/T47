package com.example.thinkpad.t47checklisthead;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
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
    public static final String TAG = "MainActivity";
    Button btn = null;
    Button btn2 = null;
    Handler handler = null;

    private boolean isBind;
    private ServiceConnection ServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //cast an IBinder object into a interface we can use
            iUser = IUser.Stub.asInterface(service);
            isBind = true;
            Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
        }
    };
    private IUser iUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_open_thread);
        btn2 = (Button) findViewById(R.id.btn_open_thread2);

        //-----------------code by jinliang-------------
        //connect the service and return our binder.
        Intent intent = new Intent();
        intent.setPackage("com.example.thinkpad.t47checklisthead");
        intent.setAction("com.sss");
        bindService(intent,ServiceConnection, Service.BIND_AUTO_CREATE);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if (mHandlerThread.mHandler != null) {
                    Message msg = new Message();
                    msg.what = 1;
                    mHandlerThread.mHandler.sendMessage(msg);
                    Log.d(TAG, ">>>>> btn2  send a msg");
                }*/
                if (isBind) {
                    try {
                        boolean login = iUser.login("abc", "aaa");
                        Log.d(TAG, login+"");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }else {

                }
            }
        });
        //-----------------code by jinliang-------------

    }


}
