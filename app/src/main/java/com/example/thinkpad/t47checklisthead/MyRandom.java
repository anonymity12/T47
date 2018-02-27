package com.example.thinkpad.t47checklisthead;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.Random;
/**
 * Created by thinkpad on 2018/2/26.
 */

public class MyRandom extends Thread {
    private Random random;
    public static boolean isRandom = true;
    private int i;
    private Message msg;
    private Handler handler;

    public MyRandom() {
    }

    public MyRandom(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        random = new Random();

        while (isRandom) {
            i = random.nextInt(2000);
            msg = new Message();
            msg.what = i;
            handler.sendMessage(msg);
            Log.i("TAG", ">>>>>>>>>>>>>..handler sent message");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}