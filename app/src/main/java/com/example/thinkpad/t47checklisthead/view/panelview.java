package com.example.thinkpad.t47checklisthead.view;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.icu.util.MeasureUnit;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.graphics.Path;

import com.example.thinkpad.t47checklisthead.R;

/**
 * Created by thinkpad on 2018/2/2.
 */
public class panelview extends View {
    private Paint paint = new Paint();
    Paint textpaint = new Paint();
    Paint mPaint = new Paint();
    Paint citePaint;
    TextView text;
    float dg = 120;
    float num = 66;
    float mPanelSize;
    public panelview(Context context) {
        this(context, null);

    }
    public panelview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public panelview(Context context, AttributeSet attrs, int defStyleAttrs) {
        super(context, attrs, defStyleAttrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.panelview, defStyleAttrs, 0);
        mPanelSize = a.getDimension(R.styleable.panelview_panelSize, 600);
        a.recycle();
    }
    public void setdg(float dgree) {
        dg = (dgree * 180) / 100;//dg是角度，从百分比degree过来
        num = dgree;//num是百分比
        postInvalidateDelayed(100);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float defaultSize = mPanelSize;
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
        setMeasuredDimension(resultWidth, resultHeight);
    }

    protected void onDraw(Canvas canvas) {
// TODO Auto-generated method stub
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        //移动到可能：200，250处
        canvas.translate(getWidth() / 2, getHeight()/2);//这里取得view的宽高用作移动点，bug？ fine
        paint.setStrokeWidth(80);
        paint.setColor(0xff1e7d1c);//画绿色底盘，表明已经达到
        RectF vol1 = new RectF(-getWidth() / 2 + 40, -getHeight() / 2 + 40, getWidth() / 2 - 40, getHeight() / 2 - 40);//这里的120应该在后期可以调整：panelSize
        canvas.drawArc(vol1, 180, dg, false, paint);
        paint.setColor(0xffd3d3d3);//画灰色底盘，未达到
        canvas.drawArc(vol1, 180 + dg, 180 - dg, false, paint);
        //canvas.drawCircle(0, 0, 200, paint); //??ԲȦ
        //以下是画指针的那个小圆圈
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(0xffd30000);//偏红色
        canvas.drawCircle(0, 0, 15, mPaint);
        canvas.save();//方向是0
        //绘制文字
        citePaint = new Paint(paint);
        citePaint.setColor(Color.WHITE);
        citePaint.setTextSize(18);
        citePaint.setStrokeWidth(2);
        citePaint.setStyle(Paint.Style.FILL);
        citePaint.setTextAlign(Paint.Align.CENTER);//这里设置了 文字居中属性
        canvas.drawText("world pitch percent", 0, 50, citePaint);//这里y也应该根据panelSize 调整
        citePaint.setTextSize(30);
        citePaint.setStrokeWidth(4);
        canvas.drawText((int) (num) + "%", 0, 90, citePaint);//这里y也应该根据panelSize 调整
        canvas.restore();
        //结束绘制文字
        canvas.save();//方向是0
        //开始绘制刻度线
        canvas.rotate(90);//顺时针90
        Paint tmpPaint = new Paint(paint); //画red刻度线
        tmpPaint.setStrokeWidth(5);
        tmpPaint.setColor(Color.RED);
        tmpPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        float y = 120;
        int count = 32; //控制数量
        for (int i = 0; i < count; i++) {
            if (i >= 0 && i <= 16) {
                if (i % 4 == 0) {
                     canvas.drawLine(0,y, 0, y+25, tmpPaint);//长刻度线
//                    canvas.drawCircle(0, y, 5, tmpPaint);
                } else {
                    canvas.drawLine(0,y, 0, y +10, tmpPaint);//短刻度线
//                    canvas.drawCircle(0, y, 5, tmpPaint);
                }
            }
            canvas.rotate(360f / count, 0f, 0f); //45 / 4 °每次旋转，多余的旋转用于回归原来的0度
        }
        canvas.restore();//回归0°
        //画指针，此时画布已经到达原来的位置。
        canvas.rotate(dg - 90, 0, 0);
        tmpPaint.setStrokeWidth(2);
        tmpPaint.setStyle(Paint.Style.FILL);
        tmpPaint.setColor(Color.WHITE);
        // canvas.drawCircle(0, -150, 8, tmpPaint);
        Path path = new Path();
        path.moveTo(-7, 0);
        path.lineTo(7, 0);
        path.lineTo(0, -190);
        path.close();
        //canvas.drawLine(0, 0, 0, -150, tmpPaint);
        canvas.drawPath(path, tmpPaint);
        Log.i("panelview", "the dg is : " + dg);
//        canvas.restore();
    }
}