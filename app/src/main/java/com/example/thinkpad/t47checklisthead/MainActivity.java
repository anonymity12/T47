package com.example.thinkpad.t47checklisthead;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Basic activity for testings.
 * tested:
 * 1. seek bar control the panel view
 * 2. using fragment manager to use fragment.
 * 3. using circle button.
 * 4. using tab layout and view pager. Tutorial from http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0731/3247.html
 * 5. google example: Creating Swipe Views with Tabs.
 * 6. 03142018 test handler
 * 7. Test AIDL by jinliang gao
 * 8. Using messenger # Android art c2.4.3 @03192018
 * 9. show u how to write file in Android.
 * 11. show u how to find screen size
 * 12. show u how to use view stub
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ViewStub mViewStub;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test_viewstub);
        mViewStub = findViewById(R.id.act_test_viewstub_viewstub);
        Log.e(TAG, "onCreate: viewstub" );
        findViewById(R.id.act_test_viewstub_tv_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View layoutView;
                layoutView = mViewStub.inflate();
                Log.i(TAG, "onClick: " + layoutView.getId());
            }
        });
    }
}
