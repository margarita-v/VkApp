package com.margarita.vk_app.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.margarita.vk_app.models.view.base.BaseViewModel;

public abstract class BaseViewHolder<Item extends BaseViewModel>
        extends RecyclerView.ViewHolder {

    protected BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindViewHolder(Item item);

    public abstract void unbindViewHolder();

    /**
     * Clear text view's text on unbind view holder
     * @param textView TextView which text will be cleared
     */
    protected void clearTextView(TextView textView) {
        textView.setText(null);
    }

    /**
     * Clear image view's image on unbind view holder
     * @param imageView ImageView which image will be cleared
     */
    protected void clearImageView(ImageView imageView) {
        imageView.setImageBitmap(null);
    }

    /**
     * Show picture
     * @param imagePath Path for loading picture
     * @param imageView Component which will show the picture
     */
    protected void loadImage(String imagePath, ImageView imageView) {
        Glide.with(itemView.getContext())
                .load(imagePath)
                .into(imageView);
    }
}
