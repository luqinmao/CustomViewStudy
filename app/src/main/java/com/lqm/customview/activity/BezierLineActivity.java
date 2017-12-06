package com.lqm.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lqm.customview.Bezier.BezierLineView;
import com.lqm.customview.R;

/**
 * user：lqm
 * desc：
 */

public class BezierLineActivity extends Activity {


    private BezierLineView mBezierLineView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_line);

        mBezierLineView = (BezierLineView)findViewById(R.id.view);

        mBezierLineView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBezierLineView.onStart();
            }
        });
    }
}
