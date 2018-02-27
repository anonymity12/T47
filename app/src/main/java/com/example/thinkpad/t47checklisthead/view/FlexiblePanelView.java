package com.example.thinkpad.t47checklisthead.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import static com.example.thinkpad.t47checklisthead.utils.Utils.dpToPixel;

/**
 * Created by thinkpad on 2018/2/9.
 */

public class FlexiblePanelView extends View{
    final float radius = dpToPixel(120);
    float yaw =0;// - 5/6 pi -> 0 -> 5/6 pi;
    RectF rect = new RectF();
    Paint paint =new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    ObjectAnimator animator = ObjectAnimator.ofFloat(this, "yaw", 0, 2.5f);

    public FlexiblePanelView(Context context) {
        this(context, null);
    }

    // please always remember call super(context, attrs) or nullPointerException thrown
    public FlexiblePanelView(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
        //point paint
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setStrokeWidth(10);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);
        pointPaint.setARGB(200,135,206,250);

    }


    // this 3 params constructor will not be called normally and in this experiment
    public FlexiblePanelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        animator.setDuration(1000);
        animator.setInterpolator(new FastOutLinearInInterpolator());

        //point paint
        pointPaint.setStyle(Paint.Style.STROKE);
        pointPaint.setARGB(200,135,206,250);
    }
    public float getYaw() {
        return yaw;
    }

    public void setYaw(float systemYaw) {
        this.yaw = systemYaw;
        invalidate();
    }
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.end();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("TAG", ">>>>>>>>>>onDraw");
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        int[] colors = {Color.GREEN, Color.YELLOW, Color.RED};
//        Shader shader = new LinearGradient(centerX - radius, centerY, centerX + radius, centerY,colors,null, Shader.TileMode.CLAMP);
//        paint.setShader(shader);
        //improve follow code
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(dpToPixel(5));

        rect.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        canvas.drawArc(rect, (float) (120 + yaw / Math.PI * 180),300,false, paint);
        canvas.drawPoint(centerX,centerY + radius,pointPaint);
        canvas.drawArc(rect, (float) (89.5 + yaw / Math.PI * 180),1f,false,paint);

    }
}
