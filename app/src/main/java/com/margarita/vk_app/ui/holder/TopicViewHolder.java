package com.margarita.vk_app.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.manager.VkFragmentManager;
import com.margarita.vk_app.models.common.Place;
import com.margarita.vk_app.models.view.TopicViewModel;
import com.margarita.vk_app.ui.fragment.comments.TopicCommentsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicViewHolder extends BaseViewHolder<TopicViewModel> {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvComments)
    TextView tvComments;

    @Inject
    VkFragmentManager fragmentManager;

    public TopicViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void bindViewHolder(TopicViewModel topicViewModel) {
        tvTitle.setText(topicViewModel.getTitle());
        tvComments.setText(topicViewModel.getCommentsCount());
        addFragmentOnClick(fragmentManager,
                TopicCommentsFragment.newInstance(new Place(
                        parseIntToString(topicViewModel.getGroupId()),
                        parseIntToString(topicViewModel.getId()))));
    }

    @Override
    public void unbindViewHolder() {
        clearTextView(tvTitle);
        clearTextView(tvComments);
    }
}
