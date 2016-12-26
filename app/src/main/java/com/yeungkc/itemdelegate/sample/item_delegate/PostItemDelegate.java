package com.yeungkc.itemdelegate.sample.item_delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeungkc.itemdelegate.AbsListItemItemDelegate;
import com.yeungkc.itemdelegate.sample.bean.Post;
import com.yeungkc.itemdelegate.sample.R;

import java.util.List;

public class PostItemDelegate extends AbsListItemItemDelegate<Post,Object,PostItemDelegate.PostViewHolder>{
    @Override
    protected boolean isForViewType(@NonNull Object data, @NonNull List<Object> dataSets, int position) {
        return data instanceof Post;
    }

    @NonNull
    @Override
    protected PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull Post data, @NonNull PostViewHolder viewHolder, @NonNull List payloads) {
        viewHolder.bind(data);
    }

    @Override
    protected long getItemId(@NonNull Post data, @NonNull List<Object> dataSets, int position) {
        return data.hashCode();
    }

    @Override
    protected int getSpanSize(@NonNull List<Object> dataSets, int position, int spanCount) {
        // default value = 1
        return super.getSpanSize(dataSets, position, spanCount);
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        private final TextView mContent;
        private final ImageView mIv;

        PostViewHolder(View itemView) {
            super(itemView);
            mIv = (ImageView) itemView.findViewById(R.id.iv);
            mContent = (TextView) itemView.findViewById(R.id.tv);
        }

        void bind(Post item) {
            mIv.setImageResource(item.getBgId());
            mContent.setText(item.getContent());
        }
    }
}
