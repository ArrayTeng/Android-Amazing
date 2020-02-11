package com.example.recyclerview.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tengfei
 * date 2020-02-11 20:07
 * email arrayadapter.cn@gmail.com
 * description
 */
public class RecyclerView extends ViewGroup {

    private Adapter adapter;

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    //内容偏移量
    private int scrolly;

    //当前显示的View
    private List<View> viewList;

    private int rowCount;
    private boolean needRelayout;
    private int width;
    private int height;

    private int[] heights;

    //View的第一行实在内容的第几行
    private int firstRow;

    private Recycler recycler;

    public RecyclerView(Context context) {
        this(context, null);
    }

    public RecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        viewList = new ArrayList<>();
        needRelayout = true;
        if (adapter != null) {
            recycler = new Recycler(adapter.getViewTypeCount());
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (needRelayout || changed) {
            needRelayout = false;
            viewList.clear();
            removeAllViews();
            if (adapter != null) {
                width = r - l;
                height = b - t;
                int left, top = 0, right, bottom;
                top = -scrolly;
                for (int i = 0; i < rowCount && top < height; i++) {
                    bottom = top + heights[i];
                    View view = makeAndStep(i, 0, top, width, bottom);
                    viewList.add(view);
                    top = bottom;
                }
            }
        }
    }

    private View makeAndStep(int row, int left, int top, int right, int bottom) {
        View view = obtainView(row, right - left, bottom - top);
        view.layout(left, top, right, bottom);
        return view;
    }

    private View obtainView(int row, int width, int height) {
        int itemType = adapter.getItemViewType(row);
        View recyView = recycler.getRecyclerView(itemType);
        View view = adapter.getView(row, recyView, this);
        if (view == null) {
            throw new RuntimeException("View can`t null");
        }
        view.setTag(R.id.type_item_view, itemType);
        view.setTag(R.id.tag_row, row);
        view.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));

        addView(view,0);
        return view;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        int h = 0;
        if (adapter != null) {
            this.rowCount = adapter.getCount();
            heights = new int[rowCount];
            for (int i = 0; i < heights.length; i++) {
                heights[i] = adapter.getHeight(i);
            }

        }

        //计算所有item控件的高度总和
        int tmpH = sumArray(heights, 0, heights.length);

        h = Math.min(heightSize, tmpH);

        setMeasuredDimension(widthSize, h);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
    }

    private int sumArray(int array[], int firstIndex, int count) {
        int sum = 0;
        count += firstIndex;
        for (int i = firstIndex; i < count; i++) {
            sum += array[i];
        }
        return sum;
    }
}
