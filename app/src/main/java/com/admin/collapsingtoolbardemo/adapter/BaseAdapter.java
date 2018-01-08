package com.admin.collapsingtoolbardemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseAdapter<T> extends SimpleAdapter {
    public Context mContext;
    private int mFirstPageIndex = 0;
    protected List<T> mListData = new ArrayList<T>();

    protected abstract View getItemView(int position, View convertView,
                                        ViewGroup parent);

    public BaseAdapter(Context context) {
        super(context, null, 0, null, null);
        this.mContext = context;
        this.mFirstPageIndex = 0;
    }

    public BaseAdapter(Context context, int firstPageIndex) {
        super(context, null, 0, null, null);
        this.mContext = context;
        this.mFirstPageIndex = firstPageIndex;
    }

    public void initData(int pageIndex, List<T> data) {
        if (pageIndex == mFirstPageIndex) {
            mListData.clear();
        }
        if (null != data) {
            mListData.addAll(data);
        }
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public T getItem(int position) {
        if (position > -1 && position < mListData.size()) {
            return mListData.get(position);
        }
        return null;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position, convertView, parent);
    }

}

