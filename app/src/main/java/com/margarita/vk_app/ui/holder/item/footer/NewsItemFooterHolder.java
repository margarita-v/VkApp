package com.margarita.vk_app.ui.holder.item.footer;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.manager.VkFragmentManager;
import com.margarita.vk_app.models.common.Place;
import com.margarita.vk_app.models.view.item.footer.NewsItemFooter;
import com.margarita.vk_app.ui.activity.BaseActivity;
import com.margarita.vk_app.ui.fragment.CommentsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsItemFooterHolder extends BaseFooterHolder<NewsItemFooter> {

    @BindView(R.id.tvCommentsIcon)
    TextView tvCommentsIcon;

    @BindView(R.id.tvCommentsCount)
    TextView tvCommentsCount;

    @BindView(R.id.tvRepostsIcon)
    TextView tvRepostIcon;

    @BindView(R.id.tvRepostsCount)
    TextView tvRepostCount;

    @BindView(R.id.comments)
    View viewComments;

    @Inject
    VkFragmentManager fragmentManager;

    public NewsItemFooterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        VkApplication.getApplicationComponent().inject(this);
        tvLikesIcon.setTypeface(googleFont);
        tvCommentsIcon.setTypeface(googleFont);
        tvRepostIcon.setTypeface(googleFont);
    }

    @Override
    public void bindViewHolder(NewsItemFooter item) {
        super.bindViewHolder(item);
        bindFooterItem(tvCommentsCount, tvCommentsIcon, item.getCommentsCounter());
        bindFooterItem(tvRepostCount, tvRepostIcon, item.getRepostCounter());
        viewComments.setOnClickListener(view ->
                fragmentManager.addFragment(
                        (BaseActivity) view.getContext(),
                        CommentsFragment.newInstance(new Place(
                                parseIntToString(item.getOwnerId()),
                                parseIntToString(item.getId()))),
                        R.id.container));
    }

    @Override
    public void unbindViewHolder() {
        super.unbindViewHolder();
        viewComments.setOnClickListener(null);
        clearTextView(tvCommentsCount);
        clearTextView(tvRepostCount);
    }
}
