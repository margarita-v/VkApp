package com.margarita.vk_app.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.margarita.vk_app.R;
import com.margarita.vk_app.common.manager.VkFragmentManager;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.ui.activity.BaseActivity;
import com.margarita.vk_app.ui.fragment.base.BaseFragment;

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
     * Clear on click listener for the itemview
     */
    protected void clearOnClickListener() {
        itemView.setOnClickListener(null);
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

    /**
     * Set up text view with text and visibility
     * @param textView TextView which text will be set
     * @param text Text for TextView
     */
    protected void setUpTextView(TextView textView, String text) {
        textView.setText(text);
        textView.setVisibility(text.isEmpty() ? View.GONE : View.VISIBLE);
    }

    /**
     * Add new fragment to fragment manager when user clicks to the itemview
     * @param fragmentManager Fragment manager for all fragments
     * @param fragment Fragment which will be added
     */
    protected void addFragmentOnClick(VkFragmentManager fragmentManager,
                                      BaseFragment fragment) {
        itemView.setOnClickListener(view ->
                fragmentManager.addFragment(
                        (BaseActivity) view.getContext(), fragment, R.id.container)
        );
    }

    /**
     * Function for parsing int value to String
     * @param value int value which will be parsed
     * @return String representation of value
     */
    protected String parseIntToString(int value) {
        return String.valueOf(value);
    }
}
