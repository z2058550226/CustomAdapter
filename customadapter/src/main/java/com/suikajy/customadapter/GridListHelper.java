package com.suikajy.customadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始版，比较简单，现已换成新的
 * Created by zjy on 2017/8/21.
 * GridLayout的适配器
 */
public class GridListHelper {

    public static void setAdapter(GridLayout layout, GridAdapter adapter) {
        layout.removeAllViews();
        for (int i = 0; i < adapter.getItemCount(); i++) {
            ViewHolder viewHolder = adapter.onCreateViewHolder(layout, adapter.getItemType(i));
            adapter.onBindViewHolder(viewHolder, i);
            layout.addView(viewHolder.itemView);
        }
    }

    public static abstract class GridAdapter<VH extends ViewHolder> {

        protected Context mContext;
        protected LayoutInflater mInflater;

        public GridAdapter(Context mContext) {
            this.mContext = mContext;
            mInflater = LayoutInflater.from(mContext);
        }

        public abstract VH onCreateViewHolder(GridLayout parent, int type);

        public abstract void onBindViewHolder(VH holder, int position);

        public abstract int getItemCount();

        public int getItemType(int position) {
            return -1;
        }
    }

    /**
     * 单一元素的Adapter，减少更多样板代码
     *
     * @param <VH> 自己的ViewHolder
     * @param <T>  数据类泛型
     */
    public static abstract class SimpleGridAdapter<VH extends GridListHelper.ViewHolder, T> extends GridAdapter<VH> {
        protected final GridLayout mParent;
        protected List<T> mDataSet = new ArrayList<>();

        public SimpleGridAdapter(Context context, GridLayout parent) {
            super(context);
            mParent = parent;
        }

        @Override
        public abstract VH onCreateViewHolder(GridLayout parent, int type);

        @Override
        public void onBindViewHolder(VH holder, int position) {
            onSimpleBindView(holder, mDataSet.get(position), position);
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
