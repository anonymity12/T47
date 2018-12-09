package com.example.thinkpad.t47checklisthead;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by paul on 2018/12/9
 * last modified at 14:23.
 * Desc: 展示一个View（以一个ViewGroup 为例）的渲染流程： 1。 measure 2。 layout 3。draw（没有在这里体现）
 */

public class SimpleLayout extends ViewGroup {
    private static final String TAG = "tt1";

    public SimpleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() > 0) {
            View childView = getChildAt(0);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() > 0) {
            View childView = getChildAt(0);
            childView.layout(200, 100, childView.getMeasuredWidth()+ 90, childView.getMeasuredHeight() - 90);
            Log.e(TAG, "onLayout: childView.getMeasuredWidth() " + childView.getMeasuredWidth());
            Log.e(TAG, "onLayout: childView.getMeasuredHeight() " + childView.getMeasuredHeight());

        }
    }

}
