package com.example.tengfei.customview.view.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tengfei.customview.R;

/**
 * @author 滕飞
 * date 2020/7/17 5:52 PM
 * email arrayadapter.cn@outlook.com
 * description
 */
public class CalendarAct extends AppCompatActivity {

    private CustomCalendarView customCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        customCalendarView = findViewById(R.id.custom_calendar_view);
    }

    public void click(View view) {
        customCalendarView.setData();
    }
}
