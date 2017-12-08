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
    protected CircleImageView civProfileImage;

    @BindView(R.id.tvProfileName)
    protected TextView tvProfileName;

    ProfileHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(T item) {
        Glide.with(itemView.getContext())
                .load(item.getPhoto())
                .into(civProfileImage);
        tvProfileName.setText(item.getName());
    }

    @Override
    public void unbindViewHolder() {
        civProfileImage.setImageBitmap(null);
        clearTextView(tvProfileName);
    }
}
