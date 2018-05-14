package com.suikajy.customadapter.listener;

import android.view.View;

import com.suikajy.customadapter.BCAdapter;


/**
 * Interface definition for a callback to be invoked when an itemchild in this
 * view has been clicked
 */
public interface OnItemChildClickListener {
    /**
     * callback method to be invoked when an item in this view has been
     * click and held
     *
     * @param view     The view whihin the ItemView that was clicked
     * @param position The position of the view int the adapter
     */
    void onItemChildClick(BCAdapter adapter, View view, int position);
}
