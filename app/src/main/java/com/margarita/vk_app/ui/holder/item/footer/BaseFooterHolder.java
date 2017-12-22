package com.margarita.vk_app.ui.holder.item.footer;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.models.view.counter.CounterViewModel;
import com.margarita.vk_app.models.view.item.footer.BaseFooterItem;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Base class for view holders which contain footer items
 */
public abstract class BaseFooterHolder<T extends BaseFooterItem> extends BaseViewHolder<T> {

    @BindView(R.id.tvDate)
    TextView tvDate;

    @BindView(R.id.tvLikesIcon)
    TextView tvLikesIcon;

    @BindView(R.id.tvLikesCount)
    TextView tvLikesCount;

    @Inject
    Typeface googleFont;

    private Context context;

    BaseFooterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
    }

    @Override
    public void bindViewHolder(T item) {
        tvDate.setText(Utils.parseDate(item.getDateLong()));
        bindFooterItem(tvLikesCount, tvLikesIcon, item.getLikeCounter());
    }

    @Override
    public void unbindViewHolder() {
        clearTextView(tvDate);
        clearTextView(tvLikesCount);
    }

    /**
     * Set text color for TextView
     * @param textView TextView which text color will be changed
     * @param color Color which will be set
     */
    private void setColor(TextView textView, int color) {
        textView.setTextColor(ContextCompat.getColor(context, color));
    }

    /**
     * Configure footer item
     * @param tvCount TextView for count of item
     * @param tvIcon TextView for item's icon
     * @param counter Counter for an item
     */
    void bindFooterItem(TextView tvCount, TextView tvIcon, CounterViewModel counter) {
        tvCount.setText(String.valueOf(counter.getCount()));
        setColor(tvCount, counter.getTextColor());
        setColor(tvIcon, counter.getIconColor());
    }
}
