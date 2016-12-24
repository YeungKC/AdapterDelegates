package com.yeungkc.itemdelegate.sample.item_delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeungkc.itemdelegate.AbsListItemItemDelegate;
import com.yeungkc.itemdelegate.ItemDelegatesManager;
import com.yeungkc.itemdelegate.ListDelegationAdapter;
import com.yeungkc.itemdelegate.sample.R;
import com.yeungkc.itemdelegate.sample.bean.Post;

import java.util.List;

import static com.yeungkc.itemdelegate.sample.R.id.rv;

public class RvItemDelegate extends AbsListItemItemDelegate<List<Post>,List<Post>,RvItemDelegate.RvViewHolder> {
    @Override
    protected boolean isForViewType(@NonNull List<Post> item, @NonNull List<List<Post>> items, int position) {
        return true;
    }

    @NonNull
    @Override
    protected RvItemDelegate.RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new RvViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull List<Post> item, @NonNull RvItemDelegate.RvViewHolder viewHolder, @NonNull List payloads) {
        viewHolder.bind(item);
    }

    @Override
    protected long getItemId(@NonNull List<Post> item, @NonNull List<List<Post>> items, int position) {
        return item.hashCode();
    }

    public class RvViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView mRv;
        private final int SPAN_COUNT = 3;
        private final ListDelegationAdapter<List<Object>> mAdapter;

        public RvViewHolder(View itemView) {
            super(itemView);
            ItemDelegatesManager<List<Object>> listItemDelegatesManager = new ItemDelegatesManager<>();
            listItemDelegatesManager.addDelegate(new PostItemDelegate());

            mAdapter = new ListDelegationAdapter<>(listItemDelegatesManager);
            mAdapter.setHasStableIds(true);

            GridLayoutManager layout = new GridLayoutManager(itemView.getContext(), SPAN_COUNT) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            layout.setSpanSizeLookup(mAdapter.getSpanSizeLookup(layout.getSpanCount()));


            mRv = (RecyclerView)itemView.findViewById(rv);
            mRv.setLayoutManager(layout);
            mRv.setAdapter(mAdapter);
        }


        public void bind(List item) {
            mAdapter.setItems(item);
        }
    }
}
