package com.margarita.vk_app.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.TopicViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicViewHolder extends BaseViewHolder<TopicViewModel> {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvCommentsCount)
    TextView tvCommentsCount;

    public TopicViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(TopicViewModel topicViewModel) {
        tvTitle.setText(topicViewModel.getTitle());
        tvCommentsCount.setText(topicViewModel.getCommentsCount());
    }

    @Override
    public void unbindViewHolder() {
        clearTextView(tvTitle);
        clearTextView(tvCommentsCount);
    }
}
