package com.yeungkc.itemdelegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * A simplified {@link ItemDelegate} when the underlying adapter's dataset is a  {@linkplain
 * List}. This class helps to reduce writing boilerplate code like casting list item and casting
 * ViewHolder.
 *
 * @param <I>  The type of the item that is managed by this ItemDelegate. Must be a subtype of T
 * @param <T>  The generic type of the list, in other words: {@code List<T>}
 * @param <VH> The type of the ViewHolder
 * @author Hannes Dorfmann
 */
public abstract class AbsListItemItemDelegate<I extends T, T, VH extends RecyclerView.ViewHolder>
        extends ItemDelegate<List<T>> {

    @Override
    protected final boolean isForViewType(@NonNull List<T> dataSets, int position) {
        return isForViewType(dataSets.get(position), dataSets, position);
    }

    /**
     * Called to determine whether this ItemDelegate is the responsible for the given item in the
     * list or not
     * element
     *
     * @param data     The item from the list at the given position
     * @param dataSets    The dataSets from adapters dataset
     * @param position The dataSets position in the dataset (list)
     * @return true if this ItemDelegate is responsible for that, otherwise false
     */
    protected abstract boolean isForViewType(@NonNull T data, @NonNull List<T> dataSets, int position);

    /**
     * Creates the  {@link RecyclerView.ViewHolder} for the given data source item
     *
     * @param parent The ViewGroup parent of the given datasource
     * @return ViewHolder
     */
    @NonNull
    @Override
    protected abstract VH onCreateViewHolder(@NonNull ViewGroup parent);

    @Override
    @SuppressWarnings("unchecked")
    protected final void onBindViewHolder(@NonNull List<T> dataSets, int position,
                                          @NonNull RecyclerView.ViewHolder holder, @NonNull List payloads) {
        onBindViewHolder((I) dataSets.get(position), (VH) holder, payloads);
    }

    /**
     * Called to bind the {@link RecyclerView.ViewHolder} to the item of the dataset
     *
     * @param data       The data item
     * @param viewHolder The ViewHolder
     * @param viewHolder The payloads
     */
    protected abstract void onBindViewHolder(@NonNull I data, @NonNull VH viewHolder,
                                             @NonNull List payloads);

    @Override
    @SuppressWarnings("unchecked")
    protected final long getItemId(@NonNull List<T> dataSets, int position) {
        return getItemId((I) dataSets.get(position), dataSets, position);
    }

    protected long getItemId(@NonNull I data, @NonNull List<T> dataSets, int position) {
        return super.getItemId(dataSets, position);
    }
}
