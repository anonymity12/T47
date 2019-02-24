package com.example.thinkpad.t47checklisthead.action.impl;

import android.os.RemoteException;
import android.util.Log;

import com.example.thinkpad.t47checklisthead.NormalUserAction;

/**
 * Created by paul on 2019/2/24
 * last modified at 21:51.
 * Desc:
 */

public class NormalUserActionAIDLImpl extends NormalUserAction.Stub {
    private static final String TAG = "NormalUserActionAIDL";
    @Override
    public void saveMoney(float money) throws RemoteException {
        Log.d(TAG, "saveMoney:----->>>> " + money);
    }

    @Override
    public float getMoney() throws RemoteException {
        return 1000;
    }

}
