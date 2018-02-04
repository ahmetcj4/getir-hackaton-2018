package com.omka.mackhaton.common.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<ViewHolder<T>> {
    public ViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new ViewHolder<T>(binding);
    }

    public void onBindViewHolder(ViewHolder<T> holder, int position) {
        T obj = getObjForPosition(position);
        holder.bind(obj);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    protected abstract T getObjForPosition(int position);

    protected abstract int getLayoutIdForPosition(int position);
}