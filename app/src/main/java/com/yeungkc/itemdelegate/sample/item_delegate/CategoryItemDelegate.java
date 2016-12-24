package com.yeungkc.itemdelegate.sample.item_delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeungkc.itemdelegate.AbsListItemItemDelegate;
import com.yeungkc.itemdelegate.sample.bean.Category;
import com.yeungkc.itemdelegate.sample.R;

import java.util.List;

public class CategoryItemDelegate extends AbsListItemItemDelegate<Category, Object, CategoryItemDelegate.CategoryViewHolder> {

    @Override
    protected boolean isForViewType(@NonNull Object item, @NonNull List<Object> items, int position) {
        return item instanceof Category;
    }

    @NonNull
    @Override
    protected CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull Category item, @NonNull CategoryViewHolder viewHolder, @NonNull List payloads) {
        viewHolder.bind(item);
    }

    @Override
    protected long getItemId(@NonNull List<Object> items, int position) {
        return items.get(position).hashCode();
    }

    @Override
    protected int getSpan(@NonNull List<Object> items, int position, int spanCount) {
        return spanCount;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitle;

        CategoryViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView;
        }

        void bind(Category item) {
            mTitle.setText(item.getTitle());
        }
    }
}
