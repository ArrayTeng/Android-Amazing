package com.example.tengfei.customview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tengfei.customview.R;

/**
 * @author tengfei
 */
public class TrackTextViewFragment extends Fragment {

    public static final String KEY = "ItemFragmentText";

    private String text;

    public static TrackTextViewFragment newInstance(String text) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY, text);
        TrackTextViewFragment itemFragment = new TrackTextViewFragment();
        itemFragment.setArguments(bundle);
        return itemFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_color_track, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            text = bundle.getString(KEY);
        }
        TextView textView = view.findViewById(R.id.fragment_track_text);
        textView.setText(text);
    }
}
