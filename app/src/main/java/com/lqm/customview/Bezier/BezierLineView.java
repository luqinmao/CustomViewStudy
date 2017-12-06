package com.lqm.customview.Bezier;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.lqm.customview.R;

/**
 * user：lqm
 * desc：实现添加至购物车的运动轨迹
 */

public class BezierLineView extends View {
    private int mStartX;
    private int mStartY;
    private int mEndY;
    private int mEndX;
    private int mControlX;
    private int mControlY;
    private Paint mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mPath = new Path();
    private Paint mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mCurX;
    private int mCurY;

    public BezierLineView(Context context) {
        this(context,null);
    }

    public BezierLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        mCirclePaint.setColor(getResources().getColor(R.color.colorAccent));
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setStrokeWidth(15);
        mPathPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPathPaint.setStyle(Paint.Style.STROKE);
        mPathPaint.setStrokeWidth(15);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartX = 100;
        mStartY = 100;
        mEndX = w - 100;
        mEndY = h - 100;
        mControlX = w - 100;
        mControlY = 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mStartX, mStartY, 24, mCirclePaint);
        canvas.drawCircle(mEndX, mEndY, 24, mCirclePaint);
        mPath.reset();
        mPath.moveTo(mStartX, mStartY);
        mPath.quadTo(mControlX, mControlY, mEndX, mEndY);
        canvas.drawPath(mPath, mPathPaint);

        canvas.drawCircle(mCurX, mCurY, 24, mCirclePaint);
    }

    public void onStart() {
        BezierEvaluator evaluator = new BezierEvaluator(new PointF(mControlX, mControlY));
        PointF startPoint = new PointF(mStartX, mStartY);
        PointF endPoint = new PointF(mEndX, mEndY);
        ValueAnimator anim = ValueAnimator.ofObject(evaluator, startPoint, endPoint);
        anim.setDuration(1000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointF curPoint = (PointF) valueAnimator.getAnimatedValue();
                mCurX = (int) curPoint.x;
                mCurY = (int) curPoint.y;
                invalidate();
            }
        });
        anim.start();
    }


}
