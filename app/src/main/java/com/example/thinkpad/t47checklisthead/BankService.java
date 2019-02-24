package com.example.thinkpad.t47checklisthead;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.thinkpad.t47checklisthead.action.impl.NormalUserActionAIDLImpl;

/**
 * Created by paul on 2019/2/24
 * last modified at 22:22.
 * Desc:
 */

public class BankService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action)) {
            if ("com.paul.ACTION_NORMAL_USER".equals(action)) {
                return new NormalUserActionAIDLImpl();
            }
        }
        return  null;
    }
}
