package com.lqm.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lqm.customview.R;

/**
 * user：lqm
 * desc：贝塞尔曲线
 */

public class BezierActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);
    }
}
