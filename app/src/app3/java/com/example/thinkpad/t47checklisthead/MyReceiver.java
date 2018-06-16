package com.example.thinkpad.t47checklisthead;

/**
 * Created by paul on 2018/6/16
 * last modified at 11:07 AM.
 * Desc:
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent!=null && intent.getAction().equals("com.abc.mydata")){
            Log.d("App3","From App3 Receiver : " +intent.getStringExtra("data"));
            Toast.makeText(context,"From App3 Receiver : " +intent.getStringExtra("data"),Toast
                    .LENGTH_LONG).show();
        }
    }
}

