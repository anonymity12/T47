package com.example.thinkpad.t47checklisthead;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import static com.example.thinkpad.t47checklisthead.utils.Utils.createDataFile;

/**
* Monitor xyz data and alert at suitable time
 * author: rongtian zheng
 * create at: 05102018
 * mail: 3060326200@qq.com
* */

/**
* *
 *
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
    float[] avArray = new float[50];
    float[] javArray = new float[50];
    long thisAlertTime, lastAlertTime;
    int counter;//counter for count javArray 15 to 29
    static float nowRecord; //tt: use this for record sensor data, recordThread will use it
    static float[] standardFallTemplate = {11.796272f,
            11.201355f,
            8.593473f,
            10.219366f,
            9.2791f,
            9.939259f,
            9.621416f,
            9.870411f,
            10.071402f,
            11.31397f,
            10.717559f,
            9.473474f,
            11.129844f,
            7.171493f,
            8.013452f,
            9.39375f,
            11.814729f,
            3.605548f,
            4.843654f,
            10.40942f,
            16.104265f,
            13.269487f,
            11.345426f,
            9.042633f,
            29.014047f,
            8.127797f,
            8.111951f,
            13.975922f,
            6.007527f,
            14.146987f,
            9.623895f,
            8.293974f,
            10.061343f,
            10.044915f,
            13.742053f,
            9.298467f,
            8.216829f,
            11.955499f,
            11.277865f,
            8.307464f,
            10.383641f,
            9.89646f,
            9.974058f,
            9.474529f,
            10.903182f,
            9.807233f,
            10.311951f,
            10.056973f,
            10.127024f,
            9.276332f};
    static Object lockObj = new Object();
    private AudioManager mgr;
    private SoundPool soundPool;
    private float streamVolumeCurrent;
    private float streamVolumeMax;
    private float volume;
    private int soundId;
    private Handler myAlertHandler;
    String fileName;
    private String dataString;


    public MonitorService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        assert mSensorManager != null;
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);

        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        mgr = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = streamVolumeCurrent / streamVolumeMax;
        soundId = soundPool.load(this, R.raw.alert11, 1);
        myAlertHandler = new Handler();

        File sensorDataDir = new File("/sdcard/sensor_data_recording");
        if (!sensorDataDir.exists()) {
            boolean firstCreate = sensorDataDir.mkdirs();
            Log.d(TAG, "onCreate: mkdirs: /sdcard/sensor_data_recording");
        }
        fileName = createDataFile();
    }



    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: service onBind!!!!");
        return stub;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: service unbind!!!");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(this);
        Log.d(TAG, "onDestroy: service destroyed");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
            dataString = x + "," + y + "," + z + "\r\n";
            appendMethodB(fileName, dataString);
            //Log.d(TAG, "onSensorChanged: watch fre!!!");
            av = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
            storeData(av);
            nowRecord = av;

            //tt: another way:  we always execute above and choose to execute follow
            if (av > 22) {
                thisAlertTime = System.currentTimeMillis();
                if (thisAlertTime - lastAlertTime > 3000){
                    //tt: start a recordThread
                    new recordThread(lockObj).start();
                    lastAlertTime = thisAlertTime;
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    float[] storeData(float value) {
        System.arraycopy(avArray, 1, avArray, 0, 49);
        avArray[49] = value;
        return avArray;
    }

    boolean isFall(float[] prisonArray) {
        double sum = 0;
        int x = 0;
        for (float prison : prisonArray) {
            sum = sum + Math.sqrt(Math.pow((double)(prison - standardFallTemplate[x]),2));
            x ++;
        }
        Log.d(TAG, "isFall: bias sum is: " + sum);
        //tt: now we assume that exceed 20 is fall
        if (sum < 400) {
            return true;
        }

        return false;
    }



    class recordThread extends Thread {
        float[] javArrayInThread = new float[50];
        int recordCounter = 25;
        Object lockObj;
        WarningAlertThread warningAlertThread;

        recordThread(Object lockObj) {
            this.lockObj = lockObj;
        }


        @Override
        public void run() {
            super.run();
            synchronized (lockObj){
                System.arraycopy(avArray, 24, javArrayInThread, 0, 25);//tt: w: might muiltThread use avArray;
                //tt: whenever we start a record thread we create a warning thread.
                warningAlertThread = new WarningAlertThread(lockObj);
                warningAlertThread.start();
                while (recordCounter > 0) {
                    try {
                        Thread.sleep(28); //tt: 1000 / 35(delay_normal frequency) =  28;
                        javArrayInThread[50 - recordCounter] = nowRecord;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    recordCounter--;
                }
                for (float f : javArrayInThread) {
                    Log.d(TAG, "WTF: ele is: "+ f);
                }
                //now recordCounter is 0; and we start to analyze javArrayInThread
                if (isFall(javArrayInThread)) {
                    lockObj.notifyAll();
                    Log.d(TAG, "run: fall detected, intriger alert");
                }else {
                    warningAlertThread.interrupt();
                }

            }

        }
    }

    class WarningAlertThread extends Thread {
        Object lockObj;

        WarningAlertThread(Object lockObj) {
            super();
            this.lockObj = lockObj;
        }

        @Override
        public void run() {
            super.run();
            synchronized (lockObj) {
                try {
                    Log.d(TAG, "run: alertThread waiting");
                    lockObj.wait();
                    Log.d(TAG, "run: alertThread alert!!");
                    myAlertHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            soundPool.play(soundId, 1f, 1f, 1, 0, 1.0f);
                        }
                    }, 20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void appendMethodB(String fileName, String content) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}