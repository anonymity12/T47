package com.example.thinkpad.t47checklisthead.fragment;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.thinkpad.t47checklisthead.MonitorService;
import com.example.thinkpad.t47checklisthead.R;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by paul on 2018/4/23.
 */

public class ContentFragment extends android.support.v4.app.Fragment implements ScreenShotable {
    private static final String TAG = "ContentFragment";
    public static final String CLOSE = "Close";
    public static final String WALKING = "Walking";
    public static final String STUMBLE = "Stumble";
    public static final String SIT = "Sit";
    public static final String CASE = "Case";
    public static final String SHOP = "Shop";
    public static final String PARTY = "Party";
    public static final String MOVIE = "Movie";

    private View containerView;
    protected ImageView mImageView;
    Button startBtn,stopBtn;
    View.OnClickListener buttonListener;
    protected int res;
    private Bitmap bitmap;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: !!!!");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public static ContentFragment newInstance(int resId) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //tt: 47 is special case: we use a activity_main_monitor_layout has two button
        if (res == 47) {
            View myView = inflater.inflate(R.layout.activity_main_monitor_layout, container, false);
            startBtn = myView.findViewById(R.id.startMonitor);
            stopBtn = myView.findViewById(R.id.stopMonitor);
            buttonListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.startMonitor:
                            getActivity().bindService(new Intent(getActivity(), MonitorService.class), serviceConnection, BIND_AUTO_CREATE);
                            break;
                        case R.id.stopMonitor:
                            getActivity().unbindService(serviceConnection);

                    }
                }
            };
            startBtn.setOnClickListener(buttonListener);
            stopBtn.setOnClickListener(buttonListener);
            return myView;
        } else {
            View rootView = inflater.inflate(R.layout.fragment_walk, container, false);
            mImageView = (ImageView) rootView.findViewById(R.id.image_content);
            mImageView.setClickable(true);
            mImageView.setFocusable(true);
            mImageView.setImageResource(res);
            return rootView;
        }
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    //tt: why it mean? screenshot
    //tt: guess it will save the bitmap for animation
    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                ContentFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}


