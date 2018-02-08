package com.example.thinkpad.t47checklisthead.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyViewGroupB extends LinearLayout {

    public MyViewGroupB(Context context) {
        super(context);
    }

    public MyViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroupB(Context context, AttributeSet attrs,
                        int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("xys", "ViewGroupB dispatchTouchEvent" + ev.getAction());
        return super.dispatchTouchEvent(ev);//如果在这里返回true，则执行到B的dispatchTouchEvent即结束
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("xys", "ViewGroupB onInterceptTouchEvent" + ev.getAction());
        return super.onInterceptTouchEvent(ev);//如果在这里返回true，则执行B的onTouchEvent,再执行A的onTouchEvent,即使点击View，View也无法
        //接收到点击事件，这就是拦截的意义吧。
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("xys", "ViewGroupB onTouchEvent" + event.getAction());
        return super.onTouchEvent(event);//如果返回true，则onTouchEvent仅仅向上返回执行到B这一层。无论你是点击View，还是ViewGroupB
    }
}

