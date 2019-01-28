package com.example.tengfei.customview.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.tengfei.customview.R;
import com.example.tengfei.customview.view.ImGestureDetector;

/**
 * @author tengfei
 * date 2019/1/28 2:07 PM
 * email tengfeigo@outlook.com
 * description 手势检测与缩放手势检测
 */
public class GestureDetectorActivity extends AppCompatActivity {

    private Button btGesture;

    private GestureDetector gestureDetector;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector);
        btGesture = findViewById(R.id.bt_gesture);

        gestureDetector = new GestureDetector(this, new ImGestureDetector());

        btGesture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("ImGestureDetector", "ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.i("ImGestureDetector", "ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i("ImGestureDetector", "ACTION_DOWN");
                        break;
                    default:
                        break;
                }
                return gestureDetector.onTouchEvent(event);
            }
        });
    }
}
