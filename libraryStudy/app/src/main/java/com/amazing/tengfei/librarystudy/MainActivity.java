package com.amazing.tengfei.librarystudy;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import androidx.annotation.Nullable;

@Route(path = "/main/main")
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        ARouter.getInstance().build("/app/simple")
                .withString("string","tengfei")
                .withInt("int",1)
                .withParcelable("parcelable",new People("feifei",33))
                .navigation();
    }
}
