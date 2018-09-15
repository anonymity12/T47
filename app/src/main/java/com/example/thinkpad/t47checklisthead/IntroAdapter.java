package com.example.thinkpad.t47checklisthead;

/**
 * Created by paul on 2018/6/16
 * last modified at 1:16 PM.
 * Desc:
 */

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class IntroAdapter extends FragmentPagerAdapter {

    public IntroAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return IntroFragment.newInstance(Color.parseColor("#03A9F4"),
                        position); // blue
            default:
                return IntroFragment.newInstance(Color.parseColor("#4CAF50"),
                        position); // green
        }
    }

    @Override public int getCount() {
        return 2;
    }
}
