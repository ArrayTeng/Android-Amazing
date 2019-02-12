package com.example.tengfei.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.tengfei.BaseActivity;
import com.example.tengfei.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author tengfei
 * date 2019/1/29 5:48 PM
 * email tengfeigo@outlook.com
 * description 城市索引列表
 */
public class CityIndexListActivity extends BaseActivity {

    @Override
    public int setContentLayoutView() {
        return R.layout.activity_city_index_list;
    }

    @Override
    public void initOperation(@Nullable Bundle savedInstanceState) {

    }

}
