package com.example.thinkpad.t47checklisthead.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thinkpad.t47checklisthead.R;
import com.example.thinkpad.t47checklisthead.utils.Constants;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by paul on 2018/4/24.
 */

public class WalkingFragment extends Fragment implements ScreenShotable {
    private View containerView;
    private Bitmap bitmap;
    private String argString;



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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_walking, container, false);
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
}
