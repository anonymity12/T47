package com.example.thinkpad.t47checklisthead.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import static com.example.thinkpad.t47checklisthead.utils.Utils.dpToPixel;

/**
 * Created by thinkpad on 2018/3/5.
 */

public class CloseWithPowerIndicatorView extends View {
    float radius = dpToPixel(70);
    RectF rect = new RectF();
    Paint arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint deeperArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint powerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int incrementAngular = 0;

    public int getIncrementAngular() {
        return incrementAngular;
    }

    public void setIncrementAngular(int incrementAngular) {
        this.incrementAngular = incrementAngular;
        postInvalidate();
    }

    public CloseWithPowerIndicatorView(Context context) {
        this(context, null);
    }

    public CloseWithPowerIndicatorView(Context context, AttributeSet attributeSet) {

        super(context, attributeSet);
        //arcPaint initialize
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(10);
        arcPaint.setStrokeCap(Paint.Cap.ROUND);
        arcPaint.setARGB(20,200,0,0);
        deeperArcPaint = new Paint(arcPaint);
        deeperArcPaint.setARGB(255,200,0,0);

    }

    //setPower


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float defaultSize = dpToPixel(100);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int resultWidth, resultHeight;

        if (widthMode == MeasureSpec.EXACTLY) {
            resultWidth = widthSize;
        } else {
            float tempWidth = defaultSize;
            resultWidth = (int) (getPaddingLeft() + tempWidth + getPaddingRight());
        }
        //same goes with height
        if (heightMode == MeasureSpec.EXACTLY) {
            resultHeight = heightSize;
        } else {
            float tempHeight = defaultSize;
            resultHeight = (int) (getPaddingLeft() + tempHeight + getPaddingRight());
        }
        //use the smaller one
        if (resultHeight < resultWidth) {
            resultWidth = resultHeight;
        } else {
            resultHeight = resultWidth;
        }
        setMeasuredDimension(resultWidth, resultHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;


        radius = dpToPixel(getWidth() / 4 - dpToPixel(10));

        rect.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        canvas.drawArc(rect,-60,300,false,arcPaint);
        canvas.drawArc(rect, 30 - incrementAngular,120 + 2*incrementAngular,false,deeperArcPaint);
        canvas.drawLine(centerX,centerY,centerX,centerY - dpToPixel(140) - dpToPixel(20) + radius,arcPaint);
        canvas.drawLine(centerX,centerY,centerX,centerY - dpToPixel(30) - dpToPixel(incrementAngular),deeperArcPaint);

    }
}
