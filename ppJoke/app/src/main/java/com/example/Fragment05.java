package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.libnavannotation.ActivityDestination;
import com.example.libnavannotation.FragmentDestination;
import com.example.tengfei.R;

/**
 * @author tengfei
 * date 2020-02-01 19:54
 * email arrayadapter.cn@gmail.com
 * description
 */
@ActivityDestination(pageUrl = "main/tabs/publish")
public class Fragment05 extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment04);
    }

}
