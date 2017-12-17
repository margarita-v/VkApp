package com.margarita.vk_app.ui.holder.attachment;

import android.view.View;
import android.widget.ImageView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.manager.VkFragmentManager;
import com.margarita.vk_app.models.view.attachment.ImageAttachment;
import com.margarita.vk_app.ui.activity.BaseActivity;
import com.margarita.vk_app.ui.fragment.attachment.ImageFragment;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageAttachmentHolder extends BaseViewHolder<ImageAttachment> {

    @BindView(R.id.ivAttachmentImage)
    ImageView ivAttachmentImage;

    @Inject
    VkFragmentManager fragmentManager;

    public ImageAttachmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void bindViewHolder(ImageAttachment imageAttachment) {
        if (imageAttachment.isNeedClick()) {
            itemView.setOnClickListener(view ->
                    fragmentManager.addFragment(
                            (BaseActivity) itemView.getContext(),
                            ImageFragment.newInstance(imageAttachment.getPhotoUrl()),
                            R.id.container));
        }

        loadImage(imageAttachment.getPhotoUrl(), ivAttachmentImage);
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        clearImageView(ivAttachmentImage);
    }
}
