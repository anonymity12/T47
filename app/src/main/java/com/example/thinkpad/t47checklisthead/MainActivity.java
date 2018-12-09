package com.example.thinkpad.t47checklisthead;

/**
 * Created by paul on 2018/9/28
 * last modified at 21:50.
 * Desc:
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mainLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        LayoutInflater LayoutInflater = android.view.LayoutInflater.from(this);
        View buttonLayout = LayoutInflater.inflate(R.layout.button_layout, null);
        mainLayout.addView(buttonLayout);

    }

}