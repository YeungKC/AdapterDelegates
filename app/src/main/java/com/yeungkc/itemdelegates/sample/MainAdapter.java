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

package com.yeungkc.itemdelegates.sample;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.yeungkc.itemdelegates.ItemDelegatesManager;
import com.yeungkc.itemdelegates.sample.adapterdelegates.AdvertisementItemDelegate;
import com.yeungkc.itemdelegates.sample.adapterdelegates.CatItemDelegate;
import com.yeungkc.itemdelegates.sample.adapterdelegates.DogItemDelegate;
import com.yeungkc.itemdelegates.sample.adapterdelegates.GeckoItemDelegate;
import com.yeungkc.itemdelegates.sample.adapterdelegates.SnakeListItemItemDelegate;
import com.yeungkc.itemdelegates.sample.model.DisplayableItem;
import java.util.List;

/**
 * @author Hannes Dorfmann
 */
public class MainAdapter extends RecyclerView.Adapter {

  private ItemDelegatesManager<List<DisplayableItem>> delegatesManager;
  private List<DisplayableItem> items;

  public MainAdapter(Activity activity, List<DisplayableItem> items) {
    this.items = items;

    // Delegates
    delegatesManager = new ItemDelegatesManager<>();
    delegatesManager.addDelegate(new AdvertisementItemDelegate(activity));
    delegatesManager.addDelegate(new CatItemDelegate(activity));
    delegatesManager.addDelegate(new DogItemDelegate(activity));
    delegatesManager.addDelegate(new GeckoItemDelegate(activity));
    delegatesManager.addDelegate(new SnakeListItemItemDelegate(activity));

  }

  @Override public int getItemViewType(int position) {
    return delegatesManager.getItemViewType(items, position);
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return delegatesManager.onCreateViewHolder(parent, viewType);
  }

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		delegatesManager.onBindViewHolder(items, position, holder);
	}

	@Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
	  delegatesManager.onBindViewHolder(items, position, holder, payloads);
  }

  @Override public int getItemCount() {
    return items.size();
  }
}
