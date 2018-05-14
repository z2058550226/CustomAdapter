package com.suikajy.customadapter.listener;

import android.view.View;

import com.suikajy.customadapter.BCAdapter;


/**
 * Interface definition for a callback to be invoked when an item in this
 * view has been clicked and held.
 */
public interface OnItemLongClickListener {
    /**
     * callback method to be invoked when an item in this view has been
     * click and held
     *
     * @param adapter  the adpater
     * @param view     The view whihin the RecyclerView that was clicked and held.
     * @param position The position of the view int the adapter
     * @return true if the callback consumed the long click ,false otherwise
     */
    boolean onItemLongClick(BCAdapter adapter, View view, int position);
}