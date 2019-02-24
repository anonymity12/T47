package com.example.thinkpad.t47checklisthead;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    NormalUserConnection mNormalUserConnection;
    private NormalUserAction mNormalUserAction;
    boolean isBind;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: !!!");
        setContentView(R.layout.activity_main);
        doBindService();

    }
    private void doBindService() {
        Log.d(TAG, "doBindService: ...");
        Intent i = new Intent();
        i.setAction("com.paul.ACTION_NORMAL_USER");
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setPackage("com.example.thinkpad.t47checklisthead");

        mNormalUserConnection = new NormalUserConnection();
        isBind = bindService(i, mNormalUserConnection, BIND_AUTO_CREATE);

    }



    private class NormalUserConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");
            // 拿到了 iBinder
            mNormalUserAction = NormalUserAction.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    }

    public void saveMoneyClick(View view) throws RemoteException {
        Log.d(TAG, "saveMoneyClick: ");
        mNormalUserAction.saveMoney(1200);
    }
    public void getMoneyClick(View view) throws RemoteException {
        float moneyGet = mNormalUserAction.getMoney();
        Log.d(TAG, "getMoneyClick: money is " + moneyGet);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}

