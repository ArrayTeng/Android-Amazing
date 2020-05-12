package com.amazing.tengfei.librarystudy;

import android.icu.util.IslamicCalendar;
import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author 飞一般的感觉
 * date 2020/5/12 10:52 AM
 * email arrayadapter.cn@gmail.com
 * description
 */
public class ShareViewModel extends ViewModel {

    private final MutableLiveData<String> mutableLiveData = new MutableLiveData<>();

    public void setText(String s) {
        mutableLiveData.setValue(s);
    }

    public LiveData<String> getText() {
        return mutableLiveData;
    }


}
