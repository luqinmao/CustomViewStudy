package com.lqm.customview.Bezier;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

import com.lqm.customview.utils.BezierUtil;

/**
 * 贝赛尔曲线估值器
 */
public class BezierEvaluator implements TypeEvaluator<PointF> {

    /* 控制点坐标 */
    private PointF mControlPoint;

    public BezierEvaluator(PointF controlPoint) {
        mControlPoint = controlPoint;
    }

    @Override
    public PointF evaluate(float v, PointF pointF, PointF t1) {
        return BezierUtil.calculateBezierPointForQuadratic(v, pointF, mControlPoint, t1);
    }
}
