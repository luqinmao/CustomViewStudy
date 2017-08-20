package com.lqm.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Button viewGroupButton = (Button) findViewById(R.id.viewgroup);
        viewGroupButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.viewgroup:

                startActivity(new Intent(MainActivity.this,ViewGroupActivity.class));

                break;

        }

    }
}
