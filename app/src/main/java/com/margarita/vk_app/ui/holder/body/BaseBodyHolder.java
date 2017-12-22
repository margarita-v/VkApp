package com.margarita.vk_app.ui.holder.body;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.common.manager.VkFragmentManager;
import com.margarita.vk_app.models.view.body.BaseBodyItem;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Base class for view holders which contain body items
 */
public abstract class BaseBodyHolder<T extends BaseBodyItem> extends BaseViewHolder<T> {

    @BindView(R.id.tvText)
    TextView tvText;

    @BindView(R.id.tvAttachments)
    TextView tvAttachments;

    @Inject
    Typeface googleFont;

    @Inject
    VkFragmentManager fragmentManager;

    BaseBodyHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        tvAttachments.setTypeface(googleFont);
    }

    @Override
    public void unbindViewHolder() {
        clearTextView(tvText);
        clearTextView(tvAttachments);
    }
}
