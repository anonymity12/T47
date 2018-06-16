package com.example.thinkpad.t47checklisthead;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by paul on 2018/6/16
 * last modified at 11:50 AM.
 * Desc:
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";

    String ACTION = "github.nisrulz.action.MY_ACTION";


    public MyBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive:!! ");
        if (intent != null && intent.getAction().equals(ACTION)) {
            String data = intent.getStringExtra("data");
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
        }
    }
}
