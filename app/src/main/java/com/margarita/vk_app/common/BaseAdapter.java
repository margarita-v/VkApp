package com.margarita.vk_app.common;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.margarita.vk_app.models.view.BaseViewModel;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder<BaseViewModel>> {

    private List<BaseViewModel> list = new ArrayList<>();

    /**
     * Key is a type of model and layout, value is a model.
     * Need to create a concrete view holder.
     */
    private SparseArray<BaseViewModel> typeInstances = new SparseArray<>();

    @Override
    public BaseViewHolder<BaseViewModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        return typeInstances.get(viewType).createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<BaseViewModel> holder, int position) {
        holder.bindViewHolder(list.get(position));
    }

    @Override
    public void onViewRecycled(BaseViewHolder<BaseViewModel> holder) {
        super.onViewRecycled(holder);
        holder.unbindViewHolder();
    }

    @Override
    public int getItemViewType(int position) {
        return typeInstances.keyAt(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * Register of layout type
     * @param item Item which will be added to adapter
     */
    private void registerTypeInstance(BaseViewModel item) {
        int key = item.getType().getId();
        if (typeInstances.indexOfKey(key) != -1) {
            typeInstances.put(key, item);
        }
    }

    /**
     * Add new items to the adapter
     * @param newItems Items which will be added to the adapter
     */
    public void addItems(List<BaseViewModel> newItems) {
        for (BaseViewModel newItem: newItems) {
            registerTypeInstance(newItem);
        }

        list.addAll(newItems);
        notifyDataSetChanged();
    }

    /**
     * Set a new item list to the adapter
     * @param items Items which will be set to the adapter
     */
    public void setItems(List<BaseViewModel> items) {
        list.clear();
        typeInstances.clear();
        addItems(items);
    }
}
