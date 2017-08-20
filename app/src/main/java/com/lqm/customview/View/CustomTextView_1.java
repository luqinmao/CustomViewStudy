package com.lqm.customview.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.lqm.customview.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @user  lqm
 * @desc  自定义TextView，点击显示随机数
 */

public class CustomTextView_1 extends TextView{

    private String mTitleText;
    private int mTitleColor;
    private int mTitleTextSize;
    private Paint mPaint;
    private Rect mBoundRect;

    public CustomTextView_1(Context context) {
        this(context, null);
    }

    public CustomTextView_1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView_1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr); //super
        getXmlParams(context,attrs,defStyleAttr);
        init();
    }

    //获得自定义样式属性
    private void getXmlParams(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(attrs,R.styleable.CustomTextView,defStyleAttr,0);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            switch (attr){
                case R.styleable.CustomTextView_titleText:
                    mTitleText = typedArray.getString(attr);
                    break;
                case R.styleable.CustomTextView_titleTextColor:
                    mTitleColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTextView_titleTextSize:
                    mTitleTextSize = typedArray.getDimensionPixelSize(attr,sp2px(16));
                    break;
            }
        }
        typedArray.recycle();
    }

    /**
     * 初始化与
     * 获得绘制文本的宽和高
     */

    private void init() {
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBoundRect = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBoundRect);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mTitleText = randomText();
                postInvalidate();
            }
        });


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBoundRect);
            float textWidth = mBoundRect.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBoundRect);
            float textHeight = mBoundRect.height();
            int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = desired;
        }
        Log.e("lqm","width:"+width + "width："+width);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawText(mTitleText,getWidth() /2 - mBoundRect.width()/2,
                getHeight()/2 - mBoundRect.height()/2, mPaint);

    }

    //获取随机数
    private String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() <4){
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set){
            sb.append(""+ i);
        }
        return sb.toString();
    }


    private int sp2px(int spSize){
       int pxSize =  (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, spSize, getResources().getDisplayMetrics());
        return pxSize;


    }

}
