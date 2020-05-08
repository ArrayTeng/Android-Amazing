package com.amazing.tengfei.librarystudy;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.annotation.Nullable;

/**
 * @author 飞一般的感觉
 * date 2020/5/7 5:18 PM
 * email arrayadapter.cn@gmail.com
 * description
 */

@Route(path = "/show/simple")
public class SimpleActivity extends BaseActivity {

    private static final String TAG = "SimpleActivity_TAG";

    @Autowired(name = "string")
    String name;

    @Autowired(name = "int")
    int age;

    @Autowired(name = "parcelable")
    People people;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Log.e(TAG, "onCreate: "+name+"   "+age+"   "+people.getName()+"   "+people.getAge());

    }
}
