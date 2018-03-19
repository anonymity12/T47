package com.example.thinkpad.t47checklisthead;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.example.thinkpad.t47checklisthead.MainActivity.MSG_FROM_CLIENT;
import static com.example.thinkpad.t47checklisthead.MainActivity.MSG_FROM_SERVICE;

/**
 * Created by rontian zheng on 2018/3/19.
 */

public class MessageService extends Service {
    private static final String TAG = "MessageService";
    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROM_CLIENT:
                    Log.i(TAG, "handleMessage: receive message from client" + msg.getData().getString("msg"));
                    //-----------------code that make we can see a response from this service------------
                    //delete these code, we cannot see any response from service
                    Messenger client = msg.replyTo;
                    Message replyMessage = Message.obtain(null, MSG_FROM_SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply"," Service has got your message and I will reply you later!");
                    replyMessage.setData(bundle);
                    try{
                        client.send(replyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    //-----------------code that make we can see a response from this service------------

                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }

        }
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
