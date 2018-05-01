package com.example.thinkpad.t47checklisthead.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thinkpad.t47checklisthead.R;
import com.example.thinkpad.t47checklisthead.utils.Constants;
import com.example.thinkpad.t47checklisthead.view.SplineChart01View;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by paul on 2018/4/24.
 * optional improvement: using dataBiding feature 2018-04-25 09:25:16
 * todo : use chart view in this fragment
 */

public class WalkingFragment extends Fragment implements ScreenShotable,SensorEventListener {
    SensorManager mSensorManager;
    private View containerView;
    private Bitmap bitmap;
    private String argString;
    float[] xData;
    TextView xValueText, yValueText, zValueText;
    SplineChart01View splineChart01View;



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
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),mSensorManager.SENSOR_DELAY_NORMAL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_walking, container, false);
        xValueText = rootView.findViewById(R.id.x);
        yValueText = rootView.findViewById(R.id.y);
        zValueText = rootView.findViewById(R.id.z);
        splineChart01View = rootView.findViewById(R.id.spline_chart_view);
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
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            xValueText.setText(x + "");
            yValueText.setText(y + "");
            zValueText.setText(z + "");
            //todo store x value to a double[][] then refresh UI
            xData = storeXData(event.values[0]);
            splineChart01View.setDataSeries(xData);
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
    private float[] lineDataValues = new float[300];
    public float[] storeXData(float value) {
        for(int i = 0; i < lineDataValues.length - 1; i ++) {
            lineDataValues[i] = lineDataValues[i+1];
        }
        lineDataValues[299] =value;
        return lineDataValues;
    }
}
