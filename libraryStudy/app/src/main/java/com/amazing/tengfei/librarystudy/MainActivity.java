package com.amazing.tengfei.librarystudy;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.amazing.tengfei.librarystudy.lifecycle.LifecycleHandler;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.viewpager.widget.ViewPager;

@Route(path = "/main/main")
public class MainActivity extends BaseActivity {

    private ViewPager viewPager;

    private List<Fragment> fragments = new ArrayList<>();

    private static final String TAG = "MainActivity_TAG";

    private LifecycleHandler lifecycleHandler = new LifecycleHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            return false;
        }
    },this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        viewPager = findViewById(R.id.pager);

        fragments.add(new DetailFragment());
        fragments.add(new MasterFragment());
        lifecycleHandler.sendEmptyMessage(1);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));


    }

    public void click(View view) {

        getLifecycle().addObserver(new LifecycleObserver() {

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            void onResume(){
                Log.e(TAG, "onResume: OnLifecycleEvent   "+"onResume");
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            void onPause(){
                Log.e(TAG, "onPause: OnLifecycleEvent   "+"onResume");
            }

        });
    }


    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
