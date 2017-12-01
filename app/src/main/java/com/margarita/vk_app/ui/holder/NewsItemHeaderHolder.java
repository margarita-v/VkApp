package com.margarita.vk_app.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.NewsItemHeader;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class NewsItemHeaderHolder extends BaseViewHolder<NewsItemHeader> {

    @BindView(R.id.civProfileImage)
    private CircleImageView civProfileImage;

    @BindView(R.id.tvProfileName)
    private TextView tvProfileName;

    @BindView(R.id.tvRepostsIcon)
    private ImageView ivRepostedIcon;

    @BindView(R.id.tvRepostedProfileName)
    private TextView tvRepostedProfileName;

    public NewsItemHeaderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(NewsItemHeader item) {
        Glide.with(itemView.getContext())
                .load(item.getProfilePhoto())
                .into(civProfileImage);

        tvProfileName.setText(item.getProfileName());
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
        civProfileImage.setImageBitmap(null);
        clearTextView(tvProfileName);
        clearTextView(tvRepostedProfileName);
    }
}
