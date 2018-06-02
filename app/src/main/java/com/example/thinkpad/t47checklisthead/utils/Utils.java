package com.example.thinkpad.t47checklisthead.utils;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    private static final String TAG = "Utils";

    /*---------------------------------------------------------------------------*/
    //used for sine wave
    public static int centerStartingX, centerStartingY; // ������
    public static int centerEndX, centerEndY; // ����յ�
    public static double ScaleX = 30, ScaleY; // �̶ȼ��
    public static float core = 1000; // Y������
    public static double spacingY = 0.8;
    /*---------------------------------------------------------------------------*/

    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }
    //用于实现三个部分内不同的颜色取值，相信每个部分的临界处没有很大gap。
    public static int setColor(float val,int mCount) {
        float one = (255 + 255) / (mCount * 2 / 3);//这是一个颜色因子罢？？
        int r = 0, g = 0, b = 0;
        if (val < (mCount * 1 / 3)) {
            r = (int) (one * val);
            g = 255;
        } else if (val >= (mCount * 1 / 3) && val < (mCount * 2 / 3)) {
            r = 255;
            g = 255 - (int) ((val - (mCount * 1 / 3)) * one);
        } else {
            r = 255;
        }//最后一个三等分
        return Color.rgb(r, g, b);
    }

/**
 * Create data record file in path: /sdcard/sensor_data_recording/yyyy-MM-dd-HH-mm-ss-sss.csv
 * */
    public static String createDataFile() {
        String fileName = "/sdcard/sensor_data_recording/" + getFormatDate("createFile") + ".csv";
        File sensorDataFile = new File(fileName);
        if (!sensorDataFile.exists()) {
            Log.d(TAG, "onCreate: dataFile not be created???!!!!");
            try {
                sensorDataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "createDataFile: ", e);
            }
        }
        return fileName;
    }

    public static String getFormatDate(String fromWhere) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-sss");
        String dateString = formatter.format(date);
        if (fromWhere.equals("createFile")) {
            return "sensorData" + dateString;
        }
        return null;
    }
}
