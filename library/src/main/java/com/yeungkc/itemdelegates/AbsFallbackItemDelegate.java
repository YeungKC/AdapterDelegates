package com.yeungkc.itemdelegates;

import android.support.annotation.NonNull;

/**
 * This class can be used as base class for a fallback delegate {@link
 * ItemDelegatesManager#setFallbackDelegate(ItemDelegate)}.
 *
 * @author Hannes Dorfmann
 * @since 1.1.0
 */
public abstract class AbsFallbackItemDelegate<T> extends ItemDelegate<T> {

    /**
     * Not needed, because never called for fallback adapter delegates.
     *
     * @param items    The data source of the Adapter
     * @param position The position in the datasource
     * @return true
     */
    @Override
    final protected boolean isForViewType(@NonNull Object items, int position) {
        return true;
    }
}
