package com.example.thinkpad.t47checklisthead;

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
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.HashMap;

/**
* Monitor xyz data and alert at suitable time
 * author: rongtian zheng
 * create at: 05102018
 * mail: 3060326200@qq.com
* */

/** todo solve follow bug!!!
* *
 * called playSound but no sound
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
    SoundPool sp;
    HashMap<Integer,Integer> hm;

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

        initSoundPool();
        
        //test sound
        Log.d(TAG, "onCreate: playSound");
        playSound(1);



    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: service onBind!!!!");
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

            //tt: another way:  we always execute above and choose to execute follow
            if (av > 30) {
                //tt: start a recordThread
                Log.d(TAG, ">>>>>>>av > 30!!!");
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
        return true;
    }

    void initSoundPool() {
        sp = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        hm = new HashMap<>();
        hm.put(1,sp.load(this,R.raw.alert,1));
    }

    void playSound(int sound) { // 获取AudioManager引用
        AudioManager am = (AudioManager) this
                .getSystemService(Context.AUDIO_SERVICE);
        // 获取当前音量
        float streamVolumeCurrent = am
                .getStreamVolume(AudioManager.STREAM_MUSIC);
        // 获取系统最大音量
        float streamVolumeMax = am
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // 计算得到播放音量
        float volume = streamVolumeCurrent / streamVolumeMax;
        // 调用SoundPool的play方法来播放声音文件
        Log.d(TAG, "playSound: gonna play volume = " + volume);
        sp.play(hm.get(sound), volume, volume, 1, -1, 1.f);
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
                    Log.d(TAG, "run: alertThread waiting");
                    lockObj.wait();
                    Log.d(TAG, "run: alertThread alert!!");
                    playSound(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
