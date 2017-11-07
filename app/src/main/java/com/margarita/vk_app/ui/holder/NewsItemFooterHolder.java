package com.margarita.vk_app.ui.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.models.view.NewsItemFooter;
import com.margarita.vk_app.models.view.counter.CounterViewModel;

import javax.inject.Inject;

public class NewsItemFooterHolder extends BaseViewHolder<NewsItemFooter> {

    private TextView tvDate;

    private TextView tvLikesIcon;
    private TextView tvLikesCount;

    private TextView tvCommentsIcon;
    private TextView tvCommentsCount;

    private TextView tvRepostIcon;
    private TextView tvRepostCount;

    @Inject
    Typeface googleFont;

    public NewsItemFooterHolder(View itemView) {
        super(itemView);
        VkApplication.getApplicationComponent().inject(this);

        tvDate = itemView.findViewById(R.id.tvDate);
        tvLikesIcon = itemView.findViewById(R.id.tvLikesIcon);
        tvLikesCount = itemView.findViewById(R.id.tvLikesCount);
        tvCommentsIcon = itemView.findViewById(R.id.tvCommentsIcon);
        tvCommentsCount = itemView.findViewById(R.id.tvCommentsCount);
        tvRepostIcon = itemView.findViewById(R.id.tvRepostsIcon);
        tvRepostCount = itemView.findViewById(R.id.tvRepostsCount);

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
        tvCount.setTextColor(counter.getTextColor());
        tvIcon.setTextColor(counter.getIconColor());
    }
}
