package com.example.tengfei.customview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tengfei.customview.R;
import com.example.tengfei.customview.view.LetterSideBar;

/**
 * @author tengfei
 * date 2019/1/24 2:05 PM
 * email tengfeigo@outlook.com
 * description 字母索引列表
 */
public class LetterSideBarActivity extends AppCompatActivity implements LetterSideBar.CurrentTouchLetterListener {

    private TextView mCurrentTouchLetter;
    private LetterSideBar mLetterSideBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_side_bar);
        mCurrentTouchLetter = findViewById(R.id.tv_side_bar_letter);
        mLetterSideBar = findViewById(R.id.sidebar);
        mLetterSideBar.setCurrentTouchLetterListener(this);
    }

    @Override
    public void currentTouchLetter(CharSequence currentTouchLetter) {
        mCurrentTouchLetter.setText(currentTouchLetter);
    }
}
