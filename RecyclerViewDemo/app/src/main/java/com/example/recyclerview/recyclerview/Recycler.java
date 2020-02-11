package com.example.recyclerview.recyclerview;

import android.view.View;

import java.util.Stack;

/**
 * @author tengfei
 * date 2020-02-11 21:51
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Recycler {

    private Stack<View>[] views;

    public Recycler(int typeNumber) {
        views = new Stack[typeNumber];
        for (int i=0;i<typeNumber;i++){
            views[i] = new Stack<View>();

        }
    }

    public void addRecyclerView(View view,int type){
        views[type].push(view);
    }

    public View getRecyclerView(int type){
        try {
            return views[type].pop();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
