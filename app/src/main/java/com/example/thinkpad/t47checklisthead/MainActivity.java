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
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ================   获取屏幕密度（方法1） ");

        // 获取屏幕密度（方法1）
        int screenWidth  = getWindowManager().getDefaultDisplay().getWidth();       // 屏幕宽（像素，如：480px）
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）

        Log.e(TAG + "", "default Display>>   screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);


        Log.e(TAG, "onCreate: ================   获取屏幕密度（方法2） ");

// 获取屏幕密度（方法2）
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();

        float density  = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;

        Log.e(TAG + "", "DisplayMetrics>>   xdpi=" + xdpi + "; ydpi=" + ydpi);
        Log.e(TAG + "  ", "DisplayMetrics>>   density=" + density + "; densityDPI=" + densityDPI);

        screenWidth  = dm.widthPixels;      // 屏幕宽（像素，如：480px）
        screenHeight = dm.heightPixels;     // 屏幕高（像素，如：800px）

        Log.e(TAG + "", "  DisplayMetrics(111) >>>    screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);


        Log.e(TAG, "onCreate: ================   获取屏幕密度（方法3） ");

// 获取屏幕密度（方法3）
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        density  = dm.density;      // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320）
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;

        Log.e(TAG + "  ", "DisplayMetrics   xdpi=" + xdpi + "; ydpi=" + ydpi);
        Log.e(TAG + "  ", "DisplayMetrics   density=" + density + "; densityDPI=" + densityDPI);

        int screenWidthDip = dm.widthPixels;        // 屏幕宽（dip，如：320dip）
        int screenHeightDip = dm.heightPixels;      // 屏幕宽（dip，如：533dip）

        Log.e(TAG + " ", " DisplayMetrics(222)    screenWidthDip=" + screenWidthDip + "; screenHeightDip=" + screenHeightDip);

        screenWidth  = (int)(dm.widthPixels * density + 0.5f);      // 屏幕宽（px，如：480px）
        screenHeight = (int)(dm.heightPixels * density + 0.5f);     // 屏幕高（px，如：800px）

        Log.e(TAG + "  ", "DisplayMetrics(222)    screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);
    }

}
