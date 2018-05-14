package com.suikajy.customadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * simple version
 * Created by zjy on 2017/8/16.
 * LinearLayout adapter
 */

public class LinearListHelper {
    public static void setAdapter(LinearLayout layout, LinearAdapter adapter) {
        layout.removeAllViews();
        for (int i = 0; i < adapter.getItemCount(); i++) {
            ViewHolder viewHolder = adapter.onCreateViewHolder(layout, adapter.getItemType(i));
            adapter.onBindViewHolder(viewHolder, i);
            layout.addView(viewHolder.itemView);
        }
    }

    public static abstract class LinearAdapter<VH extends ViewHolder> {

        protected Context mContext;
        protected LayoutInflater mInflater;

        public LinearAdapter(Context mContext) {
            this.mContext = mContext;
            mInflater = LayoutInflater.from(mContext);
        }

        public abstract VH onCreateViewHolder(LinearLayout parent, int type);

        public abstract void onBindViewHolder(VH holder, int position);

        public abstract int getItemCount();

        public int getItemType(int position) {
            return -1;
        }
    }

    public static abstract class SimpleLinearAdapter<VH extends ViewHolder, T> extends LinearAdapter<VH> {
        private final LinearLayout mParent;
        protected List<T> mDataSet = new ArrayList<>();

        public SimpleLinearAdapter(Context context, LinearLayout parent) {
            super(context);
            mParent = parent;
        }

        @Override
        public abstract VH onCreateViewHolder(LinearLayout parent, int type);

        @Override
        public void onBindViewHolder(VH holder, int position) {
            if (!mDataSet.isEmpty()) {
                onSimpleBindView(holder, mDataSet.get(position), position);
            }
        }

        protected abstract void onSimpleBindView(VH holder, T t, int position);

        @Override
        public int getItemCount() {
            return mDataSet.size();
        }

        public View inflate(@LayoutRes int layoutId) {
            return mInflater.inflate(layoutId, mParent, false);
        }

        public void refreshData(List<T> list) {
            mDataSet.clear();
            mDataSet.addAll(list);
            setAdapter(mParent, this);
        }

        public void loadMore(List<T> list) {
            mDataSet.addAll(list);
            setAdapter(mParent, this);
        }

        public void clear() {
            mDataSet.clear();
            setAdapter(mParent, this);
        }

        public boolean isAlreadyLoaded() {
            return !mDataSet.isEmpty();
        }

        public void addItem(T element) {
            final int elePosition = mDataSet.size();
            mDataSet.add(element);
            VH viewHolder = onCreateViewHolder(mParent, getItemType(elePosition));
            onBindViewHolder(viewHolder, elePosition);
            mParent.addView(viewHolder.itemView);
        }
    }

    public static class ViewHolder {
        public View itemView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
        }
    }

}
