package com.example.thinkpad.t47checklisthead;


import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
 * 9. show u how to write file in Android.
 * 11. show u how to find screen size
 * 12. show u how to use view stub
 * 13. show u how to use nfc on samsung
 * 14. test ThreadLocal
 * 15。 姿态识别，毕业设计相关代码
 * 16. bind MonitorService now, but MonitorService has thread problem in one test.
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private boolean mIsBind = false;
    private boolean mIsConnected = false;
    private MainService mMainService;
    private final int NOTIFICATION_ID = 98;
    private boolean mIsForegroundService = false;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMainService = ((MainService.ServiceHelp)service).getMainService();
            if (mMainService != null) {
                MainActivity.this.mIsConnected = true;
                Log.e(TAG, "onServiceConnected: " );
            } else {
                new Throwable("Failed to Connect service");
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            MainActivity.this.mIsConnected = false;
            Log.e(TAG, "Service disconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (null == startService(new Intent(MainActivity.this, MainService.class))) {
            new Throwable("onStart: can't start service here");
        }
        mIsBind = bindService(new Intent(MainActivity.this, MainService.class), mConnection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mIsConnected && mIsForegroundService) {
            mMainService.stopForeground(true);
            mIsForegroundService = false;
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
        Log.e(TAG, "onStop: isBind:  " + mIsBind + " is fore ground: " + mIsForegroundService );
        if (mIsBind && mMainService != null && !mIsForegroundService) {
            Log.e(TAG, "onStop: isBind:  " + mIsBind + " is fore ground: " + mIsForegroundService );
            mMainService.startForeground(NOTIFICATION_ID, getNotification());
            mIsForegroundService = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

    private Notification getNotification() {
        Notification.Builder mBuilder = new Notification.Builder(MainActivity.this);
        mBuilder.setShowWhen(false);
        mBuilder.setAutoCancel(false);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setLargeIcon(((BitmapDrawable)getDrawable(R.drawable.notification_drawable)).getBitmap());
        mBuilder.setContentText("this is content");
        mBuilder.setContentTitle("this is title");
        return mBuilder.build();
    }

}

