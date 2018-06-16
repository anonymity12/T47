package com.example.thinkpad.t47checklisthead;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
/**
 * Created by paul on 2018/6/16
 * last modified at 12:01 PM.
 * Desc:
 */
public class NormalReceiver extends BroadcastReceiver {

    private static final String TAG = "NormalReceiver";

    public NormalReceiver() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: !!");
        Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
    }

}

