package com.margarita.vk_app.ui.holder.profile;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.profile.NewsItemHeader;

import butterknife.BindView;

public class NewsItemHeaderHolder extends ProfileHolder<NewsItemHeader> {

    @BindView(R.id.ivRepostedIcon)
    ImageView ivRepostedIcon;

    @BindView(R.id.tvRepostedProfileName)
    TextView tvRepostedProfileName;

    public NewsItemHeaderHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewHolder(NewsItemHeader item) {
        super.bindViewHolder(item);
        if (item.isRepost()) {
            ivRepostedIcon.setVisibility(View.VISIBLE);
            tvRepostedProfileName.setText(item.getRepostProfileName());
        } else {
            ivRepostedIcon.setVisibility(View.GONE);
            tvRepostedProfileName.setText(null);
        }
    }

    @Override
    public void unbindViewHolder() {
        super.unbindViewHolder();
        clearTextView(tvRepostedProfileName);
    }
}
