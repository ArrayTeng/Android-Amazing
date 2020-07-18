package com.example.tengfei.customview.view.calendar;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author 滕飞
 * date 2020/7/17 5:35 PM
 * email arrayadapter.cn@outlook.com
 * description
 */
class CalendarUtils {

    /**
     * 获取当月天数
     */
    @SuppressLint("WrongConstant")
    public static int getCurrentMonthDays() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 获取当月 1 号前面有几个空余时间位置
     */
    public static int getPrevDays() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 获取当天是几号
     */
    public static String getCurrentDayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "";
    }

    /**
     * 获取当月每一个格式化后的日期
     */
    public static String getFormatDayOfMonth(int indexDay) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, indexDay);
        return format.format(calendar.getTime());
    }

    /**
     * 模拟角标数据
     */
    public static List<CornerMarker> getCornerMarker() {
        List<CornerMarker> cornerMarkerList = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            CornerMarker cornerMarker = new CornerMarker();
            cornerMarker.dayOfMonth = i + "";
            cornerMarker.cornerMarker = (i + 2) + "";
            cornerMarkerList.add(cornerMarker);
        }
        return cornerMarkerList;
    }
}
