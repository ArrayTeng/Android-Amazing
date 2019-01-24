package com.example.tengfei.customview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tengfei.customview.R;
import com.example.tengfei.customview.view.ShapeView;

/**
 * @author tengfei
 */
public class ShapeVariableActivity extends AppCompatActivity implements View.OnClickListener {

    private ShapeView mShapeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_shape_variable);
        findViewById(R.id.bt_shape_test).setOnClickListener(this);
        mShapeView = findViewById(R.id.shape_view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_shape_test:
                mShapeView.exchange();
                break;
            default:
                break;
        }
    }
}
