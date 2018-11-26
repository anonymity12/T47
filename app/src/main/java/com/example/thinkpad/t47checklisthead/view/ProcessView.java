package com.example.thinkpad.t47checklisthead.view;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;

import static com.example.thinkpad.t47checklisthead.utils.Utils.dpToPixel;
import static com.example.thinkpad.t47checklisthead.utils.Utils.setColor;
/**
 * Created by thinkpad on 2018/2/9.
 */

public class ProcessView extends View {
    final float radius = dpToPixel(120);
    float progress = 0;
    RectF  rect= new RectF();
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    ObjectAnimator animator= ObjectAnimator.ofFloat(this, "progress", 0, 100);


    public ProcessView(Context context) {
        super(context);
    }

    public ProcessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProcessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setTextSize(dpToPixel(40));
        paint.setTextAlign(Paint.Align.CENTER);
        //     animator.setRepeatCount(-1);
        animator.setDuration(1000);
        animator.setInterpolator(new FastOutSlowInInterpolator());
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
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
        float centerX=getWidth()/2;
        float centerY=getHeight()/2;
        int [] colors={Color.GREEN,Color.YELLOW,Color.RED};
        Shader shader =new LinearGradient(centerX - radius,centerY,centerX+radius,centerY,colors,null,Shader.TileMode.CLAMP);
        paint.setShader(shader);


        canvas.drawArc(rect,135,progress*2.7f,false,paint);
        //以上，画好了arc
        paint.setShader(null);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText((int) progress + "%", centerX, centerY+radius , paint);
    }
}