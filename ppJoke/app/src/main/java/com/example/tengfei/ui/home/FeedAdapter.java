package com.example.tengfei.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tengfei.databinding.LayoutFeedTypeImageBinding;
import com.example.tengfei.databinding.LayoutFeedTypeVideoBinding;
import com.example.tengfei.model.Feed;

/**
 * @author tengfei
 * date 2020-02-08 15:42
 * email arrayadapter.cn@gmail.com
 * description
 */
public class FeedAdapter extends PagedListAdapter<Feed, FeedAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;

    private String category;

    public FeedAdapter(Context context,String category) {
        super(new DiffUtil.ItemCallback<Feed>() {
            @Override
            public boolean areItemsTheSame(@NonNull Feed oldItem, @NonNull Feed newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Feed oldItem, @NonNull Feed newItem) {
                return oldItem.equals(newItem);
            }
        });
        this.category = category;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        Feed feed = getItem(position);
        return feed.itemType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = null;
        if (viewType == Feed.TYPE_IMAGE_TEXT) {
            binding = LayoutFeedTypeImageBinding.inflate(layoutInflater, parent, false);
        } else if (viewType == Feed.TYPE_VIDEO) {
            binding = LayoutFeedTypeVideoBinding.inflate(layoutInflater, parent, false);
        }
        return new ViewHolder(binding.getRoot(),binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;
        public ViewHolder(@NonNull View itemView,ViewDataBinding binding) {
            super(itemView);
            this.binding = binding;
        }

        public void bindData(Feed item) {
            if (binding instanceof LayoutFeedTypeImageBinding){
                LayoutFeedTypeImageBinding layoutFeedTypeImageBinding = (LayoutFeedTypeImageBinding) binding;
                layoutFeedTypeImageBinding.setFeed(item);
                layoutFeedTypeImageBinding.feedImage.bindData(item.width,item.height,16,item.cover);
            }else {
                LayoutFeedTypeVideoBinding layoutFeedTypeVideoBinding = (LayoutFeedTypeVideoBinding) binding;
                layoutFeedTypeVideoBinding.setFeed(item);
                layoutFeedTypeVideoBinding.playerView.bindData(category,item.width,item.height,item.cover,item.url);
            }
        }
    }
}
