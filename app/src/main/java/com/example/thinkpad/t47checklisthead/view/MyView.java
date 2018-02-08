package com.example.thinkpad.t47checklisthead.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.thinkpad.t47checklisthead.R;

/**
 * Created by thinkpad on 2018/2/1.
 */

public class MyView extends View {
    private final String TAG = "MyView";
    private int defaultSize;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        defaultSize = a.getDimensionPixelSize(R.styleable.MyView_default_size, 200);
        a.recycle();
    }

    private int getSize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            //一般不会执行
            case MeasureSpec.UNSPECIFIED:
                mySize = defaultSize;
                Log.i(TAG, ">>>>>>>>>>>UNSPECIFIED EXECUTED");
                break;
            case MeasureSpec.AT_MOST:
                mySize = size;
                Log.i(TAG, ">>>>>>>>>>>ATMOST EXECUTED");
                break;
            case MeasureSpec.EXACTLY:
                mySize = size;
                Log.i(TAG, ">>>>>>>>>>>EXACTLY EXECUTED");
                break;
        }
        Log.i(TAG, ">>>>>>>>return mySize is :" + mySize);
        return mySize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Log.i(TAG, "==========measure width");
        //defaultSize is 200dp which set in activity_main.xml
        int width = getSize(defaultSize, widthMeasureSpec);
        Log.i(TAG, ">>>>>>>> the width is  " + width);

        Log.i(TAG, "==========measure height");
        int height = getSize(defaultSize, heightMeasureSpec);
        Log.i(TAG, ">>>>>>>> the height is  " + height);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }
        setMeasuredDimension(width, height);
        int w = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        int h = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(w, h);//添加删减这行都是一样的，奇怪，昨天晚上是好使的。
        Log.i(TAG, "we set the Measured Dimension :" + width + height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.i(TAG, "The old width and height is :" + oldw + oldh);//log : 0 0
        Log.i(TAG, "The new width and height is :" + w + h);
        //从log来看，此函数在onMeasure后执行，显示 1080 300；故知道：width 从300(onMeasure) -> 1080
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "--------------onDraw executed");
        int radius = getWidth() / 2;
        int centerX = getLeft() + radius;
        int centerY = getBottom() - radius;
        Log.i(TAG, ">>>>>>>>radius is : " + radius);
        Log.i(TAG, ">>>>>>>>centerX is : " + centerX);
        Log.i(TAG, ">>>>>>>>centerY is : " + centerY);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawCircle(centerX, centerY, radius, paint);
    }
}
