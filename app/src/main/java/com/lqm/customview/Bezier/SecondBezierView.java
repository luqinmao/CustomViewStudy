package com.lqm.customview.Bezier;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.lqm.customview.R;

/**
 * user：lqm
 * desc：二阶贝塞尔曲线
 */

public class SecondBezierView extends View {

    private float mControlX;
    private float mControlY;
    private float mStartX;
    private float mStartY;
    private float mEndX;
    private float mEndY;
    private Paint mLinePaint;
    private Path mBezierPath;
    private Paint mBezierPaint;

    public SecondBezierView(Context context) {
        this(context, null);
    }

    public SecondBezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecondBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mControlX = 400;
        mControlY  = 300;
        mStartX = 200;
        mStartY = 200;
        mEndX = 800;
        mEndY = 200;
        mLinePaint = new Paint();
        mLinePaint.setColor(getResources().getColor(R.color.colorPrimary));
        mLinePaint.setStrokeWidth(10);
        mBezierPaint = new Paint();
        mBezierPaint.setColor(getResources().getColor(R.color.colorAccent));
        mBezierPaint.setStrokeWidth(10);
        mBezierPaint.setStyle(Paint.Style.STROKE);
        mBezierPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mStartX, mStartY, 8, mLinePaint);
        canvas.drawText("起点", mStartX, mStartY, mLinePaint);
        canvas.drawCircle(mEndX, mEndY, 8, mLinePaint);
        canvas.drawText("终点", mEndX, mEndY, mLinePaint);
        canvas.drawCircle(mControlX, mControlY, 8, mLinePaint);
        canvas.drawText("控制点", mControlX, mControlY, mLinePaint);
        canvas.drawLine(mStartX, mStartY, mControlX, mControlY, mLinePaint);
        canvas.drawLine(mEndX, mEndY, mControlX, mControlY, mLinePaint);

        mBezierPath.reset();//因为不断重绘，path的路径也要重置，不然页面上会显示很多条线
        mBezierPath.moveTo(mStartX, mStartY);//移至起点
        mBezierPath.quadTo(mControlX, mControlY, mEndX, mEndY);//二阶贝塞尔曲线，传入控制点和终点坐标
        canvas.drawPath(mBezierPath, mBezierPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mControlX = event.getX();
                mControlY = event.getY();
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                ValueAnimator animX = ValueAnimator.ofFloat(mControlX, getWidth() / 2);
                animX.setDuration(500);
                animX.setInterpolator(new OvershootInterpolator());
                animX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mControlX = (float) animation.getAnimatedValue();
                        invalidate();
                    }
                });
                animX.start();
                ValueAnimator animY = ValueAnimator.ofFloat(mControlY, getHeight() / 2);
                animY.setDuration(500);
                animY.setInterpolator(new OvershootInterpolator());
                animY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mControlY = (float) animation.getAnimatedValue();
                        invalidate();
                    }
                });
                animY.start();
                break;
        }
        return true;
    }


}
