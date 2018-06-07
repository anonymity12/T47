package com.example.thinkpad.t47checklisthead.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thinkpad.t47checklisthead.R;

import java.util.List;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by paul on 2018/6/7
 * last modified at 9:34 PM.
 * Desc: show u the sensor info on your phone
 */

public class TestSensorFragment extends Fragment implements ScreenShotable{


    SensorManager sm;
    TextView tx1;
    private View containerView;
    private Bitmap bitmap;

    public TestSensorFragment(){

    }

    public static TestSensorFragment newInstance(String  arg) {
        TestSensorFragment testSensorFragment = new TestSensorFragment();
        Bundle bundle = new Bundle();
        bundle.putString(String.class.getName(), arg);
        testSensorFragment.setArguments(bundle);
        return testSensorFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sm = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);
        tx1 = ((TextView) rootView.findViewById(R.id.item_detail));
        //从传感器管理器中获得全部的传感器列表
        List<Sensor> allSensors = sm.getSensorList(Sensor.TYPE_ALL);

        //显示有多少个传感器
        tx1.setText("经检测该手机有" + allSensors.size() + "个传感器，他们分别是：\n");

        //显示每个传感器的具体信息
        for (Sensor s : allSensors) {

            String tempString = "\n" + "  设备名称：" + s.getName() + "\n" + "  设备版本：" + s.getVersion() + "\n" + "  供应商："
                    + s.getVendor() + "\n";

            switch (s.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    tx1.setText(tx1.getText().toString() + s.getType() + " 加速度传感器accelerometer" + tempString);
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    tx1.setText(tx1.getText().toString() + s.getType() + " 陀螺仪传感器gyroscope" + tempString);
                    break;
                case Sensor.TYPE_LIGHT:
                    tx1.setText(tx1.getText().toString() + s.getType() + " 环境光线传感器light" + tempString);
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    tx1.setText(tx1.getText().toString() + s.getType() + " 电磁场传感器magnetic field" + tempString);
                    break;
                case Sensor.TYPE_ORIENTATION:
                    tx1.setText(tx1.getText().toString() + s.getType() + " 方向传感器orientation" + tempString);
                    break;
                case Sensor.TYPE_PRESSURE:
                    tx1.setText(tx1.getText().toString() + s.getType() + " 压力传感器pressure" + tempString);
                    break;
                case Sensor.TYPE_PROXIMITY:
                    tx1.setText(tx1.getText().toString() + s.getType() + " 距离传感器proximity" + tempString);
                    break;
                case Sensor.TYPE_TEMPERATURE:
                    tx1.setText(tx1.getText().toString() + s.getType() + " 温度传感器temperature" + tempString);
                    break;
                default:
                    tx1.setText(tx1.getText().toString() + s.getType() + " 未知传感器" + tempString);
                    break;
            }
        }

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
                TestSensorFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return this.bitmap;
    }
}
