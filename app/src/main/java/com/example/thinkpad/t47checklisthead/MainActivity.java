package com.example.thinkpad.t47checklisthead;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thinkpad.t47checklisthead.view.CloseWithPowerIndicatorView;

/**
 * Basic activity for testings.
 * tested:
 * 1. seek bar control the panel view
 * 2. using fragment manager to use fragment.
 * 3. using circle button.
 * 4. using tab layout and view pager. Tutorial from http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0731/3247.html
 * 5. google example: Creating Swipe Views with Tabs.
 */
public class MainActivity extends AppCompatActivity {
    CloseWithPowerIndicatorView closeWithPowerIndicatorView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_with_power_indicator);
        closeWithPowerIndicatorView = findViewById(R.id.close_power_view);
        closeWithPowerIndicatorView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofInt(closeWithPowerIndicatorView,"incrementAngular",90);
                animator.setDuration(300);
                animator.start();
                return true;
            }
        });


    }
}
