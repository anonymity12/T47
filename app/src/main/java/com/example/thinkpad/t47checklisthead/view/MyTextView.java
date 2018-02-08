package com.example.thinkpad.t47checklisthead.view;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.thinkpad.t47checklisthead.R;

import java.util.jar.Attributes;

/**
 * Created by thinkpad on 2018/2/2.
 */

public class MyTextView extends View{
    private final static String TAG = "MyTextView";
    private final static String TAG2 = "TAG2";
    private String mText;
    private int mTextColor;
    private int mTextSize;

    private Rect mBound;
    private Paint mPaint;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /*
        * 如果不使用以下的代码，我们将不能使用自定义属性
        * */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyTextView, defStyleAttr, 0);
        mText = a.getString(R.styleable.MyTextView_mText);
        mTextColor = a.getColor(R.styleable.MyTextView_mTextColor,Color.BLACK);
        mTextSize = (int)a.getDimension(R.styleable.MyTextView_mTextSize, 100);
        a.recycle();
        //一旦我们使用自定义的属性，以下的硬编码的值可以被舍弃
/*        mText = "Udf323fr";
        mTextColor = Color.BLACK;
        mTextSize = 100;*/

        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);


        mBound = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mBound);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyTextView(Context context, AttributeSet attrs, int defStyleAttrs, int defStyleRes) {
        super(context, attrs, defStyleAttrs, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        Log.i(TAG, "width mode is : " + widthMode);
        Log.i(TAG, "width size is : " + widthSize);
        Log.i(TAG, "height mode is : " + heightMode);
        Log.i(TAG, "height size is : " + heightSize);

        int resultWidth, resultHeight;

        //deal with width
        if (widthMode == MeasureSpec.EXACTLY) {
            resultWidth = widthSize;
        } else {
            float  textWidth = mBound.width();
            resultWidth = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            Log.i(TAG, "文字控件的宽度" + resultWidth);
        }
        //some goes with height
        if (heightMode == MeasureSpec.EXACTLY) {
            resultHeight = heightSize;
        } else {
            float textHeight = mBound.height();
            resultHeight = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            Log.i(TAG, "文字控件的高度： " + resultHeight);
        }

        Log.i(TAG2, ">>>>>>>>>>>>>>>测量安排的控件宽度是：" + resultWidth);
        //at last, we set the actually dimens
        setMeasuredDimension(resultWidth, resultHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG2, ">>>>>>>>>>>>>>>绘制时得到的控件宽度是：" + getWidth());
        canvas.drawText(mText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}
