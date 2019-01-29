package com.example.tengfei;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tengfei.activity.CityIndexListActivity;
import com.example.tengfei.entity.MainListEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_recycler)
    RecyclerView mRecyclerView;

    private List<MainListEntity> listEntities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MainRecyclerAdapter());
    }

    private void initData() {
        listEntities.add(new MainListEntity("城市索引列表", CityIndexListActivity.class));
    }


    private <T> void skipActivity(Class<T> z) {
        Intent intent = new Intent(this, z);
        startActivity(intent);
    }

     class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainRecyclerHolder> {

        @NonNull
        @Override
        public MainRecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_mian, viewGroup, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    skipActivity(listEntities.get(i).z);
                }
            });
            return new MainRecyclerHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MainRecyclerHolder mainRecyclerHolder, int i) {
            mainRecyclerHolder.textView.setText(listEntities.get(i).title);
        }

        @Override
        public int getItemCount() {
            return listEntities.size();
        }

        class MainRecyclerHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.main_item_tv)
            TextView textView;

            MainRecyclerHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
