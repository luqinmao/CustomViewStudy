package com.lqm.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.lqm.customview.R;
import com.lqm.customview.View.ExpandableBreathingButton;

/**
 * user：lqm
 * desc：可展开的、会呼吸的按钮View
 */

public class ExpandViewActivity extends Activity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_view);

        ExpandableBreathingButton publishBtn = (ExpandableBreathingButton) findViewById(R.id.publish_btn);
        publishBtn.setOnButtonItemClickListener(new ExpandableBreathingButton.OnButtonItemClickListener() {
            @Override
            public void onButtonItemClick(int position) {
                Toast.makeText(ExpandViewActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
