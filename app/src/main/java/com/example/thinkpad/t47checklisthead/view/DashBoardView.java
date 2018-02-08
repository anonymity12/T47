package com.example.thinkpad.t47checklisthead.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by thinkpad on 2018/2/2.
 */

public class DashBoardView extends View {
    private Paint mPaint;
    private int startPoint, endPoint;
    public DashBoardView(Context context) {
        this(context, null);
    }

    public DashBoardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);

        startPoint = getWidth() / 3;
        endPoint = getWidth() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        startPoint = getWidth() / 3;
        endPoint = getWidth() / 2;
        canvas.translate(getWidth() / 2, getHeight() / 2);
        for (int i = 0; i <= 360; i += 10) {
            canvas.drawLine(0,startPoint,0, endPoint,mPaint);
            canvas.rotate(10);
            Log.i("TAG2", ">>>>>>>>>>> i is :" + i);
        }

    }
}
