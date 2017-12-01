package com.margarita.vk_app.ui.holder;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.models.view.NewsItemFooter;
import com.margarita.vk_app.models.view.counter.CounterViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsItemFooterHolder extends BaseViewHolder<NewsItemFooter> {

    @BindView(R.id.tvDate)
    private TextView tvDate;

    @BindView(R.id.tvLikesIcon)
    private TextView tvLikesIcon;

    @BindView(R.id.tvLikesCount)
    private TextView tvLikesCount;

    @BindView(R.id.tvCommentsIcon)
    private TextView tvCommentsIcon;

    @BindView(R.id.tvCommentsCount)
    private TextView tvCommentsCount;

    @BindView(R.id.tvRepostsIcon)
    private TextView tvRepostIcon;

    @BindView(R.id.tvRepostsCount)
    private TextView tvRepostCount;

    private Context context;

    @Inject
    Typeface googleFont;

    public NewsItemFooterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        VkApplication.getApplicationComponent().inject(this);
        context = itemView.getContext();
        tvLikesIcon.setTypeface(googleFont);
        tvCommentsIcon.setTypeface(googleFont);
        tvRepostIcon.setTypeface(googleFont);
    }

    @Override
    public void bindViewHolder(NewsItemFooter item) {
        tvDate.setText(Utils.parseDate(item.getDateLong()));
        bindFooterItem(tvLikesCount, tvLikesIcon, item.getLikeCounter());
        bindFooterItem(tvCommentsCount, tvCommentsIcon, item.getCommentsCounter());
        bindFooterItem(tvRepostCount, tvRepostIcon, item.getRepostCounter());
    }

    @Override
    public void unbindViewHolder() {
        clearTextView(tvDate);
        clearTextView(tvLikesCount);
        clearTextView(tvCommentsCount);
        clearTextView(tvRepostCount);
    }

    /**
     * Configure footer item
     * @param tvCount TextView for count of item
     * @param tvIcon TextView for item's icon
     * @param counter Counter for an item
     */
    private void bindFooterItem(TextView tvCount, TextView tvIcon, CounterViewModel counter) {
        tvCount.setText(String.valueOf(counter.getCount()));
        setColor(tvCount, counter.getTextColor());
        setColor(tvIcon, counter.getIconColor());
    }

    /**
     * Set text color for TextView
     * @param textView TextView which text color will be changed
     * @param color Color which will be set
     */
    private void setColor(TextView textView, int color) {
        textView.setTextColor(ContextCompat.getColor(context, color));
    }
}
