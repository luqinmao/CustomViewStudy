package com.lqm.customview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.lqm.customview.R;
import com.lqm.customview.View.WaveProgressView;

import java.text.DecimalFormat;

/**
 * user：lqm
 * desc：水波纹进度view
 */

public class WaveProgressActivity extends Activity {


    private WaveProgressView waveProgressView;
    private TextView textProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_progress);
        waveProgressView = findViewById(R.id.wave_progress);

        waveProgressView.setOnAnimationListener(new WaveProgressView.OnAnimationListener() {
            @Override
            public String howToChangeText(float interpolatedTime, float updateNum, float maxNum) {
                return null;
            }

            //省略部分代码...
            @Override
            public float howToChangeWaveHeight(float percent, float waveHeight) {
                return (1-percent)*waveHeight;
            }
        });

        textProgress = (TextView) findViewById(R.id.text_progress);
        waveProgressView.setTextView(textProgress);
        waveProgressView.setOnAnimationListener(new WaveProgressView.OnAnimationListener() {
            @Override
            public String howToChangeText(float interpolatedTime, float updateNum, float maxNum) {
                DecimalFormat decimalFormat=new DecimalFormat("0.00");
                String s = decimalFormat.format(interpolatedTime * updateNum / maxNum * 100)+"%";
                return s;
            }

            @Override
            public float howToChangeWaveHeight(float percent, float waveHeight) {
                return 0;
            }
        });
        waveProgressView.setProgressNum(80,1500);

    }
}
