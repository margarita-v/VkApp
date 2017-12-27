package com.margarita.vk_app.ui.holder.post;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.post.PostHeader;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PostHeaderHolder extends BaseViewHolder<PostHeader> {

    @BindView(R.id.civProfileImage)
    CircleImageView civProfilePhoto;

    @BindView(R.id.tvProfileName)
    TextView tvProfileName;

    @BindView(R.id.tvText)
    TextView tvText;

    public PostHeaderHolder(View itemView, boolean forOpenedComment) {
        super(itemView, forOpenedComment);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(PostHeader postHeader) {
        loadImage(postHeader.getProfilePhoto(), civProfilePhoto);
        tvProfileName.setText(postHeader.getProfileName());
        setUpTextView(tvText, postHeader.getText());
    }

    @Override
    public void unbindViewHolder() {
        if (!forOpenedComment) {
            clearImageView(civProfilePhoto);
            clearTextView(tvProfileName);
            clearTextView(tvText);
        }
    }
}
