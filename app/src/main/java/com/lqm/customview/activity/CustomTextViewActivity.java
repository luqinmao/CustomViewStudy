package com.lqm.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lqm.customview.R;

/**
 * user：lqm
 * desc：基本的customTextView  CustomImageView 例子
 */

public class CustomTextViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview);
    }
}
