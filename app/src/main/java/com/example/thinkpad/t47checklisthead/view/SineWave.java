package com.example.thinkpad.t47checklisthead.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.graphics.Canvas;

import com.example.thinkpad.t47checklisthead.utils.Utils;

/**
 * Created by thinkpad on 2018/2/26.
 */

public class SineWave  extends View {
    int[] datas = new int[10]; //
    private Paint mPaint = null;

    int centerStartingX, centerStartingY; //
    int centerEndX, centerEndY; //
    double ScaleX, ScaleY; //

    public SineWave(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setAlpha(200);
        mPaint.setStrokeWidth(5);
    }

    public SineWave(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setAlpha(200);
        mPaint.setStrokeWidth(5);
    }

    public void Set(int frequency) {
        for (int i = 0; i < datas.length - 1; i++) {
            datas[i] = datas[i + 1];
        }
        datas[datas.length - 1] = frequency;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        centerStartingX = Utils.centerStartingX;

        for (int i = 0; i < datas.length - 1; i++) {

            centerStartingX += Utils.ScaleX;
            canvas.drawLine(centerStartingX, (float) (Utils.core - datas[i]
                            * Utils.spacingY), (float) (centerStartingX + Utils.ScaleX),
                    (float) (Utils.core - datas[i + 1] * Utils.spacingY), mPaint);
            canvas.drawText("" + datas[i + 1],
                    (float) (centerStartingX + Utils.ScaleX) - 20,
                    (float) (Utils.core - datas[i + 1] * Utils.spacingY), mPaint);
        }

    }

    public void reFresh() {
        this.invalidate();
        Log.i("TAG", ">>>>>>>>>>>>>>>>>>refresh view");
    }

}