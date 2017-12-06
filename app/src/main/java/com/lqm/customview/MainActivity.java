package com.lqm.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickTextView(View view){
        startActivity(new Intent(MainActivity.this,CustomTextViewActivity.class));
    }

    public void onClickViewGoup(View view){
        startActivity(new Intent(MainActivity.this,ViewGroupActivity.class));
    }

    public void onClickBessel(View view){
        startActivity(new Intent(MainActivity.this,BezierActivity.class));
    }

    public void onClickDragBubble(View view){
        startActivity(new Intent(MainActivity.this,DragBubbleActivity.class));
    }
}
