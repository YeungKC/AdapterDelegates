package com.yeungkc.itemdelegate.sample.item_delegate;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeungkc.itemdelegate.AbsListItemItemDelegate;
import com.yeungkc.itemdelegate.sample.R;
import com.yeungkc.itemdelegate.sample.bean.Episode;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class EpisodeItemDelegate extends AbsListItemItemDelegate<Episode, Object, EpisodeItemDelegate.EpisodeViewHolder> {
    private final Set<Integer> mSelectedSet;

    public EpisodeItemDelegate() {
        this(null);
    }

    public EpisodeItemDelegate(@Nullable TreeSet<Integer> selectedSet) {
        if (selectedSet == null) {
            selectedSet = new TreeSet<>();
        }
        mSelectedSet = selectedSet;
    }

    public Set<Integer> getSelectedSet() {
        return mSelectedSet;
    }

    @Override
    protected boolean isForViewType(@NonNull Object item, @NonNull List<Object> items, int position) {
        return item instanceof Episode;
    }

    @NonNull
    @Override
    protected EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new EpisodeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episode, parent, false));
    }

    @Override
    protected long getItemId(@NonNull Episode item, @NonNull List<Object> items, int position) {
        return item.hashCode();
    }

    @Override
    protected void onBindViewHolder(@NonNull Episode item, @NonNull EpisodeViewHolder viewHolder, @NonNull List payloads) {
        viewHolder.bind(item);
    }

    class EpisodeViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvNum;
        private Episode mItem;

        EpisodeViewHolder(final View itemView) {
            super(itemView);
            mTvNum = (TextView) itemView.findViewById(R.id.num);
            initEvent();
        }

        private void initEvent() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mItem == null) return;

                    mItem.setSelected(!mItem.isSelected());

                    mTvNum.setSelected(mItem.isSelected());

                    if (mItem.isSelected()) {
                        mSelectedSet.add(mItem.getNum());
                    } else {
                        mSelectedSet.remove(mItem.getNum());
                    }
                }
            });
        }

        void bind(Episode item) {
            mItem = item;
            mTvNum.setText(String.valueOf(item.getNum()));
            mTvNum.setSelected(item.isSelected());
        }
    }
}
