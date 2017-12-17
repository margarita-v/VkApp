package com.margarita.vk_app.ui.holder.profile;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.profile.ProfileViewModel;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Base class for view holders which contain components for showing profile info
 */
abstract class ProfileHolder<T extends ProfileViewModel> extends BaseViewHolder<T> {

    @BindView(R.id.civProfileImage)
    CircleImageView civProfileImage;

    @BindView(R.id.tvProfileName)
    TextView tvProfileName;

    ProfileHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(T item) {
        loadImage(item.getPhoto(), civProfileImage);
        tvProfileName.setText(item.getName());
    }

    @Override
    public void unbindViewHolder() {
        clearImageView(civProfileImage);
        clearTextView(tvProfileName);
    }
}
