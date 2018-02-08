package com.example.thinkpad.t47checklisthead.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by thinkpad on 2018/2/8.
 */

public class TestTextView extends View {

    private Paint textPaint38;
    private float textPosition;

    public TestTextView(Context context) {
        this(context, null);
    }

    public TestTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initTextPaint38();
    }

    private void initTextPaint38() {
        textPaint38 = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        textPaint38.setStyle(Paint.Style.STROKE);
        textPaint38.setColor(Color.BLACK);
//        textPaint38.setTextAlign(Paint.Align.CENTER);
        textPaint38.setStrokeCap(Paint.Cap.ROUND);
        textPaint38.setTextSize(100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("TAG",">>>>>>>>>>>we draw");
        canvas.drawText("AbcDEF",100,100,textPaint38);
        canvas.drawText("ABCDEFG",100,200,textPaint38);
        canvas.drawText("汉字大小",100,textPosition,textPaint38);

    }

    public void setPosition(float myPosition) {
        textPosition = myPosition;
        invalidate();

    }
}
