package com.example.tengfei.customview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tengfei.customview.R;
import com.example.tengfei.customview.adapter.ListScreenMenuAdapter;
import com.example.tengfei.customview.view.ListDataScreenView;

/**
 * @author tengfei
 */
public class ListDataScreenActivity extends AppCompatActivity {

    private ListDataScreenView listDataScreenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_screen);
        listDataScreenView = findViewById(R.id.list_data_screen);
        listDataScreenView.setAdapter(new ListScreenMenuAdapter());
    }
}
