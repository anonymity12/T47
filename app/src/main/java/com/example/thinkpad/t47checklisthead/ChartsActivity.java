package com.example.thinkpad.t47checklisthead;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.thinkpad.t47checklisthead.view.AreaChart03View;
import com.example.thinkpad.t47checklisthead.view.DemoView;


/**
 * Created by thinkpad on 2018/2/26.
 */

public class ChartsActivity extends AppCompatActivity {
    private DemoView mChart;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.i("TAG", ">>>>>>>>>>>>>ChartActivity onCreate requestWindowFeature");

        mChart = new AreaChart03View(this);
        FrameLayout content = new FrameLayout(this);
        FrameLayout.LayoutParams frameParm = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        frameParm.gravity = Gravity.BOTTOM | Gravity.RIGHT;

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int scrWidth = (int) (dm.widthPixels * 0.9);
        int scrHeight = (int) (dm.heightPixels * 0.9);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(scrWidth, scrHeight);

        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        final RelativeLayout chartLayout = new RelativeLayout(this);
        chartLayout.addView(mChart, layoutParams);
        content.addView(chartLayout);
        setContentView(content);
        Log.i("TAG", ">>>>>>>>>>>>>ChartActivity onCreate setContentView");

    }
}
