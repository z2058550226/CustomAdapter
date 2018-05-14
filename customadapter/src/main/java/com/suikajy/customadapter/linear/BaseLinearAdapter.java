package com.suikajy.customadapter.linear;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.suikajy.customadapter.BCAdapter;
import com.suikajy.customadapter.BCViewHolder;

import java.util.List;

/**
 * @author zjy
 * @date 2018/5/4
 */
public abstract class BaseLinearAdapter<T, VH extends BCViewHolder> extends BCAdapter<T, VH> {

    protected LinearLayout mParent;

    public BaseLinearAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public BaseLinearAdapter(@Nullable List<T> data) {
        super(data);
    }

    public BaseLinearAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public ViewGroup getParent() {
        return mParent;
    }

    @Nullable
    @Override
    public View getViewByPosition(ViewGroup parent, int position, int viewId) {
        if (parent == null) {
            return null;
        }
        View childView = parent.getChildAt(position);
        BCViewHolder viewHolder = (BCViewHolder) childView.getTag();
        if (viewHolder == null) {
            return null;
        }
        return viewHolder.getView(viewId);
    }

    @Override
    public void setParent(ViewGroup parent) {
        this.mParent = (LinearLayout) parent;
    }
}
