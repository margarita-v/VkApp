package com.margarita.vk_app.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.NewsItemHeader;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewsItemHeaderHolder extends BaseViewHolder<NewsItemHeader> {

    private CircleImageView civProfileImage;

    private TextView tvProfileName;

    private ImageView ivRepostedIcon;

    private TextView tvRepostedProfileName;

    public NewsItemHeaderHolder(View itemView) {
        super(itemView);
        civProfileImage = itemView.findViewById(R.id.civProfileImage);
        tvProfileName = itemView.findViewById(R.id.tvProfileName);
        ivRepostedIcon = itemView.findViewById(R.id.ivRepostedIcon);
        tvRepostedProfileName = itemView.findViewById(R.id.tvRepostedProfileName);
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
