package com.example.thinkpad.t47checklisthead.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
/**
 * Created by thinkpad on 2018/2/7.
 */

public class MyViewA extends View {

    public MyViewA(Context context) {
        super(context);
    }

    public MyViewA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewA(Context context, AttributeSet attrs,
                  int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("xys", "View onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("xys", "View dispatchTouchEvent" + event.getAction());
        return super.dispatchTouchEvent(event);
    }

}
