package com.example.thinkpad.t47checklisthead;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MainService extends Service {
    private ServiceHelp mHelper = new ServiceHelp();
    public class ServiceHelp extends Binder {
        public MainService getMainService(){
            return MainService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mHelper;
    }
}
