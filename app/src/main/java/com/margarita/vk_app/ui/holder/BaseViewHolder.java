package com.margarita.vk_app.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.models.view.BaseViewModel;

public abstract class BaseViewHolder<Item extends BaseViewModel>
        extends RecyclerView.ViewHolder {

    BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindViewHolder(Item item);

    public abstract void unbindViewHolder();

    /**
     * Clear text view's text on unbind view holder
     * @param textView TextView which text will be cleared
     */
    void clearTextView(TextView textView) {
        textView.setText(null);
    }
}
