package com.example.thinkpad.t47checklisthead.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyViewGroupA extends LinearLayout {

    public MyViewGroupA(Context context) {
        super(context);
    }

    public MyViewGroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroupA(Context context, AttributeSet attrs,
                        int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("xys", "ViewGroupA dispatchTouchEvent" + ev.getAction());
        return super.dispatchTouchEvent(ev);//估计怎么也不会向 viewGroupB和view 分发了。结果就是仅仅执行
        //到dispatchTouchEvent0和1到ViewGroupA
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("xys", "ViewGroupA onInterceptTouchEvent" + ev.getAction());
        return true;//估计执行到A的onInterceptTouchEvent就停止。结果是执行到A的onTouchEvent0,意思是仅仅接收到
        //Down事件。当返回true的时候，接下来的UP事件不会引起此函数的调用，符合拦截一次后 后续事件都交由它处理 的原则。
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("xys", "ViewGroupA onTouchEvent" + event.getAction());
        return false;//返回true的时候，会接收到UP事件。当onInterceptTouchEvent返回true，此函数返回super的调用的时候，
        //不会接收到UP事件（证明super调用返回的是false）
    }
}
