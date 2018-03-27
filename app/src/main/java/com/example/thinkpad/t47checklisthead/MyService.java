package com.example.thinkpad.t47checklisthead;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
/**
 * Service who deal with corresponding request(i.e. login and logout)
 * Create by jinliang gao 03192018
 * */
public class MyService extends Service {
    public MyService() {
    }

    private static final String TAG = "MyService";
    IUser.Stub stub = new IUser.Stub() {
        @Override
        public boolean login(String userName, String userPwd) throws RemoteException {
            Log.d(TAG, "Service: userName: "+userName+"pwd:"+userPwd);
            return true;
        }

        @Override
        public void logout(String userName) throws RemoteException {
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // Return the communication channel to the service.

        return stub;
    }
}