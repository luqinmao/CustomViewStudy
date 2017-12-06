package com.lqm.customview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lqm.customview.Bezier.DragBubbleView;

/**
 * user：lqm
 * desc：仿QQ消息拖拽效果
 *
 */

public class DragBubbleActivity extends Activity implements DragBubbleView.OnBubbleStateListener {

    private DragBubbleView mDragBubbleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_bubble);
        mDragBubbleView = (DragBubbleView)findViewById(R.id.dragBubbleView);
        mDragBubbleView.setText("99");
        mDragBubbleView.setOnBubbleStateListener(this);
    }

    @Override
    public void onDrag() {

    }

    @Override
    public void onMove() {

    }

    @Override
    public void onRestore() {

    }

    @Override
    public void onDismiss() {

    }
}
