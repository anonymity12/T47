package com.example.thinkpad.t47checklisthead;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";



    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive:!! ");
        Toast.makeText(context, "some thing", Toast.LENGTH_SHORT).show();

    }
}
