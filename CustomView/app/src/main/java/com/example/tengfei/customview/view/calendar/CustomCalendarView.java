package com.example.tengfei.customview.view.calendar;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.tengfei.customview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 滕飞
 * date 2020/7/17 4:13 PM
 * email arrayadapter.cn@outlook.com
 * description 自定义日历控件
 */
public class CustomCalendarView extends LinearLayout {

    private static final String TAG = "CustomCalendarView_tag";
    private RecyclerView recyclerCalendar;

    private CalendarRecyclerAdapter calendarRecyclerAdapter;

    /**
     * 保存日历信息
     */
    private List<CalendarEntity> calendarEntities = new ArrayList<>();

    public CustomCalendarView(Context context) {
        this(context, null);
    }

    public CustomCalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        bindView(context, attrs);

        renderCalendar();

    }


    /**
     * 渲染日历
     */
    private void renderCalendar() {
        //当月1号前有多少空格
        int prevDays = CalendarUtils.getPrevDays();

        //当前月有多少天
        int currentMonthDays = CalendarUtils.getCurrentMonthDays();

        //填充空格
        for (int i = 0; i < prevDays; i++) {
            CalendarEntity calendarEntity = new CalendarEntity();
            calendarEntity.calendarDay = "";
            calendarEntity.cornerMarker = "";
            calendarEntity.formatCalendarDay = "";
            calendarEntities.add(calendarEntity);
        }

        //填充日期
        for (int i=1;i<=currentMonthDays;i++){
            CalendarEntity calendarEntity = new CalendarEntity();
            calendarEntity.calendarDay = i+"";
            calendarEntity.cornerMarker = "";
            calendarEntity.formatCalendarDay = CalendarUtils.getFormatDayOfMonth(i);
            calendarEntities.add(calendarEntity);
        }
        calendarRecyclerAdapter = new CalendarRecyclerAdapter(calendarEntities);
        recyclerCalendar.setAdapter(calendarRecyclerAdapter);
        calendarRecyclerAdapter.setClickItem(new CalendarRecyclerAdapter.IClickItem() {
            @Override
            public void onClick(CalendarEntity calendarEntity) {
                Log.e(TAG, "onClick: "+calendarEntity.calendarDay+"   "+calendarEntity.cornerMarker+"  "+calendarEntity.formatCalendarDay );
                calendarRecyclerAdapter.setClickDay(calendarEntity.calendarDay);
            }
        });
    }

    /**
     * 绑定控件
     */
    private void bindView(Context context, AttributeSet attrs) {
        inflate(context, R.layout.view_calendar, this);
        recyclerCalendar = findViewById(R.id.recycler_calendar);
        recyclerCalendar.setLayoutManager(new GridLayoutManager(context, 7));
    }

    /**
     * 用于绑定角标数据
     */
    public void setData(){
        List<CornerMarker> list = CalendarUtils.getCornerMarker();
        Map<String,String> map = new HashMap<>(1);
        for(CornerMarker cornerMarker:list){
            map.put(cornerMarker.dayOfMonth,cornerMarker.cornerMarker);
        }

        for(CalendarEntity calendarEntity:calendarEntities){
            if (null!= map.get(calendarEntity.calendarDay)){
                calendarEntity.cornerMarker = map.get(calendarEntity.calendarDay);
            }
        }
        calendarRecyclerAdapter.notifyDataSetChanged();
    }
}
