package com.example.thinkpad.t47checklisthead.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thinkpad.t47checklisthead.R;
import com.example.thinkpad.t47checklisthead.utils.Constants;
import com.example.thinkpad.t47checklisthead.view.SplineChart01View;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

import static com.example.thinkpad.t47checklisthead.view.SplineChart01View.DATE_TYPE_X;
import static com.example.thinkpad.t47checklisthead.view.SplineChart01View.DATE_TYPE_Y;
import static com.example.thinkpad.t47checklisthead.view.SplineChart01View.DATE_TYPE_Z;

/**
 * Created by paul on 2018/4/24.
 * optional improvement: using dataBiding feature 2018-04-25 09:25:16
 *
 * * this commit contain function like: get the frequency of sensor 2018-05-10 07:56:39
 */

public class WalkingFragment extends Fragment implements ScreenShotable,SensorEventListener {
    // doing: 2018/5/6,10 use wave to estimate the posture not just a point, this should completed after reading brajidic
    // complete: 2018/5/10 add service to replace this fragment
    private static final String TAG = "WalkingFragment";
    SensorManager mSensorManager;
    private View containerView;
    private Bitmap bitmap;
    private String argString;
    float[] xData, yData, zData;
    float av = 0;//tt: the average of three.
    TextView xValueText, yValueText, zValueText;
    SplineChart01View splineChart01View;
    MediaPlayer mediaPlayer;
    long thisAlertTime, lastAlertTime;
    static long start_time;
    static Handler handler;
    long count;
    Bundle b;
    Message m = new Message();




    public static WalkingFragment newInstance(String args) {
        WalkingFragment walkingFragment = new WalkingFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.FRAGMENT_ARGS, args);
        walkingFragment.setArguments(bundle);

        return walkingFragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        argString = getArguments().getString(Constants.FRAGMENT_ARGS);
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        start_time = System.currentTimeMillis();
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),SensorManager.SENSOR_DELAY_NORMAL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR),SensorManager.SENSOR_DELAY_NORMAL);
        }

        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.alert);
    }

    @SuppressLint("HandlerLeak")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_walking, container, false);
        xValueText = rootView.findViewById(R.id.x);
        yValueText = rootView.findViewById(R.id.y);
        zValueText = rootView.findViewById(R.id.z);
        splineChart01View = rootView.findViewById(R.id.spline_chart_view);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                Bundle b = msg.getData();
                zValueText.setText(b.get("freFloat").toString());
                super.handleMessage(msg);
            }

        };
        return rootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }
    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                WalkingFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return this.bitmap;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        count ++;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            av = (float) Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
            judgeAndAlert(av);
            xValueText.setText(x + "");
            yValueText.setText(y + "");
            zValueText.setText(z + "");
            xData = storeXData(x);
            yData = storeYData(y);
            zData = storeZData(z);
            splineChart01View.setDataSeries(xData,DATE_TYPE_X);
            splineChart01View.setDataSeries(yData,DATE_TYPE_Y);
            splineChart01View.setDataSeries(zData,DATE_TYPE_Z);
            float fre = (1000 / ((System.currentTimeMillis() - WalkingFragment.start_time) / count));
            b.putFloat("freFloat",fre);
            m.setData(b);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }


    public void judgeAndAlert(float av){
        thisAlertTime = System.currentTimeMillis();

        if (av > 35){
            if (thisAlertTime - lastAlertTime > 5000){
                //tt: make alert
                mediaPlayer.start();
                lastAlertTime = thisAlertTime;
            }
        }

    }

    //tt: store(update) data in three array
    private float[] lineDataValuesX = new float[10];
    private float[] lineDataValuesY = new float[10];
    private float[] lineDataValuesZ = new float[10];
    public float[] storeXData(float value) {
        System.arraycopy(lineDataValuesX, 1, lineDataValuesX, 0, 9);
        lineDataValuesX[9] =value;
        return lineDataValuesX;
    }
    public float[] storeYData(float value) {
        System.arraycopy(lineDataValuesY, 1, lineDataValuesY, 0, 9);
        lineDataValuesY[9] =value;
        return lineDataValuesY;
    }
    public float[] storeZData(float value) {
        System.arraycopy(lineDataValuesZ, 1, lineDataValuesZ, 0, 9);
        lineDataValuesZ[9] =value;
        return lineDataValuesZ;
    }
}
