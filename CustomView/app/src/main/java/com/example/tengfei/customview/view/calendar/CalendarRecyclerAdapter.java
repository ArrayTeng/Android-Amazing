package com.example.tengfei.customview.view.calendar;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tengfei.customview.R;

import java.util.List;

/**
 * @author 滕飞
 * date 2020/7/17 4:21 PM
 * email arrayadapter.cn@outlook.com
 * description
 */
public class CalendarRecyclerAdapter extends RecyclerView.Adapter<CalendarRecyclerAdapter.CalendarRecyclerHolder> {

    private List<CalendarEntity> calendarEntities;

    private String clickDay;
    private String currentDayOfMonth;

    public CalendarRecyclerAdapter(List<CalendarEntity> calendarEntities) {
        this.calendarEntities = calendarEntities;
        currentDayOfMonth = CalendarUtils.getCurrentDayOfMonth();
        //默认点击的那一天是当天
        clickDay = currentDayOfMonth;
    }

    @Override
    public CalendarRecyclerHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_calendar_view,viewGroup,false);
        return new CalendarRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarRecyclerHolder calendarRecyclerHolder, final int i) {
        final CalendarEntity calendarEntity = calendarEntities.get(i);
        if (TextUtils.isEmpty(calendarEntity.calendarDay)){
            calendarRecyclerHolder.itemView.setVisibility(View.GONE);
        }else {
            calendarRecyclerHolder.itemView.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(calendarEntity.calendarDay) &&
                (Integer.parseInt(currentDayOfMonth)<=Integer.parseInt(calendarEntity.calendarDay))){
            calendarRecyclerHolder.textCornerMarker.setVisibility(View.VISIBLE);
        }else {
            calendarRecyclerHolder.textCornerMarker.setVisibility(View.GONE);
        }
        calendarRecyclerHolder.textCalendarDay.setText(calendarEntity.calendarDay);
        calendarRecyclerHolder.textCornerMarker.setText(calendarEntity.cornerMarker);

        calendarRecyclerHolder.textCalendarDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=iClickItem){
                    iClickItem.onClick(calendarEntity);
                }
            }
        });

        if (clickDay.equals(calendarEntity.calendarDay)){
            calendarRecyclerHolder.textCalendarDay.setBackgroundColor(Color.parseColor("#2196F3"));
        }else {
            calendarRecyclerHolder.textCalendarDay.setBackgroundColor(Color.parseColor("#ffffffff"));
        }


    }

    private IClickItem iClickItem;

    public interface IClickItem{

        void onClick(CalendarEntity calendarEntity);
    }


    public void setClickItem(IClickItem iClickItem) {
        this.iClickItem = iClickItem;
    }

    public void setClickDay(String clickDay){
        this.clickDay = clickDay;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return calendarEntities.size();
    }

    public static class CalendarRecyclerHolder extends RecyclerView.ViewHolder{
        private TextView textCalendarDay;
        private TextView textCornerMarker;
        public CalendarRecyclerHolder(View itemView) {
            super(itemView);
            textCalendarDay = itemView.findViewById(R.id.text_calendar_day);
            textCornerMarker = itemView.findViewById(R.id.text_corner_marker);
        }
    }
}
