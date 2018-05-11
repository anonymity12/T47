package com.example.thinkpad.t47checklisthead;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
* Monitor xyz data and alert at suitable time
 * author: rongtian zheng
 * create at: 05102018
 * mail: 3060326200@qq.com
* */

public class MonitorService extends Service implements SensorEventListener {
    private static final String TAG = "MonitorService";
    FallMonitorAidl.Stub stub = new FallMonitorAidl.Stub() {
        @Override
        public void startMonitor() throws RemoteException {
            //start to monitor at bkground
        }

        @Override
        public void sendSignal() throws RemoteException {
            //send signal to it's caller that fall detect
        }
    };
    SensorManager mSensorManager;
    MediaPlayer mediaPlayer;
    float x, y, z, av;
    float[] avArray = new float[30];
    float[] javArray = new float[30];
    long thisAlertTime, lastAlertTime;
    int counter;//counter for count javArray 15 to 29
    static float nowRecord; //tt: use this for record sensor data, recordThread will use it
    static float[] standardFallTemplate = {0.3f, 0.2f};// TODO: 2018/5/11 add fall template
    static Object lockObj = new Object();


    public MonitorService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        assert mSensorManager != null;
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mediaPlayer = MediaPlayer.create(this, R.raw.alert);
        //now we first create alert thread use static lock
        // thread1:warningAlert(always waiting for 2's notify)
        new warningAlertThread(lockObj).start();
        // thread2: recording thread, called when av > 35,
        // and after record finish, it'll notify warningAlert thread.
        // see at onSensorChange(), this thread will start



    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return stub;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
            av = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
            storeData(av);
            nowRecord = av;

            //tt: we always execute above and choose to execute follow
            if (!(counter > 0)) {
                //when counter <= 0; actually is 0, then we'll start a recording
                if (av > 35) {
                    counter = 15;
                    while (counter > 0) {
                        javArray[30 - counter] = av;
                        counter--;
                    }
                }
            }
            //tt: another way:  we always execute above and choose to execute follow
            if (av > 30) {
                //tt: start a recordThread
                new recordThread(lockObj).start();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    float[] storeData(float value) {
        System.arraycopy(avArray, 1, avArray, 0, 29);
        avArray[29] = value;
        return avArray;
    }

    boolean isFall(float[] prisonArray) {
        for (float prison : prisonArray) {

        }
        return false;
    }

    class recordThread extends Thread {
        float[] javArrayInThread = new float[30];
        int recordCounter = 15;
        Object lockObj;

        recordThread(Object lockObj) {
            this.lockObj = lockObj;
        }


        @Override
        public void run() {
            super.run();
            synchronized (lockObj){
                System.arraycopy(avArray, 14, javArrayInThread, 0, 15);//tt: w: might muiltThread use avArray;
                while (recordCounter > 0) {
                    try {
                        Thread.sleep(60); //tt: 1000 / 15(delay_normal frequency) =  66.66;
                        javArrayInThread[30 - recordCounter] = nowRecord;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    recordCounter--;
                }
                //now recordCounter is 0; and we start to analyze javArrayInThread
                //todo isFall(javArrayInThread)
                if (isFall(javArrayInThread)) {
                    lockObj.notify();
                    Log.d(TAG, "run: fall detected, intrige alert");
                }

            }

        }
    }

    class warningAlertThread extends Thread {
        Object lockObj;

        warningAlertThread(Object lockObj) {
            super();
            this.lockObj = lockObj;
        }

        @Override
        public void run() {
            super.run();
            synchronized (lockObj) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
