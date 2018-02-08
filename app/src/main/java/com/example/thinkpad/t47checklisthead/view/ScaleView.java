package com.example.thinkpad.t47checklisthead.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by thinkpad on 2018/2/5.
 */

public class ScaleView extends View {
    Paint mPaint = new Paint();

    public ScaleView(Context context) {
        this(context, null);
    }

    public ScaleView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public ScaleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawColor(Color.BLUE);
        canvas.drawRect(new Rect(0, 0, 400, 400),mPaint);

        //save our canvas
        canvas.save();
        canvas.scale(0.5f, 0.5f);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(new Rect(0,0,400,400),mPaint);
        canvas.restore();//此次restore 使缩放自动取消

        canvas.scale(0.5f, 0.5f, 200, 200);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);
    }
}
