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
    float x,y,z,av;
    float [] avArray = new float[30];
    float[] javArray = new float[30];
    long thisAlertTime, lastAlertTime;
    int counter;//counter for count javArray 15 to 29



    public MonitorService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        assert mSensorManager != null;
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        mediaPlayer = MediaPlayer.create(this, R.raw.alert);


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
            av = (float) Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
            storeData(av);

            //tt: another way
            if (av > 30 ){
                if (!eating) {
                    last 15
                }
                trigerWindow();
            }
            //tt: we always execute above and choose to execute follow
            if (!(counter > 0)) {
                //when counter <= 0; actually is 0, then we'll
                if (av > 35) {
                    counter = 15;
                    while(counter > 0){
                        javArray[30 - counter] = av;
                        counter --;
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    void judgeAndAlert(float av){
        thisAlertTime = System.currentTimeMillis();

        if (av > 35){
            if (thisAlertTime - lastAlertTime > 5000){
                //tt: enter analyze zone
                //tt: 1. copy array
                System.arraycopy(avArray,14,javArray,0,15);

                //tt: make alert
                mediaPlayer.start();
                lastAlertTime = thisAlertTime;
            }
        }

    }
    float[] storeData(float value) {
        System.arraycopy(avArray, 1, avArray, 0, 29);
        avArray[29] =value;
        return avArray;
    }
    boolean isFall(float [] waitForJudgeArray) {


        return false;
    }
}
