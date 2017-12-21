package com.margarita.vk_app.ui.holder.post;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.models.view.post.RepostHeader;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RepostHeaderHolder extends BaseViewHolder<RepostHeader> {

    @BindView(R.id.civRepostProfileImage)
    CircleImageView civProfileImage;

    @BindView(R.id.tvRepostProfileName)
    TextView tvProfileName;

    @BindView(R.id.tvRepostText)
    TextView tvRepostText;

    @BindView(R.id.tvRepostDate)
    TextView tvRepostDate;

    public RepostHeaderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(RepostHeader repostHeader) {
        loadImage(repostHeader.getProfilePhoto(), civProfileImage);
        tvProfileName.setText(repostHeader.getProfileName());
        setUpTextView(tvRepostText, repostHeader.getText());
        tvRepostDate.setText(Utils.parseDate(repostHeader.getDate()));
    }

    @Override
    public void unbindViewHolder() {
        clearImageView(civProfileImage);
        clearTextView(tvProfileName);
        clearTextView(tvRepostText);
        clearTextView(tvRepostDate);
    }
}
