package com.lqm.customview.Bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lqm.customview.R;

/**
 * user：lqm
 * desc：三阶贝塞尔曲线
 */

public class ThreeBezierView extends View {

    private float mStartX;
    private float mStartY;
    private float mEndX;
    private float mEndY;
    private Paint mLinePaint;
    private Path mBezierPath;
    private Paint mBezierPaint;

    private boolean mIsSecondPoint  =false; //是否为多点触控
    private float mControlX1;
    private float mControlY1;
    private float mControlX2;
    private float mControlY2;

    public ThreeBezierView(Context context) {
        this(context, null);
    }

    public ThreeBezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThreeBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mControlX1 = 300;
        mControlY1 = 600;
        mControlX2 = 600;
        mControlY2 = 600;
        mStartX = 200;
        mStartY = 400;
        mEndX = 800;
        mEndY = 400;
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(getResources().getColor(R.color.colorPrimary));
        mLinePaint.setStrokeWidth(10);
        mBezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBezierPaint.setColor(getResources().getColor(R.color.colorAccent));
        mBezierPaint.setStrokeWidth(10);
        mBezierPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mStartX, mStartY, 8, mLinePaint);
        canvas.drawText("起点", mStartX, mStartY, mLinePaint);
        canvas.drawCircle(mEndX, mEndY, 8, mLinePaint);
        canvas.drawText("终点", mEndX, mEndY, mLinePaint);
        canvas.drawCircle(mControlX1, mControlY1, 8, mLinePaint);
        canvas.drawText("控制点1", mControlX1, mControlY1, mLinePaint);
        canvas.drawCircle(mControlX2, mControlY2, 8, mLinePaint);
        canvas.drawText("控制点2", mControlX2, mControlY2, mLinePaint);
        canvas.drawLine(mStartX, mStartY, mControlX1, mControlY1, mLinePaint);
        canvas.drawLine(mControlX1, mControlY1, mControlX2, mControlY2, mLinePaint);
        canvas.drawLine(mControlX2, mControlY2, mEndX, mEndY, mLinePaint);

        mBezierPath.reset(); //因为不断重绘，path的路径也要重置，不然页面上会显示很多条线
        mBezierPath.moveTo(mStartX, mStartY); //移至起点
        mBezierPath.cubicTo(mControlX1, mControlY1, mControlX2, mControlY2, mEndX, mEndY); //三阶贝塞尔曲线
        canvas.drawPath(mBezierPath, mBezierPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {//多点触控
            case MotionEvent.ACTION_POINTER_DOWN:
                mIsSecondPoint = true;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mIsSecondPoint = false;
                break;
            case MotionEvent.ACTION_MOVE:
                mControlX1 = event.getX(0);//获取控制点1的横纵坐标
                mControlY1 = event.getY(0);
                if (mIsSecondPoint) {
                    mControlX2 = event.getX(1);//获取控制点2的横纵坐标
                    mControlY2 = event.getY(1);
                }
                invalidate();
                break;
        }
        return true;
    }


}
