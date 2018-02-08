package com.example.thinkpad.t47checklisthead.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by thinkpad on 2018/2/2.
 */

public class ArcView extends View {

    public ArcView(Context context) {
        this(context, null, 0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(6);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        RectF oval1 = new RectF(4, 4, 200, 200);//这些数字应该都是pixel为单位的。
        canvas.drawArc(oval1, -90, 180,true,paint);

        paint.setColor(Color.RED);

        canvas.save();
        canvas.rotate(-90,104,500);

        canvas.drawLine(104,20,104,720,paint);
        RectF oval2 = new RectF(40, 40, 300, 700);//这些数字应该都是pixel为单位的。

        canvas.drawRect(oval2,paint);

        canvas.restore();

    }
}
