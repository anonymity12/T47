package com.example.thinkpad.t47checklisthead.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by thinkpad on 2018/2/8.
 * A view that has a fixed pointer and a flexible panel like in the plane.
 *
 * ##########未完成，继承者 请看 FlexiblePanelView
 */

public class FixPanelView extends View {

    public FixPanelView(Context context) {
        this(context,null);
    }

    public FixPanelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FixPanelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
