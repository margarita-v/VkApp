package com.margarita.vk_app.common;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.margarita.vk_app.models.view.base.BaseViewModel;
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
        holder.bindViewHolder(getItem(position));
    }

    @Override
    public void onViewRecycled(BaseViewHolder<BaseViewModel> holder) {
        super.onViewRecycled(holder);
        holder.unbindViewHolder();
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType().getId();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public int getRealItemCount() {
        int count = 0;
        for (BaseViewModel item: list) {
            if (!item.isItemDecorator())
                count++;
        }
        return count;
    }

    private BaseViewModel getItem(int position) {
        return list.get(position);
    }

    /**
     * Register of layout type
     * @param item Item which will be added to adapter
     */
    private void registerTypeInstance(BaseViewModel item) {
        int key = item.getType().getId();
        if (typeInstances.indexOfKey(key) < 0) {
            typeInstances.put(key, item);
        }
    }

    /**
     * Add new items to the adapter
     * @param newItems Items which will be added to the adapter
     */
    public void addItems(List<? extends BaseViewModel> newItems) {
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
    public void setItems(List<? extends BaseViewModel> items) {
        list.clear();
        typeInstances.clear();
        addItems(items);
    }
}
