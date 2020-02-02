package com.example;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.libnavannotation.FragmentDestination;
import com.example.tengfei.R;

/**
 * @author tengfei
 * date 2020-02-01 19:54
 * email arrayadapter.cn@gmail.com
 * description
 */
@FragmentDestination(pageUrl = "main/tabs/publish")
public class Fragment05 extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment04,container,false);
    }
}
