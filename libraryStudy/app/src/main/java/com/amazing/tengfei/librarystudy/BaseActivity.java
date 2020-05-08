package com.amazing.tengfei.librarystudy;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 飞一般的感觉
 * date 2020/5/7 5:16 PM
 * email arrayadapter.cn@gmail.com
 * description
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }
}
