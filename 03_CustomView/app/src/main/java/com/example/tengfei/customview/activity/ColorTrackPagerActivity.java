package com.example.tengfei.customview.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.tengfei.customview.R;
import com.example.tengfei.customview.customview.ColorTrackTextView;
import com.example.tengfei.customview.fragment.TrackTextViewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tengfei
 */
public class ColorTrackPagerActivity extends AppCompatActivity {

    private static final String TAG = "ColorTrackPagerActivity";

    private LinearLayout linearLayout;
    private ViewPager viewPager;

    private String[] titles = {"直播", "视频", "图片", "段子", "精华", "推荐"};
    private List<ColorTrackTextView> colorTrackTextViewList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_track_pager);
        linearLayout = findViewById(R.id.li_track_pager_table);
        viewPager = findViewById(R.id.vp_track_pager_viewpager);
        initTitle();
        initPage();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG, " position -> " + position + " positionOffset-> " + positionOffset);
                ColorTrackTextView leftColorTrackTextView = colorTrackTextViewList.get(position);
                leftColorTrackTextView.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                leftColorTrackTextView.setCurrentProgress(1 - positionOffset);
                if (position == titles.length - 1) {
                    return;
                }
                ColorTrackTextView rightColorTrackTextView = colorTrackTextViewList.get(position + 1);
                rightColorTrackTextView.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
                rightColorTrackTextView.setCurrentProgress(positionOffset);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initPage() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return TrackTextViewFragment.newInstance(titles[i]);
            }

            @Override
            public int getCount() {
                return titles.length;
            }
        });
    }

    private void initTitle() {
        for (String title : titles) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            ColorTrackTextView colorTrackTextView = new ColorTrackTextView(this);
            colorTrackTextView.setTextSize(20);
            colorTrackTextView.setChangeColor(Color.RED);
            colorTrackTextView.setOriginColor(Color.BLACK);
            colorTrackTextView.setLayoutParams(params);
            colorTrackTextView.setText(title);
            linearLayout.addView(colorTrackTextView);
            colorTrackTextViewList.add(colorTrackTextView);
        }

    }
}
