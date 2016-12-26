/*
 * Copyright (c) 2015 Hannes Dorfmann.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.yeungkc.itemdelegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * An implementation of an Adapter that already uses a {@link ItemDelegatesManager} and calls
 * the corresponding {@link ItemDelegatesManager} methods from Adapter's method like {@link
 * #onCreateViewHolder(ViewGroup, int)}, {@link #onBindViewHolder(RecyclerView.ViewHolder, int)}
 * and {@link #getItemViewType(int)}. So everything is already setup for you. You just have to add
 * the {@link ItemDelegate}s i.e.
 *
 * @param <T> The type of the dataSource / dataSets
 * @author Hannes Dorfmann
 */
public abstract class AbsDelegationAdapter<T> extends RecyclerView.Adapter {

    protected ItemDelegatesManager<T> delegatesManager;
    protected T dataSets;

    public AbsDelegationAdapter() {
        this(new ItemDelegatesManager<T>());
    }

    public AbsDelegationAdapter(@NonNull ItemDelegatesManager<T> delegatesManager) {
        if (delegatesManager == null) {
            throw new NullPointerException("ItemDelegatesManager is null");
        }

        this.delegatesManager = delegatesManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegatesManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegatesManager.onBindViewHolder(dataSets, position, holder);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        delegatesManager.onBindViewHolder(dataSets, position, holder, payloads);
    }

    @Override
    public int getItemViewType(int position) {
        return delegatesManager.getItemViewType(dataSets, position);
    }

    @Override
    public long getItemId(int position) {
        return delegatesManager.getItemId(dataSets, position);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        delegatesManager.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        return delegatesManager.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        delegatesManager.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        delegatesManager.onViewDetachedFromWindow(holder);
    }

    /**
     * Get the dataSets / data source of this adapter
     *
     * @return The dataSets / data source
     */
    public T getDataSets() {
        return dataSets;
    }

    /**
     * Set the dataSets / data source of this adapter
     *
     * @param dataSets The dataSets / data source
     */
    public void setDataSets(T dataSets) {
        this.dataSets = dataSets;
    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup(final int spanCount) {
        return new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return delegatesManager.getSpanSize(dataSets, position, spanCount);
            }
        };
    }
}
