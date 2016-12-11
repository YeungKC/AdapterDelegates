package com.yeungkc.itemdelegates.sample.adapterdelegates;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yeungkc.adapterdelegates.sample.R;
import com.yeungkc.itemdelegates.AbsListItemItemDelegate;
import com.yeungkc.itemdelegates.sample.model.DisplayableItem;
import com.yeungkc.itemdelegates.sample.model.Snake;
import java.util.List;

/**
 * @author Hannes Dorfmann
 */
public class SnakeListItemItemDelegate extends
        AbsListItemItemDelegate<Snake, DisplayableItem, SnakeListItemItemDelegate.SnakeViewHolder> {

  private LayoutInflater inflater;

  public SnakeListItemItemDelegate(Activity activity) {
    inflater = activity.getLayoutInflater();
  }

  @Override
  protected boolean isForViewType(@NonNull DisplayableItem item, List<DisplayableItem> items,
      int position) {
    return item instanceof Snake;
  }

  @NonNull @Override public SnakeListItemItemDelegate.SnakeViewHolder onCreateViewHolder(
      @NonNull ViewGroup parent) {
    return new SnakeListItemItemDelegate.SnakeViewHolder(
        inflater.inflate(R.layout.item_snake, parent, false));
  }

  @Override protected void onBindViewHolder(@NonNull Snake snake,
                                            @NonNull SnakeListItemItemDelegate.SnakeViewHolder vh, @Nullable List payloads) {

    vh.name.setText(snake.getName());
    vh.race.setText(snake.getRace());
  }

  static class SnakeViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView race;

    public SnakeViewHolder(View itemView) {
      super(itemView);
      name = (TextView) itemView.findViewById(R.id.name);
      race = (TextView) itemView.findViewById(R.id.race);
    }
  }
}
